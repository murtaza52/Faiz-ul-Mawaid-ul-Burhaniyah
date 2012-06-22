(ns faiz.client.transforms
  (:require [enfocus.core :as ef]
            [faiz.client.common :as cm])
  (:use-macros [enfocus.macros :only [append defsnippet content at set-attr html-content]]
               [fetch.macros :only [letrem]])
  (:require-macros [fetch.macros :as fm])
  (:use [faiz.client.utils :only [log]]))

(defn doc [] js/document) 

(defn init []
  (letrem [n (get-navbar)]
             (at (doc)
                 ["body > header"] (html-content n))))

(defn layout-j [c]
  (cm/add-content c))

(defn layout [c]
  "Takes html string as input and inserts it into the DOM"
  (log "Layout Content" c)
  (at (doc)
      [".cljs-main"] (content c)))