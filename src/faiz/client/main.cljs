(ns faiz.client.main
  (:require [noir.cljs.client.watcher :as watcher]
            [clojure.browser.repl :as repl]
            [crate.core :as crate]
            [faiz.client.controls :as controls]
            [waltz.state :as state]
            [fetch.lazy-store :as store]
            [fetch.remotes :as remotes])
  (:use [jayq.core :only [$ append bind data delegate find show hide remove]]
        [waltz.state :only [transition]])
  (:use-macros [crate.macros :only [defpartial]]
               [waltz.macros :only [in out defstate deftrans]])
  (:require-macros [fetch.macros :as fm]))

;;************************************************
;; Dev stuff
;;************************************************

(watcher/init)
;;(repl/connect "http://localhost:9000/repl")

;;************************************************
;; Code
;;************************************************

;;util functions
(defn appends [$elem content partial]
  (doseq [c content] (append $elem (partial c))))

;;configurable data
(def form-legend "Please Enter your Information")

selectors
(def $topbar-nav ($ "div.navbar.navbar-fixed-top ul.nav.pull-left"))
(def $topbar-brand ($ "div.navbar.navbar-fixed-top a.brand"))
(def $nav-links ($ "div.navbar.navbar-fixed-top ul.nav a"))
(def $main-add ($ "div.container div#content"))
(def $main-remove ($ "div.container section#main"))

;;seq for creating elements
(def links [{:text "Information Collection" :uri "#" :action "info"}
            {:text "Faiz Registeration" :uri "#" :action "reg"}])

(def inputs [{:name "name" :label-text "Full Name" :help-text "Please enter your full name as - First Middle Last"}
             {:name "ejamaat" :label-text "Ejamaat Number" :placeholder-text "Ejamaat #"}
             {:name "mobile" :label-text "Mobile Number" :placeholder-text "Mobile #"}
             {:name "email" :label-text "Email Address"}
             {:name "watan" :label-text "Watan" :placeholder-text "Watan"}])

(def buttons [{:text "Save changes" :class "btn btn-primary"}
              {:text "Cancel" :class "btn"}])

(def thaali-options {:name "thaali" :label-text "Faiz ul Mawaid ul Burhaniyah Thaali Status"
                     :options
                     [{:value "thaali-yes" :text "I currently receive the barakaat of Faiz ul Mawaid ul Burhaniyah thaali."}
                      {:value "thaali-subscribe" :text "I would you like to receive the barakaat of Faiz ul Mawaid ul Burhaniyah thaali."}
                      {:value "thaali-problem" :text "Its currently not possible for me to avail the barakaat of Faiz ul Mawaid ul Burhaniyah thaali."}]})

(def gender-options {:name "gender" :label-text "Gender" :class "inline"
                     :options
                     [{:value "male" :text "Male"}
                      {:value "female" :text "Female"}]})

(def list-area {:name "area" :label-text "Area of Residence in Pune" :options (sort ["Burhani Colony / Market Yard" "Fatima Nagar / Wanawadi" "Kondhwa" "Mithanagar" "Camp" "Bhawani Peth" "City" "Nigdi" "Kasarwadi" "Pimpri-Chinchwad" "Kothrud" "Shivajinagar" "Deccan" "Aundh" "Swargate" "Kalyani Nagar" "Viman Nagar" "Baner" "Wakad"])})

(def list-college {:name "college" :label-text "College" :options (sort ["D.Y.Patil College" "Allana Institute of Management and Science" "Fergusson College" "ILS Law College" "Modern College of Arts, Science & Commerce" "Ness Wadia College of Commerce" "Nowrosjee Wadia College" "Poona College of Arts Science & Commerce" "Symbiosis" "Sinhgad College"])})

(def list-course {:name "course" :label-text "Course" :options (sort ["Commerce" "Engineering" "Law" "Medicine" "CA" "Computer Science" "Architecture"])})


;;state
(def state (atom {:state :init}))

;;DOM Creation
;;(defn controller []
 ;)

(appends $topbar-nav links controls/link)
(remove $main-remove)
(append $main-add (controls/form
               form-legend
               (map #(controls/button %) buttons)
               (map #(controls/text-input %) inputs)
               (controls/select-list list-area)
               (controls/select-list list-college)
               (controls/select-list list-course)
               (controls/radio-buttons thaali-options)
               (controls/radio-buttons gender-options)
               ))



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

