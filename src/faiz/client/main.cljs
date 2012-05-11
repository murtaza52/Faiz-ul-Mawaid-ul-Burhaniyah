(ns faiz.client.main
  (:require [noir.cljs.client.watcher :as watcher]
            [clojure.browser.repl :as repl]
            [crate.core :as crate]
            [faiz.client.controls :as controls]
            [waltz.state :as state]
            [fetch.lazy-store :as store]
            [fetch.remotes :as remotes]
            [faiz.client.options :as opt]
            [faiz.client.core :as core])
  (:use [jayq.core :only [$ append bind data delegate find show hide remove]]
        [waltz.state :only [transition]])
  (:use-macros [crate.def-macros :only [defpartial]]
               [waltz.macros :only [in out defstate deftrans]])
  (:require-macros [fetch.macros :as fm]))

;;************************************************
;; Dev stuff
;;************************************************

(watcher/init)
(repl/connect "http://localhost:9000/repl")

(state/set core/app :student-info)

