import { defineConfig } from 'rollup';
import encoding from 'rollup-plugin-encoding';


export default defineConfig({
    input: 'ext/js/content.js', // Assuming this is where shadow-cljs outputs
    output: {
        file: 'ext/js/content_bundle.js', // Output file
        format: 'es', // Or 'iife' for inline JavaScript
    },
    plugins: [
        encoding({
            encoding: 'ASCII'
        })
    ]
});
