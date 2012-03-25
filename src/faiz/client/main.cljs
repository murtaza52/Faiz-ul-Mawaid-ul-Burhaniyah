(ns faiz.client.main
  (:require [noir.cljs.client.watcher :as watcher]
            [clojure.browser.repl :as repl]
            [crate.core :as crate]
            [example.client.controls :as controls]
            [waltz.state :as state]
            [fetch.lazy-store :as store]
            [fetch.remotes :as remotes])
  (:use [jayq.core :only [$ append bind data delegate find show hide inner add-class remove-class]]
        [waltz.state :only [transition]])
  (:use-macros [crate.macros :only [defpartial]]
               [waltz.macros :only [in out defstate deftrans]])
  (:require-macros [fetch.macros :as fm]))
  (:require [noir.cljs.client.watcher :as watcher]
            [clojure.browser.repl :as repl]
            [crate.core :as crate])
  (:use [jayq.core :only [$ append]])
  (:use-macros [crate.macros :only [defpartial]]))

;;************************************************
;; Dev stuff
;;************************************************

(watcher/init)
;;(repl/connect "http://localhost:9000/repl")

;;************************************************
;; Code
;;************************************************

(def $content ($ :#content))

(defpartial up-and-running []
  [:p.alert "CLJS is compiled and active... Time to build something!"])

(append $content (up-and-running))

;;util functions
(defn appends [$elem content partial]
  (doseq [c content] (append $elem (partial c))))

;;configurable data
(def brand "Faiz ul Mawaid ul Burhaniyah")
(def form-legend "Please Enter your Information")

;;selectors
(def $topbar-nav ($ "div.navbar.navbar-fixed-top ul.nav"))
(def $topbar-brand ($ "div.navbar.navbar-fixed-top a.brand"))
(def $nav-links ($ "div.navbar.navbar-fixed-top ul.nav a"))
(def $main ($ "section#main div.row div.span8"))

;;seq for creating elements
(def links [{:text "Information Collection" :uri "#" :action "info"}
            {:text "Faiz Registeration" :uri "#" :action "reg"}])

(def inputs [{:name "ejamaat" :label-text "Ejamaat Number" :placeholder-text "Ejamaat #"}
             {:name "mobile" :label-text "Mobile Number" :placeholder-text "Mobile #"}
             {:name "email" :label-text "Email Address"}
             {:name "mobile" :label-text "Mobile Number" :placeholder-text "Mobile #"}])

(def buttons [{:text "Save changes" :class "btn btn-primary"}
              {:text "Cancel" :class "btn"}])

;;DOM Creation
(append $topbar-brand brand)
(appends $topbar-nav links controls/link)
(append $main (controls/form
               form-legend
               (map #(controls/text-input %) inputs)
               (map #(controls/button %) buttons))) 


;;Event Binding - defmulti and delegate
(defmulti actions #(keyword %))

(defn fire-action [e]
 (.preventDefault e)
 (this-as me
   (let [$me ($ me)
         action (data $me :action)]
     (actions action))))

(defn ^:export init-page []
 (.delegate ($ "body") "div.navbar.navbar-fixed-top ul.nav a" "click" fire-action))
(init-page)


;Event Binding - defmetohds

(defmethod actions :info [_]
  (fm/letrem [a (adder 3 4)
              b (adder 5 6)]
             (js/alert (str "a: " a " b: " b))))

(defmethod actions :reg [_] (js/alert "Hello defmethod reg"))
