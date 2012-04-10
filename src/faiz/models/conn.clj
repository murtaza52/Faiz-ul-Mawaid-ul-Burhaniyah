(ns faiz.models.conn
  (:require [monger core util])
  (:import [com.mongodb WriteConcern])
  (:use somnium.congomongo))

(def connected (atom false))
(defn connected?
  []
  @connected)

(comment
  (defn connect!
  [{:keys [host port db]}]
  (when-not (connected?)
    (do
      (monger.core/connect! {:host host :port port})
      (monger.core/set-db! (monger.core/get-db db))
      (monger.core/set-default-write-concern! WriteConcern/SAFE)
      (reset! connected true))))

(defn- authenticate!
  [{:keys [db uname pwd]}]
  (when (connected?)
    (monger.core/authenticate db uname (.toCharArray pwd))))
)

(defn- connect!
  [{:keys [db host port]}]
  (when-not (connected?)
    (let [conn (make-connection db :host host :port port)]
      (set-connection! conn)
      (reset! connected conn))))

(defn- authenticate!
  [{:keys [uname pwd]}]
  (when-let [conn (connected?)]
    (authenticate conn uname pwd)))

(defn connect
  [params]
  (do
    (connect! params)
    (authenticate! params)))
