(use 'compojure)
(defroutes petiapp
  (GET "/hello" (buildform params))
  (ANY "*" (page-not-found)))

;; (run-server { :port 8800 } "/*"
;;  (servlet petiapp))

(defn buildform [params]
  (str params))