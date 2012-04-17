(ns faiz.client.office_screen
  (:require [crate.core :as crate]
            [faiz.client.controls :as controls]
            [faiz.client.options :as opt]
            [faiz.client.bindings :as bd]
            [faiz.client.core :as core])
  (:use [jayq.core :only [$ append bind data delegate find show hide remove]]
        [waltz.state :only [transition]])
  (:use-macros [crate.macros :only [defpartial]]
               [waltz.macros :only [in out defstate deftrans]]))

(def $main-add ($ "div.container div#content"))

(def form-legend "Please Enter Information from the form")

(def inputs [{:name "name" :label-text "Full Name" :help-text "Please enter your full name as - First Middle Last"}
             {:name "ejamaat" :label-text "Ejamaat Number" :placeholder-text "Ejamaat #"}
             {:name "mobile" :label-text "Mobile Number" :placeholder-text "Mobile #"}
             ])

(def textboxes [{:name "address" :label-text "Address" :help-text "Please enter your full residential address, with landmarks."}])

(def buttons [{:text "Save changes" :class "btn btn-primary" :action "save-info" :id "save-info-form"}
              {:text "Cancel" :class "btn"}])

(def list-area {:name "area" :label-text "Area of Residence" :options (sort opt/areas)})

(defmethod bd/actions :save-info [{:keys [group-id]}]
  (transition core/app :create-new-user (bd/get-fields group-id)))

(defn init
  []
  (append $main-add (controls/form
                     "Existing Form Entry"
                     form-legend
                     (map #(controls/button %) buttons)
                     (map #(controls/text-input %) inputs)
                     (controls/select-list list-area)
                     (map #(controls/textarea %) textboxes))))
