
(ns com.peti.dataaccess
  (use clojure.contrib.sql))

(def petidb {:classname "com.mysql.jdbc.Driver"
         :subprotocol "mysql"
         :subname "//localhost:3306/peti"
         :user "peti"
         :password "Hello55"})

(defn read-blogs []
    (with-connection petidb
        (with-query-results res ["select * from blogs"]
            (doall res))))

(defn save-blog
  [blog]
    (with-connection petidb
        (insert-records "blogs" blog)))

