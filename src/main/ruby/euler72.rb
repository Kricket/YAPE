require_relative 'utils'

def properFractionsUpTo(d)
  (2..d).map { |n| Utils.phi(n) }.reduce(:+)
end

MAX_DEN = 1000000
puts "Proper fractions with denominator up to #{MAX_DEN} = #{properFractionsUpTo(MAX_DEN)}"
