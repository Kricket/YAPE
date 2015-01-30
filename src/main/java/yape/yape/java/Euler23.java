package yape.yape.java;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;

import yape.math.Abundance;
import yape.math.primes.*;

/**
 * Find the sum of all positive integers that cannot be written as the sum of
 * two abundant numbers.
 */
public class Euler23 {
	
	/**
	 * It is known that all numbers above this number can be written as
	 * the sum of two abundant numbers.
	 */
	public static final Long MAX_NOSUM = 28123L;
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Prime the prime pump :)
		PrimeLister.ensurePrimesUpTo(MAX_NOSUM + 2);
		ArrayList<Long> abundants = new ArrayList<Long>(Abundance.abundantsUpTo(MAX_NOSUM));

		Set<Long> abund_sums = new HashSet<Long>();
		
		for(int i=0; i<abundants.size(); i++)
		{
			Long a = abundants.get(i);
			for(int j=i; j<abundants.size(); j++)
			{
				Long b = abundants.get(j);

				if(a + b <= MAX_NOSUM)
					// (a+b) can be written as the sum of two abundants, so remove
					// it from our total sum.
					abund_sums.add(a+b);
			}
		}
		
		System.out.println("Run time for sums: " + (System.currentTimeMillis() - startTime) / 1000.0);

		Long sum = ((MAX_NOSUM) * (MAX_NOSUM + 1)) / 2;
		for(Long l : abund_sums)
		{
			sum -= l;
		}

		
		System.out.println("Total run time: " + (System.currentTimeMillis() - startTime) / 1000.0);
		System.out.println("Sum = " + sum);
	}

}
