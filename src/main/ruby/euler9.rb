def isPythagorean(a, b, c)
  a*a + b*b == c*c
end

def findTriplet
  for a in (1...999)
    for b in (1...(1000 - a))
      c = 1000 - a - b
      return [a, b, c] if isPythagorean(a, b, c)
    end
  end
end

trip = findTriplet
puts "Triplet is #{trip}; sum is #{trip.reduce(:+)}; product is #{trip.reduce(:*)}"
