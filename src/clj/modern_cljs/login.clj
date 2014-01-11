(ns modern-cljs.login
  (:require [modern-cljs.login.validators :refer [user-credential-errors]]
            [modern-cljs.login.java.validators :refer [email-domain-errors]]))

(defn authenticate-user [email password]
  (if (or (boolean (user-credential-errors email password)) (boolean (email-domain-errors email)))
    (str "Please complete the form")
    (str email " and " password
         " passed the formal validation, but you still have to be authenticated")))
