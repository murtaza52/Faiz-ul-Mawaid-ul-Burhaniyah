(ns faiz.client.main
  (:require [clojure.browser.repl :as repl]
            [faiz.client.transforms :as tr]
            [faiz.client.reg :as reg]))

;;************************************************
;; Dev stuff
;;************************************************

(set! (.-onload js/window)
      #(do
         (repl/connect "http://localhost:9000/repl")
         (int))) 

(tr/init)

(reg/render)


;(set! (.onload js/window) #(tr/init "Faiz-ul-Mawaid-il-Burhaniyah - Poona Students" links))


;(state/set core/app :student-info)

;(do (trigger core/app :to-student-info) )

;(cm/rm-content)
;(-> info/get-content cm/add-content)


