(defproject faiz "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://exampl.com/FIXME"
  :plugins [[lein-difftest "1.3.7"]
                  [lein-marginalia "0.7.0"]
                  [lein-pprint "1.1.1"]
                  [lein-swank "1.4.4"]
                  [lein-ring "0.6.4"]]
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [noir-cljs "0.3.0"]
                 [jayq "0.1.0-alpha1"]
                 [fetch "0.1.0-alpha2"]
                 [crate "0.1.0-alpha3"]
                 [noir "1.3.0-beta2"] 
                 [com.novemberain/monger "1.0.0-SNAPSHOT"]
                 [waltz "0.1.0-SNAPSHOT"]
                 [com.novemberain/monger "1.0.0-beta4"]
                 [ibdknox/ring-gzip-middleware "0.1.1"]
                 [midje "1.3.2-SNAPSHOT"]
                 [dieter "0.2.0"]]
  :cljsbuild {:builds [{}]}
  :main ^{:skip-aot true} faiz.server
  ;:min-lein-version "2.0.0"
  :ring {:handler faiz.server/handler})
