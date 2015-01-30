(ns euler21 [:use numberTools])

; Get the sum of proper divisors of a number
(defn sum-prop-divisors [n]
  (- (sum-divisors n) n)
  )

; Figure out if n has a "friend". Return n for true or 0 for false.
(defn is-amicable [n]
  (let [m (sum-prop-divisors n)]
    (if (= m n)
      0
      (if (= n (sum-prop-divisors m))
        n
        0
))))


; Euler 21: evaluate the sum of all amicable numbers under 10000
; (Two numbers are amicable if the sum of the proper divisors of one
; equals the sum of the proper divisors of the other. The two numbers
; MUST be different!)
(println
  (time
    (apply + (map is-amicable (range 2 10000)))
  ))