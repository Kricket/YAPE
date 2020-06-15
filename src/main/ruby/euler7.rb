require_relative "utils"

PRIME_IDX = 10001
primes = Utils.genNPrimes PRIME_IDX
puts "Prime number #{PRIME_IDX} is #{primes[PRIME_IDX - 1]}"
