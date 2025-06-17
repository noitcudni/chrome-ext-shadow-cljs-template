(ns {{name}}.sw
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [<!]]
            [cljs.core.async.interop :refer-macros [<p!]]
            [{{name}}.common :refer [get-active-tab-id] :as common]))

(defn send-message-to-content []
  (go
    (let [tab-id (<! (get-active-tab-id))]
      (when tab-id
        (let [message-payload {:type :backgroundMessage :message "Hello from sw to content!"}
              marshalled (common/marshall message-payload)
              response (<p! (js/chrome.tabs.sendMessage tab-id (clj->js {:payload marshalled})))]
          (js/console.log "[sw] Received response from content:" response))))))

(defn handle-message [request]
  (js/console.log "[sw] Service worker reading message:" request)
  (let [{type :type :as request-payload} (common/unmarshall (aget request "payload"))
        _ (prn "request-payload: " request-payload)
        _ (prn "type: " type)]
    (cond
      (= type :popupMessage)
      (do (js/console.log "[sw] Handling popupMessage:" (:message request-payload))
          (send-message-to-content))
      :else
      (prn "[sw] Unhandled message type: " type))))

(defn init []
  (js/chrome.runtime.onMessage.addListener
    (fn [request sender sendResponse]
      (handle-message request)
      (sendResponse #js {:received true})))
  (js/console.log "sw init done"))
