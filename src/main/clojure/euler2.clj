(ns euler2)

; given an adjacent pair of numbers in the fibonacci sequence, calculate the next pair
(defn next-fib-pair [[a b]]
  [b (+ a b)])

; Calculate the sum of all even fibonacci numbers less than the given number
(defn fib-sum-even [max-fib]
  (loop [fib-pair [0 1] acc 0]
    (let [fibn (last fib-pair)]
      (if (> fibn max-fib)
        acc
        (if (zero? (mod fibn 2))
          (recur (next-fib-pair fib-pair) (+ acc fibn))
          (recur (next-fib-pair fib-pair) acc)
)))))

; Problem 2: find the sum of all even fibonacci numbers less than 4 million
(println
  (fib-sum-even 4000000)
  )