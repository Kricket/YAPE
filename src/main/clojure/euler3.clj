(ns euler3)

; Get the greatest odd integer <= the square root of num
(defn odd-sqrt [num]
  (let [myroot (int (. Math sqrt num))]
    (if (zero? (mod myroot 2))
      (dec myroot)
      myroot
)))

; Get a number that divides num, or 0 if num is prime
(defn one-factor [num]
  (if (< num 4)
    0 ; Everything less than 4 is prime
	  (if (zero? (mod num 2))
	    2
		  (loop [n (odd-sqrt num)]
		    (if (< n 2)
		      0 ; base case - num is prime - return 0
		      (if (zero? (mod num n)) ; recursive case - check if n | num
		        ; n | num: return n
		        n
		        ; n doesn't divide num, so continue the loop
		        (recur (- n 2))
))))))

; Two-argument function that returns a list of prime factors of n
(defn factors-worker [num acc]
  (let [f1 (one-factor num)]
    (if (zero? f1)
      [num]
      (concat acc (factors-worker f1 acc) (factors-worker (/ num f1) acc))
)))


; Get a list of the factors of num
(defn factors [num]
  (factors-worker num []))


; Euler problem 3: get the greatest prime factor of 600851475143
(println
  (factors 600851475143)
  )