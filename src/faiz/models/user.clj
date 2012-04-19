(ns faiz.models.user
  (:require [monger.collection :as mc]
            [faiz.models.conn :as conn]
            [monger.query :as q])
  (:use [monger.operators]))

(comment (defn connection-string {:host "staff.mongohq.com"
                         :port 10079
                         :db "faiz"
                         :uname "admin"
                                  :pwd "admin123"})
         (q/with-collection "student-data"
           (q/limit 1))
         
         (mc/find-maps "student-data"))

(def connection-string "mongodb://admin:admin123@staff.mongohq.com:10079/faiz")

(def user-coll "test-users")

(conn/connect! connection-string)

(defn new-doc
  [coll doc]
  (mc/insert coll doc))

(defn find-doc
  [coll doc]
  (mc/find-maps coll doc))

(defn find-user
  [doc]
  (mc/find-maps user-coll doc))

(defmulti get-user #(count %))

(defmethod get-user 1 [kvs]
  (find-user {(first (keys kvs)) (first (vals kvs))}))

(defmethod get-user :default [kvs]
  (find-user {:ejamaat (:ejamaat kvs)}))

(defn new-user
  [user]
  (if-let [u (seq (get-user user))]
    {:status false :reason :user-exists :user u}
    (let [resp (mc/insert user-coll user)]
      {:status true})))


(comment
(new-user {:ejamaat 20341288 :name "Mustafa Bhojawala"})
(mc/find-maps user-coll {:ejamaat "1222"})
)


















;mongodb://<user>:<password>@staff.mongohq.com:10085/fmb

 

;; localhost, default port
;(monger.core/connect!)

;; given host, given port
;
;(monger.core/connect! { :host "staff.mongohq.com" :port 10085})

;(monger.core/authenticate "faiz" "fmb" (.toCharArray "786110"))

;(monger.core/set-db! (monger.core/get-db "fmb"))

;(mc/find-maps "student-data")

;mongodb://<user>:<password>@staff.mongohq.com:10085/fmb