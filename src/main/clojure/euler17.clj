(ns euler17)

; The number of letters in the spelling of numbers up to 19.
; "zero" is 0 for simplicity in the code that follows.
(def under-20
  [0 ; zero - not used
   3
   3
   5
   4
   4
   3
   5
   5
   4
   3
   6 ; eleven
   6 ; twelve
   8 ;thirteen
   8 ;fourteen
   7
   7
   9
   8 ; eighteen
   8 ;nineteen
])

; Number of letters in multiples of ten from 20 to 90.
; First two places are padded for ease of use.
(def tens-lens
  [0 0 ; padding for ease of use
   6 ;twenty
   6 ;thirty
   5 ;forty
   5 ;fifty
   5 ;sixty
   7 ;seventy
   6 ;eighty
   6 ;ninety
])

; The number of letters in the spelling of a number from 1 to 99 inclusive.
(defn len-under-100 [n]
  (cond
    (< n 1) 0
    (< n 20) (nth under-20 n)
    :default (+ (nth under-20 (mod n 10)) ; ones place
               (nth tens-lens (int (/ n 10))) ; tens place
)))

; The number of letters in the spelling of a number from 1 to 1000 inclusive.
(defn num-len [n]
  (cond
    (< n 100) (len-under-100 n)
    (= n 1000) 11 ; one thou sand
    :default ; n is 3 digits
    (let [under-100 (len-under-100 (mod n 100))]
      (if (zero? under-100)
        ; Just "x hun dred"
        (+ 7 (nth under-20 (int (/ n 100))))
        ; "x hun dred and ..."
        (+ 10 (nth under-20 (int (/ n 100))) under-100)
))))

; Euler problem 17: count the letters of all numbers from 1 to 1000.
(def lens-up-to-1000
  (apply +
    (map num-len (range 1 1001))
))

(println lens-up-to-1000)