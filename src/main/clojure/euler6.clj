(ns euler6)

; Get the square of a number
(defn square [x]
  (* x x))

; Get the sum of the squares of a sequence of numbers
(defn sum-squares [nums]
  (apply + (map square nums)))

; Get the square of the sum of a sequence of numbers
(defn square-sum [nums]
  (square (apply + nums)))

; Get the difference between the square of the sum, and the sum of
; the squares, of a sequence of numbers
(defn diff-sq-s--s-sq [nums]
  (- (square-sum nums) (sum-squares nums)))


; Euler problem 6: Get the diff for the first hundred natural numbers
(println
  (diff-sq-s--s-sq (range 1 101))
 )