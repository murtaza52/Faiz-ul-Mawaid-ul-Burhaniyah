(ns faiz.views.main
  (:use [net.cgrand.enlive-html]
        [noir.core :only [defpage]]
        [noir.fetch.remotes :only [defremote]]))

(def params {:title  "Faiz-ul-Mawaid-il-Burhaniyah - Poona Students"})

(def links [{:url "#" :text "Home"} {:url "#" :text "Hisaab"} {:url "#" :text "Hello"}])

(def templ "faiz/templates/components.html")

(deftemplate index "faiz/templates/index.html" [p]
  [:title] (content (:title p)))

(defsnippet nav-link templ [:top-navbar (attr= :data-navlinks "left") :> first-child]
  [{:keys [url text]}]
  [:a] (set-attr :href url)
  [:a] (content text))

(nav-link (first links))

(defsnippet top-navbar templ [:top-navbar]
  [links]
  [[:ul (attr= :data-navlinks "left")]] (content (map nav-link links)))

(defn to->html [n]
  (apply str (emit* n)))

(defremote get-navbar []
  (to->html (top-navbar links)))

(defpage "/" []
  (index params))

(comment
  (def templ (html-resource "faiz/templates/components.html"))
  (select templ [:top-navbar  [:ul (attr= :data-navlinks "left")] first-child]))