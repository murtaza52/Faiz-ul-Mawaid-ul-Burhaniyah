(ns faiz.client.info_collection
  (:require [crate.core :as crate]
            [faiz.client.controls :as controls]
            [faiz.client.options :as opt]
            [faiz.client.bindings :as bd]
            [faiz.client.core :as core])
  (:use [jayq.core :only [$ append bind data delegate find show hide remove]]
        [waltz.state :only [transition]])
  (:use-macros [crate.macros :only [defpartial]]
               [waltz.macros :only [in out defstate deftrans]]))

;;util functions
(defn appends [$elem content partial]
  (doseq [c content] (append $elem (partial c))))

;;configurable data
(def form-legend "Please Enter your Information")

;selectors
(def $main-add ($ "div.container div#content"))
(defn main-remove [] (remove ($ "div.container section#main")))

;;seq for creating elements
(def links [{:text "Information Collection" :uri "#"}
            {:text "Admin" :uri "#"}])

(def inputs [{:name "name" :label-text "Full Name" :help-text "Please enter your full name as - First Middle Last"}
             {:name "ejamaat" :label-text "Ejamaat Number" :placeholder-text "Ejamaat #"}
             {:name "mobile" :label-text "Mobile Number" :placeholder-text "Mobile #"}
             {:name "email" :label-text "Email Address"}
             {:name "watan" :label-text "Watan" :placeholder-text "Watan"}])

(def textboxes [{:name "address" :label-text "Address" :help-text "Please enter your full residential address, with landmarks."}])

(def buttons [{:text "Save changes" :class "btn btn-primary" :action "save-info" :id "save-info-form"}
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

(def list-area {:name "area" :label-text "Area of Residence" :options (sort opt/areas)})

(def list-college {:name "college" :label-text "College" :options (sort opt/colleges)})

(def list-course {:name "course" :label-text "Course" :options (sort opt/courses)})


(defmethod bd/actions :save-info [{:keys [group-id]}]
  (transition core/app :create-new-user (bd/get-fields group-id)))

(defn create-info-collection-page
  []
  (append $main-add (controls/form
                     "info-collection"
                     form-legend
                     (map #(controls/button %) buttons)
                     (map #(controls/text-input %) inputs)
                     (controls/select-list list-area)
                     (map #(controls/textarea %) textboxes)
                     (controls/select-list list-college)
                     (controls/select-list list-course)
                     (controls/radio-buttons thaali-options)
                     (controls/radio-buttons gender-options))))



