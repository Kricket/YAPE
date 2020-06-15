lcm = 1
for x in 2..20
  lcm = lcm.lcm x
end

puts "Least Common Multiple of 1..20 is #{lcm}"
