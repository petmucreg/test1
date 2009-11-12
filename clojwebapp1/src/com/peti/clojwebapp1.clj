(ns com.peti.clojwebapp1
    (:gen-class)
    (:require compojure)
    (use clojure.core))

(def blogs (atom []))

(defn create-article [aMap]
    [:div {:style "background-color:#A9B6B9;"}
     [:h3 (:subject aMap)]
     [:p (:body aMap)]])

(defn blogs-view [blogs]
  (compojure/html
    [:html
     [:body
      [:a {:href "/newblog"} "New Blog"]
      (map #(create-article %) blogs)]]))

(defn blogs-controller [params]
  (do
    (println "request params: " params " blogs: " @blogs)
    (if (seq params)
            (reset! blogs (cons params @blogs)))
    (blogs-view @blogs)))

(defn newblog-view [params]
  (compojure/html
    [:html
        [:body
            (compojure/form-to [:post "/blogs"]
              [:p
                (compojure/text-field {:cols 60} "subject")]
              [:p
                (compojure/text-area {:rows 20 :cols 80} "body")]
              [:br]
              [:br]
              (compojure/submit-button "Save"))]]))

(defn newblog-controller [params]
  (newblog-view params))

(defn start-page []
  (blogs-controller {}))

(compojure/defroutes petiapp
  (compojure/ANY "/newblog" (newblog-controller params))
  (compojure/ANY "/blogs" (blogs-controller params))
  (compojure/ANY "*" (start-page)))

(defn -main [args]
 (compojure/run-server { :port 8800 } "/*"
  (compojure/servlet petiapp)))

