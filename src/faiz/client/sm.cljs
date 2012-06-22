(ns faiz.client.sm
  (:require [waltz.state :as state]
            [fetch.remotes :as remotes]
            [faiz.client.info-collection :as info]
            [faiz.client.msg :as msg]
            [faiz.client.office-screen :as os]
            [faiz.client.tb :as tb]
            [faiz.client.common :as cm])
  (:use-macros [waltz.macros :only [in out defstate defevent]])
  (:use [waltz.state :only [trigger]]
        [faiz.client.core :only [app]])
  (:require-macros [fetch.macros :as fm]))

(defstate app :student-info
  (in [] (cm/rm-content))
  (in [] (-> info/get-content cm/add-content)))

(defevent app :to-student-info []
  (state/set app :student-info))


(comment

  ;;(in [] (tb/init))
  
(defstate app :message-page
  (in [msg](info/main-remove)
      (msg/init msg)))

(defstate app :office-screen
  (in [] (os/init)))

(defevent app :to-office-screen
  (state/set app :office-screen))

(defevent app :user-created-successfully []
  (state/set app :message-page
             "You have successfully submitted your information. Thanks for submitting your information to Poona Jamaat (Anjuman-e-Taheri)"))

(defevent app :user-created-error []
  (state/set app :message-page "Information for the submitted ejamaat already exists. Thanks for visiting us again."))

(defevent app :create-new-user [user]
  (fm/letrem [resp (new-user user)]
             (if (:status resp)
               (trigger app :user-created-successfully)
               (trigger app :user-created-error))))

  )
