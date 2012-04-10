(ns example.client.app-sm
  (:require [waltz.state :as state]
            [fetch.remotes :as remotes]
            [faiz.client.info_collection :as info]
            [faiz.client.bindings :as bd])
  (:use-macros [waltz.macros :only [in out defstate deftrans]])
  (:use [waltz.state :only [transition]]
        [faiz.client.core :only [app]])
  (:require-macros [fetch.macros :as fm]))

(defstate app :student-info
  (in [] (info/create-info-collection-page))
  (in [] (bd/init-page))
  (out [] (cm/$main-remove)))
