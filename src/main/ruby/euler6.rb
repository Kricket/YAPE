sumOfSq = (1..100).map { |i| i*i }.reduce(:+)
sqOfSum = (1..100).reduce(:+) ** 2

puts "For 1..100: square of sum (#{sqOfSum}) - sum of squares (#{sumOfSq}) = #{sqOfSum - sumOfSq}"
