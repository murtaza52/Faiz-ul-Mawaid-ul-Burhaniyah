(ns faiz.server
  (:require [noir.server :as server]
            [noir.cljs.core :as cljs]
            [noir.fetch.remotes :as remote]
            [ring.middleware.gzip :as gzip]))

(server/load-views-ns 'faiz.views)

;(server/add-middleware remote/wrap-remotes)

;(server/add-middleware gzip/wrap-gzip)

(def options {:mode :dev :ns 'faiz})

(def cljs-options {:advanced {:externs ["externs/jquery.js"]}})

(def handler (server/gen-handler options))

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "9052"))]
    ;(cljs/start mode cljs-options)
    (server/start port options)))