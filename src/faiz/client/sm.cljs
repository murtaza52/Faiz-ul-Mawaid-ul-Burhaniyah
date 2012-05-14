(ns faiz.client.sm
  (:require [waltz.state :as state]
            [fetch.remotes :as remotes]
            [faiz.client.infoCollection :as info]
            [faiz.client.msg :as msg]
            [faiz.client.officeScreen :as os]
            [faiz.client.tb :as tb])
  (:use-macros [waltz.macros :only [in out defstate deftrans]])
  (:use [waltz.state :only [transition]]
        [faiz.client.core :only [app]])
  (:require-macros [fetch.macros :as fm]))

(defstate app :student-info
  (in [] (tb/init))
  (in [] (info/main-remove))
  (in [] (info/create-info-collection-page)))

(defstate app :message-page
  (in [msg](info/main-remove)
      (msg/init msg)))

(defstate app :office-screen
  (in [] (os/init)))

(deftrans app :to-office-screen
  (state/set app :office-screen))

(deftrans app :user-created-successfully []
  (state/set app :message-page
             "You have successfully submitted your information. Thanks for submitting your information to Poona Jamaat (Anjuman-e-Taheri)"))

(deftrans app :user-created-error []
  (state/set app :message-page "Information for the submitted ejamaat already exists. Thanks for visiting us again."))

(deftrans app :create-new-user [user]
  (fm/letrem [resp (new-user user)]
             (if (:status resp)
               (transition app :user-created-successfully)
               (transition app :user-created-error))))


