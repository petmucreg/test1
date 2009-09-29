;; (run-server { :port 8800 } "/*"
;;  (servlet petiapp))

(ns com.peti.clojwebapp1
    (:gen-class)
    (use compojure)
    (use clojure.core))

(defn blog-entry-form [params]
  (html
    (form-to [:post "/blog-entry-form-handler"]
      (text-area {:rows 20 :cols 73} "area1")
      [:br]
      (submit-button "Save"))))

(defn blog-entry-success [params]
  (html
    (text-area "Entry Saved")))

(defn blog-entry-form-handler [params]
  (if (nil? (get params :area1))
        (blog-entry-form params)
        (blog-entry-success params)))

(defroutes petiapp
  (GET "/blog-entry-form" (blog-entry-form params))
  (POST "/blog-entry-form-handler" (blog-entry-form-handler params))
  (ANY "*" (page-not-found)))

