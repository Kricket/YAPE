package yape.yape.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import yape.math.primes.PrimeLister;

/**
 * Find the prime under 1 million that can be written as the sum of the most
 * number of consecutive primes.
 */
public class Euler50 {
	private static HashSet<Long> primeSet;
	private static int primesInSum;
	private static int maxDepth;
	private static Long maxPrime;

	public static void main(String[] args) {
		PrimeLister.ensurePrimesUpTo(1000000);
		ArrayList<Long> primes = new ArrayList<Long>(PrimeLister.getPrimeList());
		primeSet = new HashSet<Long>(primes);
		ArrayList<Long> sums1 = new ArrayList<Long>(
				Collections.<Long>nCopies(primes.size(), 0L)
				);
		ArrayList<Long> sums2 = new ArrayList<Long>(sums1);
		primesInSum = 1;
		maxDepth = 0;
		
		int len = primes.size();
		// Build sums1 on top of primes (on top of zeroes)...
		len = buildNextLevel(sums2, primes, sums1, len);
		// Build sums2 on top of sums1 on top of primes...
		len = buildNextLevel(primes, sums1, sums2, len);

		// The order is now
		// primes -> sums1 -> sums2 -> ...
		while(len > 1)
		{
			len = buildNextLevel(sums1, sums2, sums1, len);
			len = buildNextLevel(sums2, sums1, sums2, len);
		}
		System.out.println("Max depth " + maxDepth + " prime = " + maxPrime);
	}
	
	
	/**
	 * Build level0 on top of levels 1 (which is on top of level 2)
	 * Example:
	 *     10   15   23    31        (level0)
	 *   5    8    12   18    24     (level1)
	 * 2    3    5    7    11    13  (level2)
	 * The top level is the sum of all the LEAVES under it.
	 * The len param tells us how many valid elements exist in level2 and level1. We return the
	 * new number of valid elements (an element becomes invalid when it exceeds 1 million, since
	 * we're looking for a prime under 1 million).
	 */
	private static int buildNextLevel(final ArrayList<Long> level2, ArrayList<Long> level1, ArrayList<Long> level0, int len)
	{
		primesInSum++;

		for(int i=1; i<len; i++)
		{
			Long sum = level1.get(i) + level1.get(i-1) - level2.get(i);
			if(sum > 999999)
				return (i-1);
			if(primeSet.contains(sum))
			{
				maxDepth = primesInSum;
				maxPrime = sum;
			}
			level0.set(i-1, sum);
		}
		
		return len-1;
	}
}