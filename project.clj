;; This buffer is for notes you don't want to save, and for Lisp evaluation.
;; If you want to create a file, visit that file with C-x C-f,
;; then enter the text in that file's own buffer.

(defproject faiz "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://exampl.com/FIXME"
  :plugins [[lein-difftest "1.3.7"]
            [lein-marginalia "0.7.0"]
            [lein-pprint "1.1.1"]
            [lein-swank "1.4.4"]
            [lein-ring "0.6.4"]
            [clj-ns-browser "1.2.0"]]
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [noir-cljs "0.3.2" :exclusions [org.clojure/clojure]]
                 [jayq "0.1.0-alpha3"]
                 [fetch "0.1.0-alpha2"]
                 [crate "0.2.0-alpha2"]
                 [noir "1.3.0-beta4"] 
                 [com.novemberain/monger "1.0.0-beta4"]
                 [waltz "0.1.0-alpha1"]
                 [com.datomic/datomic "0.1.3065"]]
  :cljsbuild {:builds [{}]}
  :main ^{:skip-aot true} faiz.server
  :ring {:handler faiz.server/handler}
  :jvm-opts ["-Xms64m -Xmx256m"])
