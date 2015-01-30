package yape.math;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

import yape.math.primes.*;

/**
 * Contains methods concerning abundant numbers.
 */
public class Abundance {
	/**
	 * Get all abundant numbers less than or equal to the given number. The
	 * algorithm is very similar to the sieve of Eratosthenes for primes.
	 * @param n The highest possible abundant number desired.
	 * @return A list of all abundant numbers <= n.
	 */
	public static List<Long> abundantsUpTo(Long n)
	{
		BitSet abundants = new BitSet(n.intValue()+1);
		
		// Run the sieve
		for(int curNum = 6; curNum <= n; curNum = getNextNonDeficient(curNum, abundants))
		{
			if(isAbundant((long) curNum))
				abundants.set(curNum);

			// All multiples of a perfect or abundant number, are abundant.
			int abundant = curNum*2;
			while(abundant <= n)
			{
				abundants.set(abundant);
				abundant += curNum;
			}
		}
		
		// Collect the results
		LinkedList<Long> ans = new LinkedList<Long>();
		for(int i=12; i<=n; i++)
		{
			if(abundants.get(i))
				ans.add((long) i);
		}
		
		return ans;
	}
	
	
	/**
	 * Given a number and a BitSet where true <=> abundant, return
	 * the next abundant number.
	 * @param cur The current number.
	 * @param abundants The BitSet where true implies abundance.
	 * @return The next unmarked abundant number greater than cur. All
	 * abundant numbers that are already marked, are multiples of some
	 * previous abundant or perfect number, and can therefore be ignored,
	 * because we've already filtered out all their multiples.
	 */
	private static int getNextNonDeficient(int cur, BitSet abundants)
	{
		for(cur++; cur < abundants.size(); cur++)
		{
			if(abundants.get(cur))
				continue;

			if(isDeficient((long) cur))
				continue;
			
			return cur;
		}
		
		// Shouldn't get here: this means that we've reached the end of the list.
		return cur;
	}
	
	
	/**
	 * Determine if a number is abundant.
	 */
	public static boolean isAbundant(Long n)
	{
		return (Decomposer.sumOfDivisors(n) > 2*n);
	}


	/**
	 * Determine if a number is deficient.
	 */
	public static boolean isDeficient(Long n)
	{
		return (Decomposer.sumOfDivisors(n) < 2*n);
	}
}
