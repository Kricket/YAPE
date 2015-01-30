package yape.math.primes;

import java.util.BitSet;
import java.util.LinkedList;

public class PrimeLister
{
	/**
	 * Cached list of primes.
	 */
	private static LinkedList<Long> primes;
	/**
	 * Limit for sieve size.
	 */
	private static final int MAX_SIEVE_SIZE = 10000000;

	/**
	 * Get the list of primes that have been generated.
	 */
	public static LinkedList<Long> getPrimeList()
	{
		return primes;
	}
	
	/**
	 * Sieve up to a certain number of primes. Erases any previously-existing prime list.
	 * @param sieve_size The size of the sieve. A size of i will sieve up to 2*(i+1) inclusive.
	 */
	private static void sieveUpTo(int sieve_size)
	{
		primes = new LinkedList<Long>();
		primes.add(2L);
		
		BitSet sieve = new BitSet(sieve_size);
		
		// sieve[n] = 2n + 3
		// Thus, all multiples of 2 are already gone, as are 0 and 1.
		// sieve[n] == false <=> 2n + 3 is prime.
		// index_to_real: 2*i + 3 = r
		// real_to_index: (r-3)/2 = i
		int sqrt = (int) Math.sqrt(sieve_size);
		for(int i=0; i<=sqrt; i++)
		{
			// Skip if not prime
			if(sieve.get(i))
				continue;
			
			long prime = 2L*i + 3L;
			primes.add(prime);
			
			// Go through all multiples of p, staring with p^2.
			for(int j = (2*i*i + 6*i + 3); j < sieve_size; j += prime)
				sieve.set(j);
		}
		
		// Now, all "false" entries remaining in the sieve are prime.
		
		// If the sieve size is a perfect square, then we will already have added
		// sieve[sqrt], so we must increment.
		if(sqrt*sqrt == sieve_size)
			sqrt += 1;
		
		// Pick up the rest of the primes.
		for(int i = sqrt; i < sieve_size; i++)
			if(!sieve.get(i))
				primes.add(2L*i + 3L);
	}
	
	/**
	 * Using trial division, grow the list until the last prime is >= the given limit.
	 * @param limit
	 */
	private static void trialDivideUpTo(long limit)
	{
		for(long candidate = primes.getLast() + 2L; candidate <= limit; candidate += 2L)
		{
			if(hasDivisor(candidate))
				continue;
			
			primes.add(candidate);
		}
	}

	/**
	 * Expand the list of primes until we have all primes less than the given limit.
	 * @param limit The upper bound (exclusive) for the last prime.
	 */
	public static void ensurePrimesUpTo(long limit)
	{
		if(primes == null || primes.getLast() < limit)
		{
			long sieve_limit = limit/2 - 1;
			if(sieve_limit > MAX_SIEVE_SIZE)
			{
				sieveUpTo(MAX_SIEVE_SIZE);
				trialDivideUpTo(limit);
			}
			else
				sieveUpTo((int) sieve_limit);
		}
	}

	/**
	 * Determine if one of the already-generated primes divides the given number.
	 * @param candidate Number to check for divisors.
	 * @return true if primes contains a divisor of candidate; false otherwise.
	 */
	public static boolean hasDivisor(long candidate)
	{
		long sqrt = (long) Math.sqrt(candidate);
		for(Long p : primes)
		{
			if(p > sqrt)
				break;
			
			if(candidate % p == 0)
				return true;
		}
		
		return false;
	}

	
	/**
	 * Determine if the given number is prime. Note that the list of primes must
	 * be generated first!
	 */
	public static boolean isPrime(long n) {
		if(n > primes.getLast())
			return !hasDivisor(n);
		
		return primes.contains(n);
	}

}
