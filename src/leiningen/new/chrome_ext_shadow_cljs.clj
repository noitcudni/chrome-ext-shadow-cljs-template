(ns leinigen.new.chrome-ext-shadow-cljs
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "chrome-ext"))

(defn chromex-ext
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' chromex project (shadow-cljs).")
    (->files data
             ["shadow-cljs.edn" (render "shadow-cljs.edn" data)]
             ["rollup.config.js" (render "rollup.config.js" data)]
             ["README.md" (render "README.md" data)]
             ["package.json" (render "package.json" data)]

             ["src/main/{{sanitized}}/common.cljs" (render "src/main/app_name/common.cljs" data)]
             ["src/main/{{sanitized}}/popup.cljs" (render "src/main/app_name/popup.cljs" data)]
             ["src/main/{{sanitized}}/content.cljs" (render "src/main/app_name/content.cljs" data)]
             ["src/main/{{sanitized}}/sw.cljs" (render "src/main/app_name/sw.cljs" data)]

             ["ext/css/material-design-iconic-font.min.css" (render "ext/css/material-design-iconic-font.min.css")]
             ["ext/fonts/Material-Design-Iconic-Font.eot" (render "ext/fonts/Material-Design-Iconic-Font.eot")]
             ["ext/fonts/Material-Design-Iconic-Font.woff2" (render "ext/fonts/Material-Design-Iconic-Font.woff2")]
             ["ext/fonts/Material-Design-Iconic-Font.woff" (render "ext/fonts/Material-Design-Iconic-Font.woff")]
             ["ext/fonts/Material-Design-Iconic-Font.ttf" (render "ext/fonts/Material-Design-Iconic-Font.ttf")]
             ["ext/fonts/Material-Design-Iconic-Font.svg" (render "ext/fonts/Material-Design-Iconic-Font.svg")]
             ["ext/icon48.png" (render "ext/icon48.png")]
             ["ext/icon32.png" (render "ext/icon32.png")]
             ["ext/icon19.png" (render "ext/icon19.png")]
             ["ext/icon16.png" (render "ext/icon16.png")]
             ["ext/icon128.png" (render "ext/icon128.png")]
             ["ext/popup.html" (render "ext/popup.html" data)]
             ["ext/manifest.json" (render "ext/manifest.json" data)]
             ["ext/re-com.css" (render "ext/re-com.css" data)]
             ["ext/bootstrap.css" (render "ext/bootstrap.css" data)]

             ["prod/css/material-design-iconic-font.min.css" (render "prod/css/material-design-iconic-font.min.css")]
             ["prod/fonts/Material-Design-Iconic-Font.eot" (render "prod/fonts/Material-Design-Iconic-Font.eot")]
             ["prod/fonts/Material-Design-Iconic-Font.woff2" (render "prod/fonts/Material-Design-Iconic-Font.woff2")]
             ["prod/fonts/Material-Design-Iconic-Font.woff" (render "prod/fonts/Material-Design-Iconic-Font.woff")]
             ["prod/fonts/Material-Design-Iconic-Font.ttf" (render "prod/fonts/Material-Design-Iconic-Font.ttf")]
             ["prod/fonts/Material-Design-Iconic-Font.svg" (render "prod/fonts/Material-Design-Iconic-Font.svg")]
             ["prod/icon48.png" (render "prod/icon48.png")]
             ["prod/icon32.png" (render "prod/icon32.png")]
             ["prod/icon19.png" (render "prod/icon19.png")]
             ["prod/icon16.png" (render "prod/icon16.png")]
             ["prod/icon128.png" (render "prod/icon128.png")]
             ["prod/popup.html" (render "prod/popup.html" data)]
             ["prod/manifest.json" (render "prod/manifest.json" data)]
             ["prod/re-com.css" (render "prod/re-com.css" data)]
             ["prod/bootstrap.css" (render "prod/bootstrap.css" data)]
             )))
