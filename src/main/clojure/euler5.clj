(ns euler5)

; calculate the GCD of two positive integers
(defn gcd [a b]
  (if (> b a)
    (recur b a)
    (let [r (mod a b)]
      (if (zero? r)
        b
        (recur b r)
))))

; calculate the LCM of a pair of positive integers
(defn lcm-pair [[a b]]
  (/ (* a b) (gcd a b)))

; calculate the LCM of a list of positive integers
(defn lcm [nums]
  (if (= 2 (count nums))
    (lcm-pair (take 2 nums))
    (recur (lazy-cat [(lcm-pair (take 2 nums))] (drop 2 nums)))))

; euler problem 5: LCM of all integers from 1 to 20
(println
  (lcm (range 1 21))
  )