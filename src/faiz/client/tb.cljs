(ns faiz.client.tb
  (:require [crate.core :as crate]
            [faiz.client.controls :as cs]
            [jayq.core :as jq]
            [faiz.client.bindings :as bd]))

(def $topbar-nav (jq/$ "div.navbar.navbar-fixed-top ul.nav.pull-left"))
(def $topbar-brand (jq/$ "div.navbar.navbar-fixed-top a.brand"))
(def $nav-links (jq/$ "div.navbar.navbar-fixed-top ul.nav a"))

(def links [{:text "Office Screen" :uri "#" :action "to-office-screen"}
            {:text "Admin" :uri "#"}
            {}])

(defn create
  [ls]
  (doseq [l ls] (cs/link l)))

(def ls (create links))

(defn init
  []
  (jq/append $topbar-nav ls))

(defmethod bd/actions :to-office-screen []
  (transition core/app :to-office-screen))
