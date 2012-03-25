(ns faiz.server
  (:require [noir.server :as server]
            [noir.cljs.core :as cljs]))

(server/load-views-ns 'faiz.views)
(def cljs-options {:advanced {:externs ["externs/jquery.js"]}})

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "9052"))]
    (cljs/start mode cljs-options)
    (server/start port {:mode mode
                        :ns 'faiz})))
