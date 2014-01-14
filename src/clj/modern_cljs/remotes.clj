(ns modern-cljs.remotes
  (:require [shoreleave.middleware.rpc :refer [defremote wrap-rpc]]
            [compojure.handler :refer [site]]
            [modern-cljs.login.java.validators :as v]
            [modern-cljs.utils :refer [parse-integer parse-double]]))

(defremote email-domain-errors [email]
  (v/email-domain-errors email))

(defremote calculate [quantity price tax discount]
  (let [q (parse-integer quantity)
        p (parse-double price)
        t (parse-double tax)
        d (parse-double discount)]
  (-> (* q p)
      (* (+ 1 (/ t 100)))
      (- d))))
