(ns faiz.client.reg
  (:require [crate.core :as crate]
            [cljsbinding :as binding]
            [faiz.client.transforms :as tr]
            [faiz.client.controls :as ctrls]
            [enfocus.core :as ef])
  (:use-macros [crate.def-macros :only [defhtml]]
               [enfocus.macros :only [append defsnippet content at set-attr html-content deftemplate]]))

(defn hello []
  (js/alert "hi"))

(defn render []
  (tr/layout (reg-form-2)))

(deftemplate reg-form-2 "/templates/reg-form.html" []
  ["#first"] (content "Hello World"))

(defhtml reg-form [] 
  [:div.form-horizontal
   [:fieldset
    [:legend "Student Registeration"]
    [:div.control-group
     [:label.control-label {:for "input01"} "Text Input"]
     [:div.controls
      [:input {:type "text" :class "input-xlarge" :id "input01"}]
      [:p]]]]])



