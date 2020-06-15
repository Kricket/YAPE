require_relative 'utils'
require 'set'

def concatNums(p, q)
  p * 10**(q.to_s.size) + q
end

primes = Utils.genPrimesTo(10000)
pset = Set.new
pset.merge primes

# cache of combinations that concatenate
catCache = {}

primes.each_with_index do |p, idx|
  primes[idx+1..primes.size-1].each do |q|
    if pset.include?(concatNums(p, q)) && pset.include?(concatNums(q, p))
      catCache[[p,q]] = 1
      catCache[[q,p]] = 1
    end
  end
end
