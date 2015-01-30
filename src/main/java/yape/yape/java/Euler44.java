package yape.yape.java;

import java.util.HashSet;

/**
 * Find the minimum difference Pa-Pb of two pentagonal numbers, such that
 * (Pa-Pb) and (Pa+Pb) are both pentagonal numbers.
 */
public class Euler44
{
	public static final int MAX_DIFF = 100000;

	public static void main(String[] args)
	{
		HashSet<Long> pentas = new HashSet<Long>();
		pentas.add(1L);
		long a = 1;
		while(true)
		{
			a++;
			long Pa = a*(3*a - 1) / 2;
			pentas.add(Pa);
			long minB = Math.max(1, a-MAX_DIFF);
			
			for(long b = a-1; b >= minB; b--)
			{
				long Pb = b*(3*b - 1)/2;
				if(!pentas.contains(Pa - Pb))
					continue;
				if(!isPenta(Pa + Pb))
					continue;
				
				System.out.println("Found " + (Pa - Pb) + " = " + Pa + " - " + Pb);
				return;
			}
		}
	}

	
	/**
	 * Determine if the given number is a pentagonal number.
	 * @param k The number to test.
	 * @return true if the number is pentagonal; false otherwise.
	 */
	public static boolean isPenta(long k)
	{
		// If 3n^2 - n - 2k has an integer root, then k is pentagonal.
		long sq = 1L + 24L*k;
		long sqrt = (long) Math.sqrt(sq);
		if(sqrt * sqrt != sq)
			return false;
		if(sqrt == 1)
			return true;
		if(sqrt > 1)
			return (1L + sqrt) % 6 == 0;
		return 	(1L - sqrt) % 6 == 0;
	}
}
