;; (run-server { :port 8800 } "/*"
;;  (servlet petiapp))

(ns com.peti.clojwebapp1
    (:gen-class)
    (use compojure)
    (use clojure.core))

(defn blog-entry-form [params]
  (html
    (form-to [:post "/blog-entry-form-handler"]
      [:p
        (text-field {:cols 60} "blogEntrySubject")]
      [:p
        (text-area {:rows 20 :cols 80} "blogEntryBody")]
      [:br]
      (submit-button "Save"))))

(defn blog-entry-success [params]
  (html
    [:head
        [:title "Entry Saved"]]
    [:body
        [:p "Entry Saved"]
        [:p (text-area "areax")]]))

(defn blog-entry-form-handler [params]
  (if (nil? (get params :area1))
        (blog-entry-form params)
        (blog-entry-success params)))

(defroutes petiapp
  (GET "/blog-entry-form" (blog-entry-form params))
  (POST "/blog-entry-form-handler" (blog-entry-form-handler params))
  (ANY "*" (page-not-found)))

