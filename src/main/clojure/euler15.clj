(ns euler15)

; Optimization (possible in Clojure?)
; start with (repeat 1)
; loop for n = 1..whatever:
;   Add element (n-1) to element n 


; Given:
; The previous line (infinite lazy sequence)
; The previous value on this line
; The index that we're currently at
; --> Get a lazy sequence representing the rest of the line
(defn rest-of-line [prevline prevval n]
  (let [cur (+ prevval (nth prevline n))]
    (lazy-seq (cons cur (rest-of-line prevline cur (inc n))))
))

; Get a lazy sequence of lazy sequences of each line, starting with the line
; after the given line
(defn rest-of-table [prevline]
  (let [thisline (rest-of-line prevline 0 0)]
    (lazy-seq (cons thisline (rest-of-table thisline)))
))

; An infinite lazy sequence of infinite lazy sequences.
; Each index (i,j) represents the number of possible paths
; from (i,j) to (0,0).
(def whole-grid
  (lazy-seq (cons (repeat 1) (rest-of-table (repeat 1))))
)

; Optional - for drawing
(defn pretty-draw [maxX maxY]
  (loop [y (dec maxY)]
    (if (< y 0)
      nil
      (do
        (println (reverse (take maxX (nth whole-grid y))))
        (recur (dec y))
))))

; Euler problem 15: get the number of paths in a 20x20 grid
(println
  (nth (nth whole-grid 20) 20)
  )