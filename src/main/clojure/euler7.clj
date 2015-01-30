(ns euler7)

; Given a number and a list of other numbers (primes), determine
; if any number in the list divides the given number
(defn has-divisor [num primes]
  (loop [n 0]
    (if (>= n (count primes))
      ; No divisor found
      false
      (if (zero? (mod num (nth primes n)))
        ; primes[n] divides num
        true
        (recur (inc n))
))))

; given a list of numbers, find the first number greater than
; the last element which is not divisible by any of the elements
(defn find-next-prime [primes]
  (loop [n (+ 2 (last primes))]
    (if (has-divisor n primes)
      (recur (+ n 2)) ; A divisor exists, so keep looking
      n ; no divisor, so return n
)))

; Get the nth prime, for n > 2
(defn nth-prime [n]
  (loop [total 2 primes [2 3]]
    (if (= (count primes) n)
      (last primes)
      (recur (inc total) (conj primes (find-next-prime primes)))
)))



; Euler problem 7: find the 10001st prime number
(println
  (nth-prime 10001)
 )