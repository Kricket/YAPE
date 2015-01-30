(ns euler10 [:use numberTools])

; Euler problem 10: find the sum of all primes less than 2 million
(println
  (apply + (take-while #(< % 2000000) primes))
  )