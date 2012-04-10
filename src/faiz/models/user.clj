(ns faiz.models.user
  (:require [monger.collection :as mc]
            [faiz.models.conn :as conn])
  (:use [monger.operators])
  (:use somnium.congomongo))

(defn connection-string {:host "staff.mongohq.com"
                         :port 10079
                         :db "faiz"
                         :uname "admin"
                         :pwd "admin123"})

(conn/connect connection-string)

(fetch :student-data :limit 1)













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