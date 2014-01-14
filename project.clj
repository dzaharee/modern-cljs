(defproject modern-cljs "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  ;; CLJ AND CLJS source code path
  :source-paths ["src/clj" "src/cljs" "src/brepl"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2069"]
                 [compojure "1.1.6"]
                 [domina "1.0.3-SNAPSHOT"]
                 [hiccups "0.2.0"]
                 [org.clojars.magomimmo/shoreleave-remote-ring "0.3.1-SNAPSHOT"]
                 [org.clojars.magomimmo/shoreleave-remote "0.3.1-SNAPSHOT"]
                 [com.cemerick/valip "0.3.2"]
                 [enlive "1.1.4"]]

  ;; lein-ljsbuild plugin to build a cljs project
  :plugins [[lein-cljsbuild "1.0.0"]
            [lein-ring "0.8.8"]
            [com.cemerick/clojurescript.test "0.2.1"]]

  :hooks [leiningen.cljsbuild]

  :test-paths ["test/clj" "test/cljs"]

  :cljsbuild {:builds
              {:ws-unit-tests
               {;; CLJS source code and unit test paths
                :source-paths ["src/brepl" "src/cljs" "test/cljs"]

                ;; Google Closure Compiler options
                :compiler {;; the name of emitted JS script file for unit testing
                           :output-to "test/js/testable_dbg.js"

                           ;; minimum optimization
                           :optimizations :whitespace
                           ;; prettyfying emitted JS
                           :pretty-print true}}

               :simple-unit-tests
               {;; same path as above
                :source-paths ["src/brepl" "src/cljs" "test/cljs"]

                :compiler {;; different JS output name for unit testing
                           :output-to "test/js/testable_pre.js"

                           ;; simple optimization
                           :optimizations :simple

                           ;; no need prettification
                           :pretty-print false}}

               :advanced-unit-tests
               {;; same path as above
                :source-paths ["src/cljs" "test/cljs"]

                :compiler {;; different JS output name for unit testing
                           :output-to "test/js/testable.js"

                           ;; advanced optimization
                           :optimizations :advanced

                           ;; no need prettification
                           :pretty-print false}}

               :dev
               {;; clojurescript source code path
                :source-paths ["src/cljs" "src/brepl"]

                ;; Google Closure Compiler options
                :compiler {;; the name of emitted JS script file
                           :output-to "resources/public/js/modern_dbg.js"

                           ;; minimum optimization
                           :optimizations :whitespace

                           ;; prettyfying emitted JS
                           :pretty-print true}}
               :prod
               {;; clojurescript source code path
                :source-paths ["src/cljs"]

                :compiler {;; different output name
                           :output-to "resources/public/js/modern.js"

                           ;; simple optmization
                           :optimizations :advanced

                           ;; no need prettyfication
                           :pretty-print false}}
               :pre-prod
               {;; clojurescript source code path
                :source-paths ["src/cljs" "src/brepl"]

                :compiler {;; different output name
                           :output-to "resources/public/js/modern_pre.js"

                           ;; simple optmization
                           :optimizations :simple

                           ;; no need prettyfication
                           :pretty-print false}}}

              :crossovers [valip.core valip.predicates
                           modern-cljs.login.validators
                           modern-cljs.shopping.validators]

              :test-commands {"phantomjs-whitespace"
                              ["phantomjs" :runner "test/js/testable_dbg.js"]

                              "phantomjs-simple"
                              ["phantomjs" :runner "test/js/testable_pre.js"]

                              "phantomjs-advanced"
                              ["phantomjs" :runner "test/js/testable.js"]}}

  :ring {:handler modern-cljs.core/app})
