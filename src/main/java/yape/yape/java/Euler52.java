package yape.yape.java;

import java.util.HashSet;
import java.util.Set;

/**
 * Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
 */
public class Euler52 {
	public static void main(String[] args) {
		for(long numDigits=2; ; numDigits++)
		{
			long minX = (long) Math.pow(10, numDigits - 1);
			long maxX = getMax(numDigits);
			
			for(long x=minX; x<=maxX; x++)
			{
				if(checkSolution(x))
				{
					System.out.println("Solution: " + x);
					System.exit(0);
				}
			}
		}
	}

	/**
	 * See if the given value is a solution to the problem.
	 */
	private static boolean checkSolution(long x) {
		Set<Long> digits = getDigits(x);
		for(long mult=2; mult<7; mult++)
			if(!getDigits(x*mult).equals(digits))
				return false;
		return true;
	}

	/**
	 * Get the largest value of x (given that x has n digits) such that 6x also has n digits.
	 */
	private static long getMax(long numDigits) {
		// For 6x to have the same number of digits as x, x must be less than 999... / 6
		return ((long) Math.pow(10, numDigits) - 1L) / 6L;
	}

	/**
	 * Get a Set containing the digits of the given number. For example, "6939" would
	 * return a set containing 6, 9, and 3.
	 */
	public static Set<Long> getDigits(Long number) {
		HashSet<Long> digits = new HashSet<Long>();
		while(number > 0)
		{
			digits.add(number % 10);
			number = number / 10;
		}
		return digits;
	}
}
