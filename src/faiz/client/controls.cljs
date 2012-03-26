(ns faiz.client.controls
  (:require [crate.core :as crate])
  (:use-macros [crate.macros :only [defpartial]]))

(defpartial link [{:keys [text uri action]}]
  [:li [:a {:href uri :data-action action :id action} text]])

(defpartial button [{:keys [text class]}]
  [:button {:class class} text])

(defpartial form [legend-text buttons & controls]
  [:div.form-horizontal
   [:fieldset [:legend legend-text]
    (flatten controls)
    [:div.form-actions
    buttons]]])

(defn chrome [{:keys [name label-text controls]}]
  [:div.control-group
   [:label.control-label {:for name} label-text]
   [:div.controls controls]])

(defpartial text-input
  [{:keys [name label-text placeholder-text input-class help-text] :or {input-class "input-xlarge" help-text nil}}]
  (let [placeholder-text (if placeholder-text placeholder-text label-text)]
  [:div.control-group
   [:label.control-label {:for name} label-text]
   [:div.controls
    [:input {:type "text" :class input-class :id name :placeholder placeholder-text}]
    (if help-text [:p.help-block help-text] nil)]]))

(defpartial select-list [{:keys [name label-text options]}]
  [:div.control-group
   [:label.control-label {:for name} label-text]
   [:div.controls
    [:select {:id name}
     (for [o options] [:option o])]]])

;(defmacro defchrome [])



(defn selection [{:keys [name value text type class]}]
  [:label {:class (str type " " class)}
   [:input {:id value :type type :name name :value value}] text])

(defpartial checkboxes [{:keys [name label-text options class]}]
  [:div.control-group
   [:label.control-label {:for "name"} label-text]
   [:div.controls (for [o options] (selection (merge o {:name name :type "checkbox" :class class})))]])

(defpartial radio-buttons [{:keys [name label-text options class]}]
  [:div.control-group
   [:label.control-label {:for "name"} label-text]
   [:div.controls (for [o options] (selection (merge o {:name name :type "radio" :class class})))]])