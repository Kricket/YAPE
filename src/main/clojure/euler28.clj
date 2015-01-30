(ns euler28)

; This could be optimized even further by using sum formulae for each diagonal.

; Going up+right: n^2 for n=3, 5, 7...
(defn up-right-from [n]
  (lazy-seq (cons (* n n) (up-right-from (+ n 2))))
)

(def up-right
  (up-right-from 3)
)

; Going down+left: n^2 + 1 for n=2, 4, 6...
(defn down-left-from [n]
  (lazy-seq (cons (inc (* n n)) (down-left-from (+ n 2))))
  )

(def down-left
  (down-left-from 2)
  )

; Going up+left: n^2 - n + 1 for n=3, 5, 7...
; Going down+right: (same formula) for 2, 4, 6...
(defn n2-less-n-plus-1 [n]
  (lazy-seq (cons (inc (- (* n n) n)) (n2-less-n-plus-1 (+ n 2))))
  )

(def up-left
  (n2-less-n-plus-1 3)
  )

(def down-right
  (n2-less-n-plus-1 2)
  )


; Get the sum of diagonals for a spiral of radius n (not counting the center)
(defn diag-sum [rad]
  (+ 1
    (apply + (take rad up-right))
    (apply + (take rad up-left))
    (apply + (take rad down-right))
    (apply + (take rad down-left))
    ))


; Euler problem 28: get the sum of the diagonals of a 1001x1001 spiral
(println
  (diag-sum 500)
  )