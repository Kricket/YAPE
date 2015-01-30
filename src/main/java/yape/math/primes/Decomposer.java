package yape.math.primes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import yape.math.MathHelpers;

/**
 * The Decomposer class contains functions involved with the decomposition
 * of an integer into its factors (prime or otherwise).
 */
public class Decomposer {
	/**
	 * Get a list of all prime divisors of a number, including repeats.
	 * @param n The number whose divisors we want.
	 * @return An ordered list of all primes (including multiplicity) dividing n.
	 * For example, 12 would return [2 2 3].
	 */
	public static List<Long> getPrimeDivisors(long n)
	{
		PrimeLister.ensurePrimesUpTo((int)Math.sqrt(n));
		List<Long> primes = PrimeLister.getPrimeList();
		List<Long> divisors = new LinkedList<Long>();
		
		Iterator<Long> it = primes.iterator();
		while(it.hasNext())
		{
			long p = it.next().longValue();
			
			while(n % p == 0)
			{
				divisors.add(p);
				n = n / p;
			}
		}
		
		if(n != 1)
			divisors.add(n);
		
		return divisors;
	}
	
	/**
	 * Get a list of all prime divisors of a number, not including repeats.
	 * @param n The number whose divisors we want.
	 * @return An ordered list of all primes (without multiplicity) that divide n.
	 * For example, 12 would return [2 3].
	 */
	public static List<Long> getUniquePrimeDivisors(long n)
	{
		PrimeLister.ensurePrimesUpTo(n);
		LinkedList<Long> divisors = new LinkedList<Long>();
		
		for(Long p:PrimeLister.getPrimeList())
		{
			if(n%p == 0)
			{
				divisors.add(p);
				while(n%p == 0)
					n /= p;
			}
			
			if(n == 1)
				break;
		}
		
		return divisors;
	}
	
	/**
	 * Get a map of [prime => multiplicity] of all the divisors of the given number.
	 * @param n The number whose primes we want.
	 */
	public static Map<Long, Long> getPrimeDivisorsWithExponents(long n)
	{
		HashMap<Long, Long> map = new HashMap<Long, Long>();
		List<Long> divisors = getPrimeDivisors(n);
		
		for(Long p : divisors)
		{
			if(map.containsKey(p))
				map.put(p, map.get(p) + 1);
			else
				map.put(p, 1L);
		}
		
		return map;
	}
	
	
	/**
	 * Get all the divisors (prime or otherwise) of the given number.
	 * @param n The number whose divisors we want.
	 * @return A list of all divisors.
	 * For example, 12 would return [1 2 3 4 6 12]
	 */
	public static List<Long> getDivisors(long n)
	{
		LinkedList<Long> divisors = new LinkedList<Long>();
		divisors.add(1L);

		for(long i=2; i<=(long)Math.sqrt(n); i++)
		{
			if(n % i == 0)
			{
				divisors.add(i);
				if(i*i != n)
					divisors.add(n / i);
			}
		}

		Collections.sort(divisors);
		return divisors;
	}

	
	/**
	 * Get the sum of divisors of a number.
	 * @param n The number whose sum of divisors (sigma_1) we want.
	 */
	public static long sumOfDivisors(long n)
	{
		List<Long> divisors = getPrimeDivisors(n);
		Iterator<Long> it = divisors.iterator();
		long sum = 1;
		
		long p = it.next().longValue();
		int mult = 1;
		while(it.hasNext())
		{
			long q = it.next().longValue();
			if(q == p)
				mult++;
			else
			{
				sum *= MathHelpers.geomSum(p, mult);
				mult = 1;
				p = q;
			}
		}
		
		sum *= MathHelpers.geomSum(p, mult);
		
		return sum;
	}
}
