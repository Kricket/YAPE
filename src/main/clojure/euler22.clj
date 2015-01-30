(ns euler22)

; Get all the names as a sorted list
(defn get-sorted-list []
  (sort
    (re-seq #"[A-Z]+"
      (slurp "resources/names.txt")
      )))

(def A-offset (dec (int \A)))

; Calculate the "value" of a name = the sum of its characters
(defn word-value [word]
  (loop [n 0 sum 0]
    (if (= n (count word))
      sum
      (recur (inc n) (+ sum (- (int (nth word n)) A-offset)))
      )))

(defn get-score [idx word]
  (* (inc idx) (word-value word))
  )

(defn total-score []
  (let [names (get-sorted-list)]
    (loop [n 0 total 0]
      (if (= n (count names))
        total
        (recur (inc n) (+ total (get-score n (nth names n))))
        ))))

; Euler problem 22: Get the sum of all the scores of all the names in the file
(println
  (time (total-score))
  )