require_relative 'utils'

# For any k, We use 2k as an upper bound, since there is always the solution
#   1^(2k) * 2 * k
# In addition, any combination of integers a1...an can be made into a solution (not necessarily minimal)
# by adding ones: a1 * a2 * ... * an = a1 + a2 + ... + an + (enough ones to make up the difference)

# The idea here: iterate through all possible combinations of any integers whose product <= 2*MAX_K.
# Turn this into a solution by adding ones.
# Save this solution if it's the smallest we've found so far.
# When we get to the end, the mpsn table should be filled with all the solutions up to MAX_K.

class E88v2
  def solve(max)
    @MAX_K = max
    @MAX_solution = 2 * max
    # Minimal Product-Sum numbers. mpsn[i] = minimal found for k = (i+1)
    @mpsn = Array.new(@MAX_K)
    @mpsn[0] = 0 # Since the problem doesn't ask for k = 1

    @numchecks = 0

    # One of the factors must necessarily be less than sqrt(max possible).
    # Thus, we can optimize the first iteration:
    (2..Integer.sqrt(@MAX_solution)).each do |factor|
      findSolution factor, factor, 1
    end

#    puts "Found solutions: #{@mpsn}"
    puts "Number of checks: #{@numchecks}"
    puts "Sum of unique Minimal Product-Sum Numbers for 2 <= k <= #{max} = #{@mpsn.uniq.reduce(:+)}"
  end

  private

  def findSolution(curSum, curProd, numFactors)
    checkSolution curSum, curProd, numFactors

    return if curProd > @MAX_solution

    (2..(@MAX_solution / curProd)).each do |factor|
      findSolution curSum + factor, curProd * factor, numFactors + 1
    end
  end

  def checkSolution(sum, prod, numFactors)
    @numchecks += 1

    ones = prod - sum
    km1 = numFactors + ones - 1
    if km1 < @MAX_K && (@mpsn[km1] == nil || @mpsn[km1] > prod)
      @mpsn[km1] = prod
    end
  end
end

E88v2.new.solve 12000
