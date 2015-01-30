package yape.yape.java;

import java.util.List;
import java.util.HashSet;
import java.util.Iterator;

import yape.math.primes.PrimeLister;

/**
 * Find the smallest prime which, by replacing some of the digits with the same
 * digit, produces eight different primes.
 */
public class Euler51 {

	/**
	 * Store primes in a HashSet for quick lookup.
	 */
	private static HashSet<Long> primes;
	/**
	 * For walking through the list.
	 */
	private static Iterator<Long> prime_it;
	
	private static long startTime;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		startTime = System.currentTimeMillis();
		PrimeLister.ensurePrimesUpTo(1000000);
		List<Long> pList = PrimeLister.getPrimeList(); //primesUpTo(1000000);
		primes = new HashSet<Long>(pList);
		prime_it = pList.iterator();
		while(prime_it.next() < 56003L) {}
		
		Long p = 0L;
		
		while(prime_it.hasNext())
		{
			p = prime_it.next();
			if(checkString(p.toString()))
				break;
		}
	}

	/**
	 * Check the given string to see if it might be a solution. Recursively calls itself
	 * with one additional character replaced by a "*".
	 * @param primeString A prime number in string form, potentially with some digits replaced
	 * by "*". 
	 */
	private static boolean checkString(String primeString) {
		if(primeString.contains("*"))
		{
			// Since we have stars, we have to replace them with each digit and see
			// if they produce a prime.
			int count = 0;
			char c;
			if(primeString.charAt(0) == '*')
				c = '1';
			else
				c = '0';
			for(; c<='9'; c++)
			{
				Long p = Long.parseLong(primeString.replace('*', c));
				if(primes.contains(p))
					count++;
			}
			
			if(count > 7)
			{
				// Solution found
				System.out.println("Found " + primeString);
				for(c = '0'; c<='9'; c++)
				{
					Long p = Long.parseLong(primeString.replace('*', c));
					if(primes.contains(p))
						System.out.println(p);
				}
				System.out.println("Runtime " + (System.currentTimeMillis() - startTime) / 1000.0);
				return true;
			}
		}
		
		
		// No solution found with primeString, so add a "*" and try again.
		for(int i=0; i<primeString.length(); i++)
		{
			if(primeString.charAt(i) == '*')
				continue;
			
			if(checkString(primeString.substring(0, i) + "*" + primeString.substring(i+1)))
				return true;
		}
		
		return false;
	}
}
