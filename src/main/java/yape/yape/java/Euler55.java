package yape.yape.java;

import java.math.BigInteger;

/**
 * Figure out how many Lychrel numbers there are below 10,000. We are given that any
 * non-Lychrel numbers below this limit, become palindromes after 50 iterations.
 */
public class Euler55 {
	public static void main(String[] args) {
		int lychrel = 0;
		for(Long l=1L; l<10000; l++)
			if(isLychrel(l))
				lychrel++;
		
		System.out.println("Total number of lychrel numbers under 10000: " + lychrel);
	}

	/**
	 * Determine if the given number is a Lychrel number. For our purposes, this means
	 * that it does not become palindromic after 50 iterations.
	 */
	public static boolean isLychrel(Long n) {
		return !becomesPalindromic(nextSumBI(n), 50);
	}
	
	/**
	 * Determine if the given number becomes a palindrome in the given number of iterations.
	 * For example,
	 * 349 + 943 = 1292,
	 * 1292 + 2921 = 4213
	 * 4213 + 3124 = 7337
	 * => 349 becomes a palindrome in 3 iterations.
	 * @param n The number to test.
	 * @param iterations The maximum number of iterations to use.
	 * @return
	 */
	public static boolean becomesPalindromic(Long n, int iterations) {
		if(iterations < 1)
			return false;
		
		if(isPalindrome(n))
			return true;
		
		if(useBI(n))
			return becomesPalindromic(nextSumBI(n), iterations-1);
		else
			return becomesPalindromic(nextSumLong(n), iterations-1);
	}
	
	/**
	 * Determine if n + reverse(n) > the max value for Longs.
	 */
	private static boolean useBI(Long n) {
		return (n > Long.MAX_VALUE / 10);
	}

	public static boolean becomesPalindromic(BigInteger n, int iterations) {
		if(iterations < 1)
			return false;
		
		if(isPalindrome(n))
			return true;
		
		return becomesPalindromic(nextSumBI(n), iterations-1);
	}
	
	private static Long nextSumLong(Long n) {
		return n + Long.valueOf(reverse(n.toString()));
	}
	
	private static BigInteger nextSumBI(Long n) {
		return BigInteger.valueOf(n.longValue()).add(new BigInteger(reverse(n.toString())));
	}
	
	private static BigInteger nextSumBI(BigInteger n) {
		return n.add(new BigInteger(reverse(n.toString())));
	}
	
	private static String reverse(String s) {
		return (new StringBuffer(s)).reverse().toString();
	}

	public static boolean isPalindrome(Number n) {
		String s = n.toString();
		int length = s.length();
		for(int i=0; i<length/2; i++)
		{
			if(s.charAt(i) != s.charAt(length-i-1))
				return false;
		}
		
		return true;
	}
}
