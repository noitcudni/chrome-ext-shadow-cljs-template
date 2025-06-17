# Chrome Extension v3 in CLJS

Ported [this example](https://github.com/GoogleChrome/chrome-extensions-samples/tree/17956f44b6f04d28407a4b7eee428611affd4fab/examples/water_alarm_notification) to use ClojureScript with shadow-cljs.

Clone this repo and run

```
npm install
npx shadow-cljs watch ext
# or
npx shadow-cljs release ext
```

Then load the `ext` folder as an "Unpacked Extension" via [Chrome Extensions](chrome://extensions/).

Written using the "modern" [:target :esm](https://shadow-cljs.github.io/docs/UsersGuide.html#target-esm), which appears to work fine. Manifests no longer need to be modified by the build because ESM can load additional files on its own (as done with the `:shared` module).

Only relevant change over the JS version was adding `"type": "module` to the `"background"` in `manifest.json`. As well as `type="module"` in the `popup.html`.

For me the notification doesn't actually show and I'm not sure why. Might be because I blocked Chrome from showing Notifications, but I can't find any indicator about any of that. The code does load and run fine which is all I wanted to show.

You do need to load the shadow-cljs page at localhost:9630 and the extension itself for the repl to function properly.

## rollup
### development
```
rollup -i ext/js/content.js -w -o ext/js/content_bundle.js
```
If the content script fails to load, try restarting the patched up chromium as the last resort.
Editing and saving content.cljs will cause a reload, and it will fail because it can't eval content script. Just reload the extension to see your change.

### production
```
rollup -i prod/js/content.js -w -o prod/js/content_bundle.js
```
still need to do this.

## run custom compiled chromium
in src/out/release
run ./chrome --use-gl=desktop

#NOTE
5/8/2025 - I got background and popup to jackin while hotcompile content with rollup. Still need to reload the extension.

Don't eval from content. The nrepl is essentially a glorified compiler. Once I eval an expression, it screws up sw's and pop's repl
