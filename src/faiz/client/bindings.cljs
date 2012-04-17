(ns faiz.client.bindings
  (:require [jayq.core :as jq]
            [faiz.client.core :as core]
            [waltz.state :as state]
            [clojure.string :as str]))

(def fields (atom nil))

(defn get-state
  []
  (first (state/current core/app)))

(defn get-fields
  [group-id]
  (let [state (keyword (get-state)) group (keyword group-id)]
    (-> @fields state group)))

(defn get-body
  [_]
  (jq/$ "body"))

(defn attr-sel
  ([attr] (str "[" attr "]"))
  ([attr val] (str "[" attr "='" val "']")))
  
(defn get-data-value
  [elem data-param]
  (jq/data elem data-param))

(defn event-sel
  [event]
  (attr-sel "data-action-type" (name event)))

(defn by-id
  [id]
  (jq/$ (str "#" id)))

(defn by-attr [attr val]
  "Returns a collection of jquery elements. Selects using the attribute attr = value"
  (jq/$ (attr-sel attr val)))

(defn by-event
  "Returns a collection of jquery elements. Selects using the attribute for a given event type"
  [event]
  (jq/$ (event-sel event)))

(defn get-closest
  [elem sel]
  (.closest elem sel))

(defn get-group
  "Returns a jquery element representing the closest parent having the data-form-id attr"
  [id]
  (let [elem (by-id id) sel (attr-sel "data-form-id")]
    (get-closest elem sel)))

(defn get-id
  "Returns the value of the id attribute for a given jquery object"
  [object]
  (jq/attr object "id"))

(defn get-val
  "Returns the value for a given jquery object"
  [object]
  (jq/val object))

;;Event Binding - defmulti and delegate
(defmulti actions (fn [{:keys [action]}] (keyword action)))

(defmethod actions :change [{:keys [id v group-id]}]
  (swap! fields #(let [group-id-k (keyword group-id) state-id (get-state) field-id (keyword id)]
                    (assoc-in % [state-id group-id-k field-id] v))))

(defn fire-action [e]
  (.preventDefault e)
  (this-as me
           (let [$me (jq/$ me)
                 action (jq/data $me :action-type)
                 action-click (if (= action "click") (jq/data $me :action) nil)
                 id (get-id $me) 
                 v (if-not (= action "click") (get-val $me) nil)
                 group-elem (get-group id)
                 group-id (get-data-value group-elem :form-id)]
             (actions {:action (or action-click action) :id id :v v :group-id group-id}))))

(defn set-delegate
  [event]
  (let [sel (event-sel event) ev (name event)]
    (.delegate (get-body) sel ev fire-action)))

(defn set-delegates
  []
  (doseq [ev [:click :change]] (set-delegate ev)))

(defn init-page []
  (set-delegates))








