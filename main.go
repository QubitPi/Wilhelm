/*
 * Copyright 2025 Jiaqi Liu. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package main

import (
	"bytes"
	"encoding/json"
	"flag"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
)

func healthCheckHandler(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(http.StatusOK)
	fmt.Fprintf(w, "OK")
}

type UpstreamRequest struct {
	Message string `json:""`
}

type UpstreamResponse struct {
	Message string `json:"message"`
}

func handler(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodPost {
		http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
		return
	}

	var incomingData map[string]interface{}
	if err := json.NewDecoder(r.Body).Decode(&incomingData); err != nil && err.Error() != "EOF" {
		log.Printf("Error decoding incoming request body: %v", err)
		http.Error(w, "Bad Request", http.StatusBadRequest)
		return
	}
	defer r.Body.Close()

	upstreamData := UpstreamRequest{Message: fmt.Sprintf("%v", incomingData)}

	jsonBody, err := json.Marshal(upstreamData)
	if err != nil {
		log.Printf("Error marshaling upstream request body: %v", err)
		http.Error(w, "Internal Server Error", http.StatusInternalServerError)
		return
	}

	upstreamURL := "http://notification-ws:8080/dingding/createNotification"
	req, err := http.NewRequest(http.MethodPost, upstreamURL, bytes.NewBuffer(jsonBody))
	if err != nil {
		log.Printf("Error creating upstream request: %v", err)
		http.Error(w, "Internal Server Error", http.StatusInternalServerError)
		return
	}
	req.Header.Set("Content-Type", "application/json")

	client := &http.Client{}
	resp, err := client.Do(req)
	if err != nil {
		log.Printf("Error sending request to upstream: %v", err)
		http.Error(w, "Internal Server Error", http.StatusInternalServerError)
		return
	}
	defer resp.Body.Close()

	upstreamRespBody, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		log.Printf("Error reading upstream response body: %v", err)
		http.Error(w, "Internal Server Error", http.StatusInternalServerError)
		return
	}

	var upstreamResponse UpstreamResponse
	if err := json.Unmarshal(upstreamRespBody, &upstreamResponse); err != nil {
		log.Printf("Error unmarshaling upstream response: %v", err)
		http.Error(w, "Internal Server Error", http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(resp.StatusCode)
	json.NewEncoder(w).Encode(upstreamResponse)
}

func main() {
	port := flag.String("port", ":8080", "Webservice port; default to 8080")
	flag.Parse()

	http.HandleFunc("/health", healthCheckHandler)
	http.HandleFunc("/createAndNotify", handler)

	http.ListenAndServe(*port, nil)
}
