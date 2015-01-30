(ns euler16)

(def bignum (.pow (bigint 2) 1000))

; Get the sum of the digits of the given number
(defn sum-digits [n]
  (loop [sum 0 cur n]
    (if (< cur 1)
      sum
      (recur (+ sum (mod cur 10)) (bigint (/ cur 10)))
)))


; Euler 16 - find the sum of the digits of 2^1000 when represented in base 10.
(println
  (sum-digits bignum)
  )