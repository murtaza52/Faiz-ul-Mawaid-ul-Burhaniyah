(ns faiz.client.info_collection
  (:require [crate.core :as crate]
            [faiz.client.controls :as controls]
            [waltz.state :as state]
            [fetch.lazy-store :as store]
            [fetch.remotes :as remotes]
            [faiz.client.options :as opt]
            [faiz.client.bindings :as bd])
  (:use [jayq.core :only [$ append bind data delegate find show hide remove]]
        [waltz.state :only [transition]])
  (:use-macros [crate.macros :only [defpartial]]
               [waltz.macros :only [in out defstate deftrans]])
  (:require-macros [fetch.macros :as fm]))

;;util functions
(defn appends [$elem content partial]
  (doseq [c content] (append $elem (partial c))))

;;configurable data
(def form-legend "Please Enter your Information")

;selectors
(def $topbar-nav ($ "div.navbar.navbar-fixed-top ul.nav.pull-left"))
(def $topbar-brand ($ "div.navbar.navbar-fixed-top a.brand"))
(def $nav-links ($ "div.navbar.navbar-fixed-top ul.nav a"))
(def $main-add ($ "div.container div#content"))
(def $main-remove ($ "div.container section#main"))

;;seq for creating elements
(def links [{:text "Information Collection" :uri "#"}
            {:text "Admin" :uri "#"}])

(def inputs [{:name "name" :label-text "Full Name" :help-text "Please enter your full name as - First Middle Last"}
             {:name "ejamaat" :label-text "Ejamaat Number" :placeholder-text "Ejamaat #"}
             {:name "mobile" :label-text "Mobile Number" :placeholder-text "Mobile #"}
             {:name "email" :label-text "Email Address"}
             {:name "watan" :label-text "Watan" :placeholder-text "Watan"}])

(def textboxes [{:name "name" :label-text "Full Name" :help-text "Please enter your full name as - First Middle Last"}])

(def buttons [{:text "Save changes" :class "btn btn-primary"}
              {:text "Cancel" :class "btn"}])

(def thaali-options {:name "thaali" :label-text "Faiz ul Mawaid ul Burhaniyah Thaali Status"
                     :options
                     [{:value "thaali-yes" :text "I currently receive the barakaat of Faiz ul Mawaid ul Burhaniyah thaali."}
                      {:value "thaali-subscribe" :text "I would you like to start receiving the barakaat of Faiz ul Mawaid ul Burhaniyah thaali."}
                      {:value "thaali-problem" :text "I would like to, however its not possible for me to avail the barakaat as of now."}]})

(def gender-options {:name "gender" :label-text "Gender" :class "inline"
                     :options
                     [{:value "male" :text "Male"}
                      {:value "female" :text "Female"}]})

(def list-area {:name "area" :label-text "Area of Residence in Pune" :options (sort opt/areas)})

(def list-college {:name "college" :label-text "College" :options (sort opt/colleges)})

(def list-course {:name "course" :label-text "Course" :options (sort opt/courses)})

(defn create-info-collection-page
  []
  (appends $topbar-nav links controls/link)
  (remove $main-remove)
  (append $main-add (controls/form
                     form-legend
                     (map #(controls/button %) buttons)
                     (map #(controls/text-input %) inputs)
                     (map #(controls/textarea %) textboxes)
                     (controls/select-list list-area)
                     (controls/select-list list-college)
                     (controls/select-list list-course)
                     (controls/radio-buttons thaali-options)
                     (controls/radio-buttons gender-options))))


  

(bd/init-page)
