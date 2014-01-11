(ns modern-cljs.remotes
  (:require [shoreleave.middleware.rpc :refer [defremote wrap-rpc]]
            [compojure.handler :refer [site]]
            [modern-cljs.core :refer [handler]]
            [modern-cljs.login.java.validators :as v]))

(defremote email-domain-errors [email]
  (v/email-domain-errors email))

(defremote calculate [quantity price tax discount]
  (-> (* quantity price)
      (* (+ 1 (/ tax 100)))
      (- discount)))

(def app (-> (var handler)
             (wrap-rpc)
             (site)))
