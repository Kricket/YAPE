package yape.yape.java;

import java.util.HashSet;
import java.util.Iterator;

import yape.math.primes.PrimeLister;

/**
 * Find the first odd composite number that cannot be written as the sum
 * of a prime and twice a square.
 */
public class Euler46
{
	/**
	 * The number of perfect squares to cache.
	 */
	public static final int SQUARE_LIST_LENGTH = 10000;

	public static void main(String[] args)
	{
		// Build a list of 2*squares
		HashSet<Long> double_squares = new HashSet<Long>(SQUARE_LIST_LENGTH);
		for(long l=1; l<=SQUARE_LIST_LENGTH; l++)
			double_squares.add(l*l*2L);
		
		// The first unverified odd composite
		long odd_composite = 35;
		
		// Grab some primes
		PrimeLister.ensurePrimesUpTo(1000000);
		// Skip 2, since twice a square is even and we're not looking for 2.
		PrimeLister.getPrimeList().removeFirst();
		
		Iterator<Long> plit = PrimeLister.getPrimeList().iterator();
		long prime = plit.next();
		while(prime < odd_composite)
			prime = plit.next();
		
		// prime = the first prime greater than odd_composite
		
		while(true)
		{
			// Loop through all primes less than odd_composite
			for(long p:PrimeLister.getPrimeList())
			{
				if(p > odd_composite)
				{
					// If we get here, it means that we didn't find a solution for our
					// current odd composite number. Thus, this is the answer we're looking for.
					System.out.println(odd_composite + " is the first.");
					return;
				}
				
				if(double_squares.contains(odd_composite-p))
				{
					//System.out.println(odd_composite + " = " + p + " + 2 * " + (odd_composite-p)/2);
					break;
				}
			}
			
			// Move up to the next odd composite number
			odd_composite += 2;
			while(odd_composite >= prime)
			{
				prime = plit.next();
				odd_composite += 2;
			}
		}
	}

}
