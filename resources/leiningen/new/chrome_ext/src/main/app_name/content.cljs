(ns {{name}}.content
  (:require [{{name}}.common :as common]))

(defn init []
  (js/console.log "content done!!")
  (js/chrome.runtime.onMessage.addListener
    (fn [request sender sendResponse]
      (let [payload (common/unmarshall (aget request "payload"))
            _ (prn payload)]
        (prn "[content] Received message from sw:" (:message payload))
        (sendResponse #js {:received true})))))
