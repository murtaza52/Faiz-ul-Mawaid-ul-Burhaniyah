;; This buffer is for notes you don't want to save, and for Lisp evaluation.
;; If you want to create a file, visit that file with C-x C-f,
;; then enter the text in that file's own buffer.

(defproject faiz "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://exampl.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [jayq "0.1.0-alpha3"]
                 [fetch "0.1.0-alpha2"]
                 [crate "0.2.0-alpha3"]
                 [noir "1.3.0-beta7"] 
                 [com.novemberain/monger "1.0.0-beta4"]
                 [waltz "0.1.0-alpha1"]
                 [com.datomic/datomic "0.1.3065"]
                 [enfocus "0.9.1-SNAPSHOT"]
                 [enlive "1.0.1"]
                 [fluentsoftware/cljs-binding "1.0.0-SNAPSHOT"]
                 [shoreleave "0.2.2-SNAPSHOT"]
                 [shoreleave/shoreleave-remote-noir "0.2.2-SNAPSHOT"]
                 [ring-anti-forgery "0.1.3"]]
  :cljsbuild {:repl-listen-port 9000
              :builds [{:source-path "src/faiz/client"
                        :compiler {:output-to "resources/public/cljs/client.js"
                                   :optimizations :whitespace
                                   :pretty-print true}
                        }]}
  :main ^{:skip-aot true} faiz.server
  :ring {:handler faiz.server/handler}
  :jvm-opts ["-Xmx256m"])
