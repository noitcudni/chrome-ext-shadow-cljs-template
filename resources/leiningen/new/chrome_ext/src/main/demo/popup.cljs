(ns demo.popup
  (:require [demo.common :as common]
            [reagent.core :as reagent :refer [atom]]
            [re-com.core :as recom]
            )
  )

(defn current-page []
  (fn []
    [recom/v-box
     :gap "20px"
     :width "380px"
     :children [[recom/label :label "recom label goes here"]]
     ]
    ))

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")) )

(defn send-message []
  (js/chrome.runtime.sendMessage #js {:payload (common/marshall {:type :popupMessage :message "Hello from popup!!!!"})})
  (js/console.log "Message sent from popup"))

(defn init []
  (mount-root)
  (send-message)
  (js/console.log "popup init done"))
