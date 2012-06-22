(ns faiz.client.info-collection
  (:require [crate.core :as crate]
            [faiz.client.controls :as controls]
            [faiz.client.options :as opt]
            [faiz.client.core :as core]
            [jayq.core :as jq]
            [jayq.util :as ju]
            [faiz.client.common :as cm])
  (:use [waltz.state :only [transition]])
  (:use-macros [crate.def-macros :only [defpartial]]
               [waltz.macros :only [in out defstate deftrans]]))

(comment (def ))


(def elements {:input-boxes [{:name ":person/first-name" :label-text "Full Name" :help-text "Please enter your full name as - First Middle Last"}
                              {:name ":person/ejamaat" :label-text "Ejamaat Number" :placeholder-text "Ejamaat #"}
                              {:name ":person/mobile" :label-text "Mobile Number" :placeholder-text "Mobile #"}
                              {:name ":person/email" :label-text "Email Address"}
                              {:name ":person/watan" :label-text "Watan" :placeholder-text "Watan"}]
               :links [{:text "Information Collection" :uri "#"}
                        {:text "Admin" :uri "#"}]
               :form-legend "Please Enter your Information"
               :textboxes [{:name ":person/address" :label-text "Address" :help-text "Please enter your full residential address, with landmarks."}]
               :buttons [{:text "Save changes" :class "btn btn-primary" :action "save-info" :id "save-info-form"}
                         {:text "Cancel" :class "btn"}]
               :thaali-options {:name ":person/thaali-status" :label-text "Faiz ul Mawaid ul Burhaniyah Thaali Status"
                                 :options
                                 [{:value ":person.thaali-status/current" :text "I currently receive the barakaat of Faiz ul Mawaid ul Burhaniyah thaali."}
                                  {:value ":person.thaali-status/applied" :text "I have already applied for the thaali."}
                                  {:value ":person.thaali-status/want" :text "I would like to start receiving the thaali."}
                                  {:value ":person.thaali-status/stopped" :text "I was receiving the thaali before but am not receiving it currently."}
                                  {:value ":person.thaali-status/unable" :text "I would like to, however its not possible for me to avail the barakaat as of now."}]}
               :gender-options {:name ":person/gender" :label-text "Gender" :class "inline"
                                 :options
                                 [{:value "male" :text "Male"}
                                  {:value "female" :text "Female"}]}
               :list-area {:name ":person/res-area" :label-text "Area of Residence" :options (sort opt/areas)}
               :list-college {:name ":person/college" :label-text "College" :options (sort opt/colleges)}
               :list-course {:name ":person/course" :label-text "Course" :options (sort opt/courses)}
               })

(def ids [:person/course :person/college :person/resarea :person/gender :person/thaali-status :person/address :person/watan :person/first-name :person/email :person/mobile :person/ejamaat]) 

(comment (defn get-values []
  (map #(fn [id]
          (jq/val))
       ids)

  (defn save []
  (transition core/app :create-new-user (get-values)))
))


(defn create-info-collection-page
  []
  (jq/append cm/$main-add (controls/form
                     "info-collection"
                     (:form-legend elements) 
                     (map #(controls/button %) (:buttons elements))
                     (map #(controls/text-input %) (:input-boxes elements))
                     (controls/select-list (:list-area elements))
                     (map #(controls/textarea %) (:textboxes elements)) 
                     (controls/select-list (:list-college elements))
                     (controls/select-list (:list-course elements))
                     (controls/radio-buttons (:thaali-options elements))
                     (controls/radio-buttons (:gender-options elements)))))



