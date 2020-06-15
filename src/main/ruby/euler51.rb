require_relative 'utils'
require 'set'

# Replace a digit in the given number
# Example: replace(12345, 2, 9) = 12945
def replace(num, idx, digit)
  pow = 10**idx
  # so pow = 10^2 = 100
  front = num / pow # = 123
  back = num - (front * pow) # = 12345 - 12300 = 45
  result = (((front / 10) * 10) + digit) * pow + back
  result
end

def e51
  primes = Utils.genPrimesTo(999999)
  bigPrimes = primes[primes.bsearch_index { |p| p > 100000 }...primes.size]
  bigPrimeSet = Set.new
  bigPrimeSet.merge bigPrimes

  bigPrimes.each_with_index do |p, idx|
    puts "Checking prime #{p} (index #{idx})" if idx % 100 == 0

    Utils.chooseNofM p.size - 1, p.size - 1 do |indices|
      foundPrimes = []
      # indices = a set of character indices to replace
      (0..9).each do |d|
        # d/digit: the number/character to replace
        test = p.dup
        indices.each { |i| test = replace(test, i, d) }

        foundPrimes << test if bigPrimeSet.include? test
      end

      foundPrimes.uniq!

      return foundPrimes if foundPrimes.size > 7
    end
  end
end

puts "Found solution: #{e51}"
