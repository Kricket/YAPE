package yape.yape.java;

import java.util.HashSet;
import java.util.LinkedList;

import yape.math.primes.PrimeLister;

/**
 * Find the number of circular primes under 1 million.
 */
public class Euler35 {
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		PrimeLister.ensurePrimesUpTo(1000000L);
		HashSet<Long> primeSet = new HashSet<Long>(PrimeLister.getPrimeList());
		LinkedList<Long> primes = new LinkedList<Long>(PrimeLister.getPrimeList());

		System.out.println("Time to get primes: " + (System.currentTimeMillis() - startTime) / 1000.0);
		System.out.println("number of primes: " + primeSet.size());
		System.out.println("Biggest prime: " + primes.getLast());
		
		// Skip single-digit primes
		primes.remove(2L);
		primes.remove(3L);
		primes.remove(5L);
		primes.remove(7L);
		int total = 4;

		for(Long p:primes)
			total += checkCircular(primeSet, p);
		
		System.out.println("total: " + total);
		System.out.println("Total time: " + (System.currentTimeMillis() - startTime) / 1000.0);
	}

	
	/**
	 * Check if the given prime is a circular prime. Remove it, and all its rotations,
	 * from the set of primes.
	 * @param primeSet
	 * @param prime
	 * @return
	 */
	private static int checkCircular(HashSet<Long> primeSet, Long prime) {
		if(!primeSet.remove(prime))
			return 0; // already removed
		String p = prime.toString();

		// Optimization...
		if(p.indexOf('2') >= 0 ||
				p.indexOf('4') >= 0 ||
				p.indexOf('5') >= 0 ||
				p.indexOf('6') >= 0 ||
				p.indexOf('8') >= 0 ||
				p.indexOf('0') >= 0 )
			return 0; // Because one of the rotations will be even

		Long circ;

		for(int i=1; i<p.length(); i++)
		{
			p = p.substring(1) + p.charAt(0);

			circ = Long.decode(p);

			if(circ == prime) // Primes like 11...
				return i;

			if(!primeSet.remove(circ))
				return 0;
		}

		return p.length();
	}

}
