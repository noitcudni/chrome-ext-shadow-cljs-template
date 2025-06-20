(ns {{name}}.common
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async.interop :refer-macros [<p!]]
            [cljs.core.async :refer [<!]]
            [cognitect.transit :as t]
            ))

(defn marshall [edn-msg]
  (let [w (t/writer :json)]
    (t/write w edn-msg)))

(defn unmarshall [msg-str]
  (let [r (t/reader :json)]
    (t/read r msg-str)))

(defn get-active-tab-id []
  (go
    (let [t (<p! (js/chrome.tabs.query #js{:lastFocusedWindow true :active true}))]
      (-> t js->clj first (get "id"))
      )))

(defn set-local-storage
  "
  Example: (go (let [r (<! (set-local-storage \"foo\" {\"a\" \"a\" \"b\" \"b\"}))]))
  "
  [k v]
  (go
    (<p! (js/chrome.storage.local.set (clj->js {k v})))))

(defn get-local-storage
  "
  Example:
  (go (let [data (<! (get-local-storage \"foo\"))]
      (prn \">> data: \" (get data \"a\"))
      ))
  "
  [k]
  (go
    (let [data (<p! (js/chrome.storage.local.get (clj->js [k])))
          data (js->clj data)
          ]
      (when-not (empty? data)
        (get data k))
      )))

;; (go (prn (<! (get-active-tab-id))))
;; (go (let [t (<p! (js/chrome.tabs.query #js{:currentWindow true :active true}))]
;;    (prn "currenWindow t: " (-> t js->clj first (get "id")))
;;    ))
