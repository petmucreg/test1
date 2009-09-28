;; (run-server { :port 8800 } "/*"
;;  (servlet petiapp))

(ns com.peti.clojwebapp1
    (:gen-class)
    (use compojure)
    (use clojure.core))

(defn buildform [params]
  (str params))

(defroutes petiapp
  (GET "/hello" (buildform params))
  (ANY "*" (page-not-found)))

(defn -main []
  (print "loszar"))

