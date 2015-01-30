(ns euler12 (:use [numberTools]))

; A triangle number = sum (1..n) i
; I define a triangle pair as (n, t)
; where n = the limit of the sum, and t = the sum
; i.e. (1,1) (2,3) (3,6) (4,10) ...

(defn next-tri-pair [[n t]]
  [(inc n) (+ t (inc n))]
)

; Get the triangle pair immediately BEFORE the first triangle pair to have n divisors
(defn get-penult-tp [n]
  (take-while
    #(< (num-divisors (last %)) n)
    (iterate next-tri-pair [1 1])
))

; Get the first triangle number to have 500 divisors
(defn first-tp-500-div []
  (last (next-tri-pair (last (get-penult-tp 500))))
)



; euler problem 12: Get the first triangle number with 500 divisors
(println
  (first-tp-500-div)
  )