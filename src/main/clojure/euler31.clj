(ns euler31)

; Here's the idea:
; Suppose we have a sequence of all the possible ways to get to every
; value, using (n-1) coins. With this, we can calculate a sequence of
; all the possible ways to get to every value using n coins. When
; adding the nth coin (c pence), the new number of ways to get to
; a value v is seq[v] + seq[v-c] + seq[v-2c] + ..., for all valid indexes
; of seq.
; The "correct" way to do this would be to initialize the sequence with
; all zeroes, except for seq[0] = 1 (one way to get to 0p with no coins).
; However, it's clear that, with 1p coins, there's one way to get to every
; value, so a tiny optimization is to start with a sequence of all 1's.

; All the possible coin values, in pence.
(def coins [1 2 5 10 20 50 100 200])


; Given a target value, a coin value, and a sequence of all the ways
; to get to all values with other coins, calculate how many ways exist
; to get to the given value, incorporating the new coin.
(defn ways-to-value [value coin other-ways]
  (loop [cur-val value total 0]
    (if (< cur-val 0)
      total
      (recur (- cur-val coin) (+ total (nth other-ways cur-val)))
      )))

; Use the previous function to generate a lazy sequence of all possible
; ways to get to any value.
(defn all-ways-to-value-from [from-val coin other-ways]
  (lazy-seq (cons
              (ways-to-value from-val coin other-ways)
              (all-ways-to-value-from (inc from-val) coin other-ways)
              )))

; Using the above lazy sequences, generate a lazy sequence incorporating
; ALL the available coins.
(def all-ways
  (loop [cidx 1 ; Skip 1p
         ans (repeat 1)]
    (if (= cidx (count coins))
      ans
      (recur
        (inc cidx)
        (all-ways-to-value-from 0 (nth coins cidx) ans)
        ))))

; Euler problem 31: find the number of possible ways to get to £2 using
; the above coins.
(println
  (nth all-ways 200)
  )