class Utils
  class << self
    # Find a factor of n smaller than n
    def findFactor(n)
      return 2 if n.even? && n > 2
      3.step(Integer.sqrt(n), 2) do |i|
        return i if n % i == 0
      end if n > 4
      1
    end

    # Find all prime factors of n
    def primeFactors(n)
      _primeFactors(n).flatten.sort
    end

    def isPrime(n)
      return false if n.even? unless n == 2
      3.step(Integer.sqrt(n), 2) do |i|
        return false if n % i == 0
      end
      true
    end

    # Generate (at least) the given number of primes
    def genNPrimes(num)
      _genPrimes { |p| p.size >= num }
    end

    # Generate primes up to (at least) the given number
    def genPrimesTo(max)
      _genPrimes { |p| p.last >= max }
    end

    # Calculate the Euler phi function for the given integer n
    def phi(n)
      primeFactors(n).uniq.reduce(n) { |prod, p| prod - prod/p }
    end

    # Iterate through all possible subsets of (0..possibleValues) of size <= maxSubsetSize
    # For each subset, call the given block
    def chooseNofM(maxSubsetSize, possibleValues, indices = [], &block)
      if maxSubsetSize == 0 || possibleValues < 0
        block.call indices if indices.size > 0
      else
        # First, all possible ways to choose, including M
        indices.push possibleValues
        chooseNofM maxSubsetSize-1, possibleValues-1, indices, &block
        # Then, all possible ways to choose WITHOUT M
        indices.pop
        chooseNofM maxSubsetSize, possibleValues-1, indices, &block
      end
    end

    private

    def _genPrimes(&stopCondition)
      puts "Generating primes..."

      startTime = Time.now

      primes = [2, 3, 5, 7, 11, 13]
      p = primes.last + 4
      while !stopCondition.call(primes)
        primes << p if isPrime p
        p += 2
        primes << p if isPrime p
        p += 4
      end

      endTime = Time.now

      puts "Generated #{primes.size} primes (up to #{primes.last}) in #{endTime - startTime}s"
      primes
    end

    def _primeFactors(n)
      factor = findFactor n
      # Base case: n is prime
      return [n] if factor == 1
      # Recursive case: n is not prime
      [_primeFactors(factor)] + [_primeFactors(n/factor)]
    end
  end
end
