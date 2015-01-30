(ns euler14)

; Given a number, get the next HOTPO number.
; (HOTPO = Half Or Triple Plus One)
(defn get-next-hotpo [n]
  (if (zero? (mod n 2))
    (/ n 2)
    (+ (* 3 n) 1)
))


; Get the length of a HOTPO chain, starting at the given number
(defn get-chain-length [start]
  (loop [n start len 1]
    (if (= n 1)
      len
      (recur (get-next-hotpo n) (inc len))
      )))

; Get an integral number half the size of the given parameter, rounding UP.
(defn halfint [n]
  (if (zero? (mod n 2))
    (/ n 2)
    (/ (inc n) 2)
))

; Find the starting number under the given limit that gives
; the longest hailstone sequence.
; We can ignore everything up to (/ startlimit 2), because all such
; numbers N are already part of a sequence starting at (* 2 N)
(defn get-longest-hail-seq [startlimit]
  (loop [start (halfint startlimit) maxlen 2 maxidx 2]
    (if (= start startlimit)
      maxidx
      (let [startlen (get-chain-length start)]
        (if (> startlen maxlen)
          (recur (inc start) startlen start)
          (recur (inc start) maxlen maxidx)
          )))))



; Euler problem 14: find the starting index under 1 million that gives the longest chain.
(println
  (time (get-longest-hail-seq 1000000))
  )