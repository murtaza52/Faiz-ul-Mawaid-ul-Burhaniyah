(ns faiz.client.utils)

(def state (atom {:debug true}))

;;Inspired by Chris Ganger's debug-log from waltz
(defn log [msg data]
  (when (and js/console
             (@state :debug))
    (let [d (if (string? data)
              data
              (pr-str data))
          s (str msg " :: " d)]
      (.log js/console s))))


;;establish the clojurescript repl.
(when (and js/console (@state :debug))
  (set! (.-onload js/window)
      #(do
         (repl/connect "http://localhost:9000/repl")))) 