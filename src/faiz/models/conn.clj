(ns faiz.models.conn
  (:require [monger core util])
  (:import [com.mongodb WriteConcern]))

(def connected (atom false))
(defn connected?
  []
  @connected)

(defn connect!
  [uri]
  (when-not (connected?)
    (do
      (monger.core/connect-via-uri! uri)
      (monger.core/set-default-write-concern! WriteConcern/SAFE)
      (reset! connected true))))

(comment

  ;congomongo connection and authentication
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

(defn- authenticate!
  "Monger authentication with password"
  [{:keys [db uname pwd]}]
  (when (connected?)
    (monger.core/authenticate db uname (.toCharArray pwd))))
)


