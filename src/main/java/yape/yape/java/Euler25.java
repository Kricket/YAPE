package yape.yape.java;

import java.math.BigInteger;

/**
 * Find the index of the first Fibonacci number with 1000 digits.
 */
public class Euler25 {
	
	public static void main(String[] args) {
		BigInteger f1 = BigInteger.ONE;
		BigInteger f2 = BigInteger.ONE;
		BigInteger fn = nextFib(f1, f2);
		BigInteger minVal = (BigInteger.TEN).pow(999);
		long n = 3;

		while(fn.compareTo(minVal) <= 0)
		{
			n++;
			f1 = f2;
			f2 = fn;
			fn = nextFib(f1, f2);
		}
		
		System.out.println("First index is " + n);
	}

	/**
	 * Get the next fibonacci number in the sequence.
	 * @param f1 The preceding fibonacci number.
	 * @param f2 The fibonacci number preceding f1.
	 * @return The fibonacci number that follows f1.
	 */
	public static BigInteger nextFib(BigInteger f1, BigInteger f2)
	{
		return f1.add(f2);
	}
}
