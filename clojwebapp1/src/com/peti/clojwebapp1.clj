(ns com.peti.clojwebapp1
    (:gen-class)
    (use compojure)
    (use clojure.core))

(def blogs (atom []))

(defn create-article [aMap]
    [:div {:style "background-color:#A9B6B9;"}
     [:h3 (:subject aMap)]
     [:p (:body aMap)]])

(defn blogs-view [blogs]
  (html
    [:html
     [:body
      [:a {:href "/newblog"} "New Blog"]
      (map #(create-article %) blogs)]]))

(defn blogs-controller [params]
  (do
    (println "request params: " params " blogs: " @blogs)
    (if (and (not (nil? (:subject params)))
              (not (nil? (:body params))))
            (reset! blogs (cons params @blogs)))
    (blogs-view @blogs)))

(defn newblog-view [params]
  (html
    [:html
        [:body
            (form-to [:post "/blogs"]
              [:p
                (text-field {:cols 60} "subject")]
              [:p
                (text-area {:rows 20 :cols 80} "body")]
              [:br]
              [:br]
              (submit-button "Save"))]]))

(defn newblog-controller [params]
  (newblog-view params))

(defn start-page []
  (blogs-controller {}))

(defroutes petiapp
  (ANY "/newblog" (newblog-controller params))
  (ANY "/blogs" (blogs-controller params))
  (ANY "*" (start-page)))

(defn -main [args]
 (run-server { :port 8800 } "/*"
  (servlet petiapp)))

