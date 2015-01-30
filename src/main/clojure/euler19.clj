(ns euler19)

(defn is-leap [year]
  (if (zero? (mod year 400))
    true
    (if (zero? (mod year 100))
      false
      (if (zero? (mod year 4))
        true
        false
        ))))

; Number of days in each month
(def months
  [3 0 3 2 3 2 3 3 2 3 2 3]
  ;[31 28 31 30 31 30 31 31 30 31 30 31]
  )

; Given a date of the first of a month, return the day of the week
; starting the next month
(defn advance-one-month [day month year]
  (if (and (= month 1) (is-leap year))
    (mod (+ 1 day (nth months month)) 7)
    (mod (+ day (nth months month)) 7)
    ))
    

(defn count-sundays [start-year start-day end-year]
  (loop [year start-year
         month 0
         day start-day
         total 0]
    (if (> year end-year)
      total
      (if (= month (count months))
        (recur (inc year) 0 day total)
         (recur year (inc month) (advance-one-month day month year)
           (if (zero? day)
             (inc total)
             total)
           )))))


; euler problem 19: Given that Jan 1, 1900 was a Monday, how many Sundays fell on
; the first of the month from Jan 1, 1901 - Dec 31, 2000?
(println
  (- (count-sundays 1900 1 2000) (count-sundays 1900 1 1900))
  )