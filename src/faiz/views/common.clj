(ns faiz.views.common
  (:require [noir.cljs.core :as cljs])
  (:use [hiccup.def]
        [hiccup.page :only [include-css include-js html5]]
        [hiccup.element :only [javascript-tag]]))

(defelem navbar-top [title]
  [:div {:class "navbar navbar-fixed-top"}
   [:div.navbar-inner
    [:div.container
     [:a {:data-target ".nav-collapse" :data-toggle "collapse" :class "btn btn-navbar"}
      [:span.icon-bar]
      [:span.icon-bar]
      [:span.icon-bar]]
     [:a {:href "./index/html" :class "brand"} title]
     [:div.nav-collapse
      [:ul.nav.pull-left]
      [:ul.nav.pull-right]]]]])

(defelem spinner []
  [:div.row
   [:div.span5 "&nbsp;"]
   [:div.span3 [:img {:src "/img/ajax-loader.gif"}]]])

(def on-ready
  (javascript-tag   "$(document).ready(function() {
     faiz.client.bindings.init_page();
 });")
)

(defhtml layout [title & content]
  (html5
   [:head
    [:title title]
    (include-css "/css/bootstrap.min.css")
    (include-css "/css/bootstrap-responsive.min.css")
    (include-css "/css/docs.css")
    ;(cljs/include-scripts :with-jquery)
    ]
   [:body
    (navbar-top title)
    [:div.container
     [:div#content
      [:section#main (spinner)]]]]
   (include-js "/js/jquery-1.7.1.min.js")
   (cljs/include-scripts)
   (include-css "/css/datepicker.css")
   (include-js "/js/bootstrap-datepicker.js")
   (include-js "/js/bootstrap.min.js")))
