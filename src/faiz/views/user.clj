(ns faiz.views.user
  (:require [faiz.models.user :as user])
  (:use [noir.fetch.remotes :only [defremote]]))

(defremote add-user [user]
  (user/new-user user))

(defremote get-user [map]
  (user/get-user map))

