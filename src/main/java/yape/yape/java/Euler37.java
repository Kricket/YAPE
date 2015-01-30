package yape.yape.java;

import java.util.HashSet;
import java.util.LinkedList;

import yape.math.primes.PrimeLister;

/**
 * There are 11 primes that, when we truncate digits (either from the left
 * or from the right), give other primes. Find their sum.
 */
public class Euler37 {
	
	public static void main(String[] args) {
		PrimeLister.ensurePrimesUpTo(1000000L);
		primes = new LinkedList<Long>(PrimeLister.getPrimeList());
		
		primeSet = new HashSet<Long>(primes);
		
		// Don't count single-digit primes.
		primes.remove(2L);
		primes.remove(3L);
		primes.remove(5L);
		primes.remove(7L);
		
		// We're given that there are only 11 that work...
		int total = 0;
		for(long p : primes)
		{
			if(isTruncatable(p))
			{
				System.out.println(p);
				total += p;
			}
		}
		
		System.out.println("total = " + total);
	}

	
	private static LinkedList<Long> primes;
	private static HashSet<Long> primeSet;
	
	
	/**
	 * Determine if the given number, when trucated from the left and from
	 * the right, still leaves primes.
	 * @param p A positive prime integer.
	 * @return True if the property holds; false otherwise.
	 */
	private static boolean isTruncatable(long p)
	{
		long q = p;
		
		// Truncate digits from the right...
		while((q = q / 10) > 0)
		{
			if(!primeSet.contains(q))
				return false;
		}

		// Truncate digits from the left...
		q = p;
		long pow10 = 10;
		while(q > pow10)
		{
			if(!primeSet.contains(q%pow10))
				return false;
			pow10 *= 10;
		}
		
		// No problems encountered, so it works!
		return true;
	}
}
