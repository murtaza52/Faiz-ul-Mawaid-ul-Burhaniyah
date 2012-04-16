(ns faiz.views.main
  (:require [faiz.views.common :as common])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        [noir.fetch.remotes :only [defremote]]))

(def title "Poona Jamaat - Anjuman e Taheri")

(defpage "/" []
         (common/layout title common/spinner))

