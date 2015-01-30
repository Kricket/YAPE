(ns euler9)

; If there exists a c such that a2 + b2 = c2, then return it.
; Otherwise, return 0.
(defn get-py-c [a b]
  (let [rt (. Math sqrt (+ (* a a) (* b b)))]
    (if (= rt (int rt))
      rt
      0)))

; Given b and a+b+c, find a. Return 0 if impossible.
(defn complete-py-trip [b maxsum]
  (loop [a 1]
    (if (= a b)
      0 ; none found, so quit
      (let [c (get-py-c a b)]
        (if (= (+ a b c) maxsum)
          a ; Success - a+b+c = maxsum
          (recur (inc a))
)))))

; Find a pythagorean triplet such that a+b+c = maxsum
(defn find-py-trip [maxsum]
  (loop [b 2]
    (let [ans (complete-py-trip b maxsum)]
      (if (> b (/ maxsum 2))
        0 ; No solution
        (if (zero? ans)
          (recur (inc b))
          [ans b])))))

; Given a and b, find a*b*c
(defn mult-py-trip [ab]
  (* (first ab) (last ab) (get-py-c (first ab) (last ab))))



; Euler problem 9: find a*b*c, where
;  a2 + b2 = c2
;  a + b + c = 1000
;  a < b < c
(println
  (mult-py-trip (find-py-trip 1000))
  )