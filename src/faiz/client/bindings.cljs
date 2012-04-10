(ns faiz.client.bindings
  (:require [jayq.core :as jq]))

(defn get-body
  [_]
  (jq/$ "body"))

(defn attr-sel
  [attr val]
  (str "[" attr "='" val "']"))

(defn event-sel
  [event]
  (attr-sel "data-action-type" (name event)))

(defn by-attr [attr val]
  "Returns a collection of jquery elements. Selects using the attribute attr = value"
  (jq/$ (attr-sel attr val)))

(defn by-event
  "Returns a collection of jquery elements. Selects using the attribute for a given event type"
  [event]
  (jq/$ (event-sel event)))

;;Event Binding - defmulti and delegate
(defmulti actions #(keyword %))

(defmethod actions :change [_]
  (js/alert "Hi I was changed"))

(defmethod actions :click [_]
  (js/alert "Hi I was clicked"))

(defn fire-action [e]
 (.preventDefault e)
 (this-as me
   (let [$me (jq/$ me)
         action (jq/data $me :action)]
     (actions action))))

(defn set-delegate
  [event]
  (let [sel (event-sel event) ev (name event)]
    (.delegate (get-body) sel ev fire-action)))

(defn set-delegates
  []
  (map #(set-delegate %) [:click :change]))

(defn init-page []
  (set-delegates))

(init-page)




