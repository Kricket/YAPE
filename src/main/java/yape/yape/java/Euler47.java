package yape.yape.java;

import java.util.List;

import yape.math.primes.*;

/**
 * Find the first integer n such that n, n+1, n+2, n+3 each have 4 unique prime divisors.
 */
public class Euler47
{
	
	public static void main(String[] args)
	{
		PrimeLister.ensurePrimesUpTo(1000000);
		long n = 104; // 3*5*7 - 1
		List<Long> divisors;
		while(true)
		{
			do {
				n++;
				divisors = Decomposer.getUniquePrimeDivisors(n);
				divisors.remove(2L);
			} while(divisors.size() != 3);
			
			long m = n+1;
			divisors = Decomposer.getUniquePrimeDivisors(m);
			divisors.remove(2L);
			if(divisors.size() != 3)
				continue;
			
			// We now have two consecutive numbers with 3 divisors (not counting 2).
			// We now look at the divisors around 2n and 2m.
			m = 2*n + 1;
			if(Decomposer.getUniquePrimeDivisors(m).size() != 4)
				continue;
			
			if(Decomposer.getUniquePrimeDivisors(m+2).size() != 4)
			{
				if(Decomposer.getUniquePrimeDivisors(m-2).size() != 4)
					continue;
				else
					System.out.println("Solution: " + (m-2));
			}
			else
				System.out.println("Solution: " + (m-1));

			break;
		}
	}
}
