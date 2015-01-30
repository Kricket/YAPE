(ns euler4)

; Determine if num is a palindrome
(defn is-palindrome [numstr]
  (if (= numstr nil) ; if empty string, then we're done
    true
    (if (= (first numstr) (last numstr))
      (recur (butlast (rest numstr)))
      false
)))

; Determine if a number can be factored into two 3-digit divisors
; Assumes that 0 < num <= 999*999
(defn has-3-dig-divisors [num]
  (loop [n (int (. Math sqrt num))]
    (if (< n 100)
      false ; No 3-digit divisors
      (if (zero? (mod num n))
        ; n divides num, so we have to check if the other divisor is 3 digits
        (if (= 3 (count (str (/ num n))))
          true
          (recur (dec n)))
        (recur (dec n))
        )
      )
    )
  )

; Find the biggest palindrome less than (* 999 999)
(defn find-biggest-palindrome []
  (loop [n 998001]
    (if (is-palindrome (str n))
      (if (has-3-dig-divisors n)
        n
        (recur (dec n))
        )
      (recur (dec n))
      )))



; euler problem 4: find the largest palindrome that is the product of two
; 3-digit numbers
(println
  (find-biggest-palindrome)
  )