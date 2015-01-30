package yape.yape.java;

import java.util.HashSet;

import yape.math.primes.PrimeLister;

/**
 * Find 3 4-digit primes such that:
 * p3-p2 = p2-p1, and
 * each prime is a permutation of the same digits.
 * Return the concatenation of the three.
 */
public class Euler49
{
	
	public static void main(String[] args)
	{
		// Construct dual HashSets and Lists of all 4-digit primes.
		PrimeLister.ensurePrimesUpTo(10000);
		while(PrimeLister.getPrimeList().getFirst() < 1000)
			PrimeLister.getPrimeList().removeFirst();
		HashSet<Long> primes = new HashSet<Long>(PrimeLister.getPrimeList());
		
		
		for(long p:PrimeLister.getPrimeList())
		{
			long max_diff = (10000L - p)/2L;
			for(long j=2; j<=max_diff; j+=2)
			{
				if(!primes.contains(p+j))
					continue;
				if(!primes.contains(p+j+j))
					continue;
				
				if(arePermutations(p, p+j, p+j+j))
				{
					System.out.println("Solution: " + p + (p+j) + (p+j+j));
				}
			}
		}
	}

	private static boolean arePermutations(long p, long l, long m)
	{
		String ls = Long.toString(l);
		String ms = Long.toString(m);
		
		while(p > 0)
		{
			int c = (int) ((p%10) + '0');
			int li = ls.indexOf(c);
			if(li < 0)
				return false;
			int mi = ms.indexOf(c);
			if(mi < 0)
				return false;
			
			ls = ls.substring(0, li) + ls.substring(li+1);
			ms = ms.substring(0, mi) + ms.substring(mi+1);
			p = p/10;
		}
		
		return true;
	}
}
