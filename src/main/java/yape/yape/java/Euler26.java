package yape.yape.java;

import java.math.BigInteger;


/**
 * Find the integer d, 0 < d < 1000, such that 1/d has the longest repeating cycle
 * in its decimal expansion.
 */
public class Euler26 {
	
	public static void main(String[] args) {
		int longestCycle = 1;
		int longestD = 1;
		for(int d = 999; d > 0; d -= 2)
		{
			int cycleLen = getCycleCount(removeFactorsOfTen(d));
			if(cycleLen > longestCycle)
			{
				longestCycle = cycleLen;
				longestD = d;
			}
		}
		
		System.out.println("Longest d = " + longestD + " (cycle length " + longestCycle + ")");
	}
	
	/**
	 * Get the length of the cycle of ten modulo the given number.
	 * @param num the modulus
	 * @return The length of 10's cycle modulo num.
	 */
	public static int getCycleCount(int num)
	{
		int first = 10 % num;
		int cur = first;
		int count = 0;
		
		do
		{
			cur = (cur*10) % num;
			if(cur == 0)
				// The representation is finite; the repeating cycle is all zeroes.
				return 1;
			count++;
		}
		while(cur != first);
		
		return count;
	}

	/**
	 * Remove all 2s and 5s from the prime factorization of i.
	 * @param i The number to reduce.
	 * @return The product of all prime factors of i except 2 and 5.
	 */
	public static int removeFactorsOfTen(int i)
	{
		while(i%2 == 0)
			i /= 2;
		
		while(i%5 == 0)
			i /= 5;
		
		return i;
	}

	/**
	 * Not needed - get some digits in the decimal expansion.
	 * @param n
	 * @return
	 */
	public static String digitsOfReciprocal(long n)
	{
		BigInteger bi = BigInteger.TEN.pow(50).divide(new BigInteger(String.valueOf(n)));
		return bi.toString();
	}
}
