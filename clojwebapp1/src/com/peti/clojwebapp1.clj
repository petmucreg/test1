; (run-server { :port 8800 } "/*"
;  (servlet petiapp))

(ns com.peti.clojwebapp1
    (:gen-class)
    (use compojure)
    (use clojure.core))

(def blogs (atom [{:subject "subject" :body "body"}]))

(defn blog-entry-form [params]
  (html
    [:html
        [:body
            (form-to [:post "/blog-entry-list"]
              [:p
                (text-field {:cols 60} "subject")]
              [:p
                (text-area {:rows 20 :cols 80} "body")]
              [:br]
              [:br]
              (submit-button "Save"))]]))

(defn create-subject [subject]
    (vector :p subject))

(defn create-body [body]
    (vector :p body))

(defn create-article [aMap]
    (html (vector :p
                  (create-subject (:subject aMap))
                  (create-body (:body aMap)))))

(defn blog-entry-list [params]
  (do
    (reset! blogs (cons params @blogs))
    [:html
     [:body
        (map #(create-article %) @blogs)]]))

(defroutes petiapp
  (ANY "/blog-entry-form" (blog-entry-form params))
  (ANY "/blog-entry-list" (blog-entry-list params))
  (ANY "*" (page-not-found)))

