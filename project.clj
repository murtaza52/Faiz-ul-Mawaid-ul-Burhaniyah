(defproject faiz "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://exampl.com/FIXME"
  :plugins [[lein-cljsbuild "0.1.3"]
            [lein-pprint "1.0.0"]
            [lein-swank "1.4.3"]]
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
                 [congomongo "0.1.9-SNAPSHOT"]
                 [midje "1.3.2-SNAPSHOT"]]
  :cljsbuild {:builds [{}]}
  :main ^{:skip-aot true} faiz.server)
