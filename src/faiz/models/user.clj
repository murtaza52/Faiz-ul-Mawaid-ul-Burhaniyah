(ns faiz.models.user
  (:require [monger.core])
  (:require [monger.collection :as mc])
  (:use     [monger.operators]))

;mongodb://<user>:<password>@staff.mongohq.com:10085/fmb

 

;; localhost, default port
;(monger.core/connect!)

;; given host, given port
;
(monger.core/connect! { :host "staff.mongohq.com" :port 10085})

(monger.core/authenticate "faiz" "fmb" (.toCharArray "786110"))

(monger.core/set-db! (monger.core/get-db "fmb"))

(mc/find-maps "student-data")

;mongodb://<user>:<password>@staff.mongohq.com:10085/fmb