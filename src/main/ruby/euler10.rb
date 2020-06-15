require_relative "utils"

MAX_PRIME = 2000000

primes = Utils.genPrimesTo MAX_PRIME
primes.pop while primes.last > MAX_PRIME

sum = primes.reduce(:+)
puts "Sum of primes up to #{MAX_PRIME} is #{sum}"
