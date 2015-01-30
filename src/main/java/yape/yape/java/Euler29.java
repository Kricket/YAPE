package yape.yape.java;

import java.util.BitSet;

/**
 * Find the number of distinct terms of the form a^b, for all values
 * of a and b such that 2 <= a,b <= 100.
 */
public class Euler29 {
	
	public static void main(String[] args) {
		int a_max = 100;
		int b_max = 100;
		
		System.out.println("Number of unique values: " + getNumberOfUniqueValues(a_max, b_max));
	}
	
	/**
	 * Count the number of distinct values in the list [a^b], for 2 <= a,b and
	 * a <= a_max, b <= b_max.
	 * @param a_max The highest value of a.
	 * @param b_max The highest value of b.
	 * @return The number of distinct values generated.
	 */
	public static long getNumberOfUniqueValues(int a_max, int b_max)
	{
		long total = 0; // total number of elements
		int a_root = (int) Math.sqrt(a_max);
		
		// counted[i] == true implies that that number has already been counted,
		// so we can ignore it.
		BitSet counted = new BitSet(a_max);
		
		for(int a=2; a<=a_max; a++)
		{
			// This number has already been counted.
			if(counted.get(a))
				continue;
			
			if(a <= a_root)
			{
				// Not only a, but also (at least one) power of a is <= a_max. Thus,
				// counting a will require a little more work.
				// We need to count the following powers of a:
				// 2, 3, 4, ..., b_max
				// 4, 6, 8, ..., 2*b_max
				// 6, 9, 12, ..., 3*b_max
				// ... up until a^i > a_max
				int num_powers = (int) (Math.log(a_max) / Math.log(a));
				// Maintain a bitset of the powers already counted
				BitSet a_powers = new BitSet(b_max * num_powers + 1);
				int a_to_pow = a;
				int a_pow = 1;
				while(a_to_pow <= a_max)
				{
					counted.set(a_to_pow);
					for(int p = 2 * a_pow; p <= a_pow * b_max; p += a_pow)
					{
						a_powers.set(p);
					}
					a_to_pow *= a;
					a_pow++;
				}
				
				// Now, just count all the set bits.
				for(int i=0; i<=b_max*num_powers; i++)
					if(a_powers.get(i))
						total++;
			}
			else
			{
				// Nothing special, so just count all the powers of a and return.
				counted.set(a); // not really necessary
				total += (b_max - 1);
			}
		}
		
		return total;
	}

}
