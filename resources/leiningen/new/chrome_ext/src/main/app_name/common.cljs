(ns {{name}}.common
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async.interop :refer-macros [<p!]]
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

;; (go (prn (<! (get-active-tab-id))))
;; (go (let [t (<p! (js/chrome.tabs.query #js{:currentWindow true :active true}))]
;;    (prn "currenWindow t: " (-> t js->clj first (get "id")))
;;    ))
