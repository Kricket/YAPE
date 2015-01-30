(ns euler20)

; Get the sum of the digits of the given number
(defn sum-digits [n]
  (loop [sum 0 cur n]
    (if (< cur 1)
      sum
      (recur (+ sum (mod cur 10)) (bigint (/ cur 10)))
)))

; Get n!
(defn factorial [n]
  (loop [i (dec n) total (bigint n)]
    (if (< i 2)
      total
      (recur (dec i) (* i total))
      )))

; euler problem 20: get the sum of the digits of 100!
(println
  (sum-digits (factorial 100))
  )