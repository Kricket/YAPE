(ns euler1)

; Determine if the given number is a multiple of 3 or 5
(defn isMult3-5 [num]
  (or (zero? (mod num 3)) (zero? (mod num 5))))

; Compute the sum of all numbers from 3 to (num-1)
; that are a multiple of 3 or 5
(defn sumMults-3-5 [num]
  (loop [n 3 acc 0]
    (if (> n num)
      acc
      (if (isMult3-5 n)
        (recur (inc n) (+ acc n))
        (recur (inc n) acc)
))))

; Problem 1: sum all multiples of 3 or 5 less than 1000
(println
  (sumMults-3-5 999)
  )