package yape.yape.java;

import java.util.HashSet;

/**
 * A product is 1-9 pandigital if it can be written as A * B = C, where each digit
 * appears exactly once in this equation. The problem is to find the sum of all
 * numbers for which there exists a 1-9 pandigital product equation.
 * 
 * The algorithm here runs through all 9! ~ 363K permutations of 1-9, and checks
 * to see if * and = can be inserted to make a correct equation. However, since
 * it turns out that our solutions will all have 4 digits, a faster way might be to
 * cycle through the 3024 4-digit numbers with unique digits, factor them, and examine
 * the factors to determine if the number is pandigital.
 */
public class Euler32 {
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		iteratePermutations("123456789", "");
		
		int sum = 0;
		for(int x : pandigital_products)
			sum += x;
		
		System.out.println("sum = " + sum);
		
		System.out.println("Run time = " + (System.currentTimeMillis() - startTime) / 1000.0);
	}

	
	private static HashSet<Integer> pandigital_products = new HashSet<Integer>();
	
	
	/**
	 * Recursively find all permutations of the given digits.
	 * @param digits The digits to permute.
	 * @param perm
	 */
	public static void iteratePermutations(String digits, String perm)
	{
		if(digits.length() == 0)
			lookForPanProduct(perm);
		else
			for(int i=0; i<digits.length(); i++)
			{
				iteratePermutations(
						digits.substring(0, i) + digits.substring(i+1),
						perm + digits.charAt(i)
						);
			}
	}
	
	
	/**
	 * Look for a pandigital product.
	 * @param perm The permutation of digits to search.
	 */
	private static void lookForPanProduct(String perm)
	{
		// Possibilities (in number of digits):
		// 1x4 = 4
		// 2x3 = 4

		int a = Integer.parseInt(perm.substring(0, 1));
		int b = Integer.parseInt(perm.substring(1, 5));
		int c = Integer.parseInt(perm.substring(5));
		
		if(a*b==c)
			pandigital_products.add(c);
		
		a = Integer.parseInt(perm.substring(0, 2));
		b = Integer.parseInt(perm.substring(2, 5));
		c = Integer.parseInt(perm.substring(5));

		if(a*b==c)
			pandigital_products.add(c);
	}
}
