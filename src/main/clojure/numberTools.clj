(ns numberTools)

; lazy sequence of primes, from github:
; https://github.com/richhickey/clojure-contrib/blob/78ee9b3e64c5ac6 82fb223fc79292175e8e4f0c/src/main/clojure/clojure/contrib/lazy_seqs.clj#L66
(def primes
  (concat 
   [2 3 5 7]
   (lazy-seq
    (let [
          ; primes-from is a function that takes as its arguments, first,
          ; a candidate number we wish to check for primacy, second
          ; an ordered list of primes that might divide n.
          ; If a divisor is found, it recurses with a new n. If not,
          ; then n must be prime, so it adds n to the list.
          ; It returns a lazy seq of all prime numbers >= the initial n.
          primes-from
          (fn primes-from [n [f & r]]
            (if (some ; Does there exist an element...
                  #(zero? (rem n %)) ; such that the element divides n...
                  (take-while #(<= (* % %) n) primes)) ; among all primes up to sqrt(n)?
              ; true: a prime exists that divides n, so recurse with the next candidate n
              (recur (+ n f) r)
              ; false: n is prime, so add it in front of all the primes that follow n.
              (lazy-seq (cons n (primes-from (+ n f) r)))))
          ;------------------------------------------------
          ; wheel is a cycle of candidate numbers, modulo 210 = 2*3*5*7.
          ; The idea is similar to the 6n +/- 1 trick.
          ; (In fact, to use that, replace the vector with [2 4])
          wheel (cycle [2 4 2 4 6 2 6 4 2 4 6 6 2 6  4  2
                        6 4 6 8 4 2 4 2 4 8 6 4 6 2  4  6
                        2 6 6 4 2 4 6 2 6 4 2 4 2 10 2 10])]
      (primes-from 11 wheel)))))




; Get the primes that divide n. Start with prime index pri.
; (To just get all the primes, set pri=0)
(defn dividing-primes [n pri]
  (loop [i 0 p (first primes)]
    (if (> p (. Math sqrt n))
      ; p is too big, so we must never have found a divisor -> n is prime
      [n]
      (if (zero? (mod n p))
        ; p | n, so add p to the list
        ;(recur (inc i) (nth primes (inc i)) (conj ans p))
        ; p | n, so add p to the list, and get the dividing primes of n/p
        (cons p (dividing-primes (/ n p) i))
        ; p doesn't divide n, so keep looking
        (recur (inc i) (nth primes (inc i)))
))))





; Given an index in a collection, return the number of times that
; the value repeats
(defn num-repeats [col idx]
  (let [value (nth col idx)]
    (loop [i (+ idx 1)]
      (if (= i (count col))
        (- i idx)
        (if (= value (nth col i))
          ; col[i] == value, so keep going
          (recur (inc i))
          ; col[i] != value, so return
          (- i idx)
)))))

; Get the number of divisors of the given number
(defn num-divisors [n]
  (let [d-primes (dividing-primes n 0)]
    (loop [i 0 total 1]
      (if (>= i (count d-primes))
        total
        (let [repeats (num-repeats d-primes i)]
          (recur (+ i repeats) (* total (inc repeats)))
)))))

; Given a number r and a limit n, get
; sum (i=0...n) (r^i)
(defn geom-sum [r n]
  (if (= 1 r)
    (inc n)
	  (int (/
	    (dec (Math/pow r (inc n)))
	    (dec r)
	    ))))

; Get the sum of divisors of a number
(defn sum-divisors [n]
  (loop [d-primes (dividing-primes n 0) sum 1]
    (if (zero? (count d-primes))
      sum
      (let [multiplicity (num-repeats d-primes 0)]
        (recur
          (drop multiplicity d-primes)
          (* sum (geom-sum (first d-primes) multiplicity)))
        ))))
