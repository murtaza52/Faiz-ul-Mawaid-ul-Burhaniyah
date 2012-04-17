(ns faiz.client.msg
  (:require [crate.core :as crate]
            [faiz.client.controls :as cs]
            [jayq.core :as jq]))

(def $main-add (jq/$ "div.container div#content"))

(defn create [msg]
  (cs/hero-unit {:p msg}))

(defn init [msg]
  (jq/append $main-add (create msg)))




