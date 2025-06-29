package main

import (
	"flag"
	"fmt"
	"net/http"
)

func healthCheckHandler(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(http.StatusOK)
	fmt.Fprintf(w, "OK")
}

func kugelblitzHandler(w http.ResponseWriter, r *http.Request) {
}

func hortenHandler(w http.ResponseWriter, r *http.Request) {
}

func main() {
	port := flag.String("port", ":8080", "Webservice port; default to 8080")
	flag.Parse()

	http.HandleFunc("/health", healthCheckHandler)

	http.HandleFunc("/kugelblitz", kugelblitzHandler)
	http.HandleFunc("/horten", hortenHandler)

	http.ListenAndServe(*port, nil)
}
