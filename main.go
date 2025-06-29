package main

import (
	"flag"
	"net/http"
)

func kugelblitzHandler(w http.ResponseWriter, r *http.Request) {

}

func hortenHandler(w http.ResponseWriter, r *http.Request) {

}

func main() {
	port := flag.String("port", ":8080", "Webservice port; default to 8080")
	flag.Parse()

	http.HandleFunc("/kugelblitz", kugelblitzHandler)
	http.HandleFunc("/horten", hortenHandler)

	http.ListenAndServe(*port, nil)
}
