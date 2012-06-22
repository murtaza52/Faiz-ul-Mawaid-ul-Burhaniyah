(ns faiz.client.common
  (:require [jayq.core :as jq]))

(defn rm-content []
  (js/alert "hi")
  (jq/remove (jq/$ "div.container section#main")))

(defn $main-add [] (jq/$ "div.container div#content"))

(defn add-content [c]
  (jq/append ($main-add) c))
