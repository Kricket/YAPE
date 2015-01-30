package yape.yape.java;

import java.math.BigInteger;

/**
 * Progressively expanding the continued fraction for sqrt(2):
 *                      1 + 1/2    = 3/2
 *               1 + 1/(2 + 1/2)   = 7/5
 *        1 + 1/(2 + 1/(2 + 1/2))  = 17/12
 * 1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29
 * On the eighth expansion, we get 1393/985, the first where the numerator has more
 * digits than the denominator. How many times does this occur in the first one
 * thousand expansions?
 */
public class Euler57 {

	private static BigInteger numerator, denominator;
	
	public static void main(String[] args) {
		numerator = BigInteger.ONE;
		denominator = BigInteger.ONE;
		
		int timesNumHadMoreDigits = 0;
		long startTime = System.currentTimeMillis();
		
		for(int i=0; i<1000; i++)
		{
			expandOnce();
			System.out.println(numerator.toString() + " / " + denominator.toString());
			if(numerator.toString().length() > denominator.toString().length())
				timesNumHadMoreDigits++;
		}
		
		System.out.println("Number of times the numerator had more digits: " + timesNumHadMoreDigits);
		System.out.println("runtime: " + (System.currentTimeMillis() - startTime) * 0.001);
	}

	/**
	 * Go one step further in the expansion of the continued fraction:
	 * - add 1 to (num/denom) (i.e., add denom to num)
	 * - swap num and denom (equivalent to taking the reciprocal)
	 * - add 1 again (i.e., add denom to num)
	 * - divide each by their GCD - NO! This is always 1...
	 */
	private static void expandOnce() {
		numerator = numerator.add(denominator);
		reciprocal();
		numerator = numerator.add(denominator);
	}
	
	private static void reciprocal() {
		BigInteger temp = numerator;
		numerator = denominator;
		denominator = temp;
	}
}
