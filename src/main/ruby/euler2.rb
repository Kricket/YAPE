def nextFib(curFib)
  [curFib[1], curFib[0] + curFib[1]]
end

curFib = [1, 2]
sum = 2
while curFib[1] < 4000000
  curFib = nextFib(curFib)
  sum += curFib[1] if curFib[1].even?
end

puts "Sum of even Fibonacci numbers less than 4 million: #{sum}"
