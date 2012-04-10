(ns faiz.client.core
  (:require [waltz.state :as state])
  (:use [waltz.state :only [transition]])
  (:use-macros [waltz.macros :only [in out defstate deftrans]]))

(def app (state/machine :app))
