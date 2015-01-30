(ns euler18)

(def triangle [
[75]
[95 64]
[17 47 82]
[18 35 87 10]
[20  4 82 47 65]
[19  1 23 75  3 34]
[88  2 77 73  7 63 67]
[99 65  4 28  6 16 70 92]
[41 41 26 56 83 40 80 70 33]
[41 48 72 33 47 32 37 16 94 29]
[53 71 44 65 25 43 91 52 97 51 14]
[70 11 33 28 77 73 17 78 39 68 17 57]
[91 71 52 38 17 14 91 43 58 50 27 29 48]
[63 66  4 68 89 53 67 30 73 16 69 87 40 31]
[ 4 62 98 27 23  9 70 98 73 93 38 53 60  4 23]
])

; Given a previous line of maximums and an index, generate a lazy seq
; representing the maximum of each child for the line directly above
(defn sub-max-line [prev-max-line idx]
  (if (= idx (dec (count prev-max-line)))
    []
    (cons
      ; The max of the two children of idx
      (max (nth prev-max-line idx) (nth prev-max-line (inc idx)))
      ; The rest of the line
      (lazy-seq (sub-max-line prev-max-line (inc idx)))
)))

; Given a line of maximums, get the line of maximums directly above it.
(defn next-max-line [prev-max-line]
  (map +
    (sub-max-line prev-max-line 0)
    (nth triangle (- (count prev-max-line) 2))
))

; Iterate through the above triangle to find the highest sum of a path
(defn greatest-path-sum []
  (loop [y (dec (count triangle))
         max-line (nth triangle y)]
    (if (= y 0)
      (first max-line)
      (recur (dec y) (next-max-line max-line))
      )))

; Euler problem 18: given the triangle above, get the highest value possible by descending
; the triangle by adjacent nodes and collecting their sum.
(println
  (greatest-path-sum)
  )