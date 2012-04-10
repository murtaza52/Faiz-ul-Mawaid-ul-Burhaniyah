(ns faiz.server
  (:require [noir.server :as server]
            [noir.cljs.core :as cljs]
            [noir.fetch.remotes :as remote]
            [ring.middleware.gzip :as gzip]))

(server/load-views-ns 'faiz.views)

(server/add-middleware remote/wrap-remotes)

(server/add-middleware gzip/wrap-gzip)

(def cljs-options {:advanced {:externs ["externs/jquery.js"]}})

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "9052"))]
    (cljs/start mode cljs-options)
    (server/start port {:mode mode
                        :ns 'faiz})))
