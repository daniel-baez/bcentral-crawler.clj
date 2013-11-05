(ns bcentral-crawler.core
  (:import [org.jsoup Jsoup])
  (:require [clojure.pprint :refer [pprint]])
  (:gen-class))

(def ^:private url "http://si3.bcentral.cl/indicadoresvalores/secure/indicadoresvalores.aspx")
(def ^:private selector "div#ind-dia table tr td")

(defn- get-document
  ([]
     (-> (Jsoup/connect url)
         .get))
  ([selector]
     (.select (get-document) selector)))

(defn- html
  [element]
  (.trim (.text element)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [table (get-document selector)]
    (pprint (apply array-map (map html table)))))
