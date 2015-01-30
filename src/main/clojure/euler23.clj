(ns euler23 [:use numberTools])

; Check if a number is "abundant": the sum of its proper divisors is
; greater than the number itself.
(defn is-abundant [n]
  (> (sum-divisors n) (* 2 n))
  )

; Generate a lazy sequence of all abundant numbers above the given number.
(defn abundant-numbers-from [n]
  (loop [idx n]
    (if (is-abundant idx)
      (lazy-seq (cons idx (abundant-numbers-from (inc idx))))
      (recur (inc idx))
      )))

(def abundant-numbers
  (abundant-numbers-from 1))


; Given that all integers > 28123 can be written as the sum of two abundant
; numbers, find all integers that can NOT be written as such.