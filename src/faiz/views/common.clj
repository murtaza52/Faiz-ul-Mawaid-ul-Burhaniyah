(ns faiz.views.common
  (:require [noir.cljs.core :as cljs])
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [include-css html5]]))

(ns example.views.common
  (:require [noir.cljs.core :as cljs])
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [include-css include-js html5]]))

(defpartial navbar-top [title]
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


(defpartial spinner []
  [:div.row
   [:div.span5 "&nbsp;"]
   [:div.span3 [:img {:src "/img/ajax-loader.gif"}]]])

(defpartial layout [title & content]
  (html5
   [:head
    [:title title]
    (include-css "/css/bootstrap.min.css")
    (include-css "/css/bootstrap-responsive.min.css")
    (include-css "/css/docs.css")
    (include-css "/css/datepicker.css")]
   [:body
    (navbar-top title)
    [:div.container
     [:div#content
      [:section#main (spinner)]]]]
   (cljs/include-scripts :with-jquery)
   (include-js "/js/bootstrap-datepicker.js")
   (include-js "/js/bootstrap.min.js")))
