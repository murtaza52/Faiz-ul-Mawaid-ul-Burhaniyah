(ns faiz.server
  (:require [noir.server :as server]
            [noir.cljs.core :as cljs]
            [noir.fetch.remotes :as remote]
            [ring.middleware.gzip :as gzip]
            [faiz.views.main :as main]
            [faiz.views.user :as user]
            [faiz.views.common :as cm]))

(server/load-views-ns 'faiz.views)

(server/add-middleware remote/wrap-remotes)

(server/add-middleware gzip/wrap-gzip)

(def cljs-options {:advanced {:externs ["externs/jquery.js"]}})

(def handler (server/gen-handler {:mode :dev :ns 'faiz}))

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "9051"))]
    (cljs/start mode cljs-options)
    (server/start port {:mode mode :ns 'faiz})))