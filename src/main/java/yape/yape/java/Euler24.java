package yape.yape.java;

import java.util.LinkedList;

import yape.math.MathHelpers;

/**
 * Find the millionth lexicographic permutation of the digits 0 - 9.
 */
public class Euler24 {
	
	public static void main(String[] args) {
		long which = 999999;
		// Order is important!
		LinkedList<String> digits = new LinkedList<String>();
		digits.add("0");
		digits.add("1");
		digits.add("2");
		digits.add("3");
		digits.add("4");
		digits.add("5");
		digits.add("6");
		digits.add("7");
		digits.add("8");
		digits.add("9");
		System.out.println(which + " permutation is " + getNthLexicoPerm(which, digits));
	}
	
	/**
	 * Get the Nth Lexicographical Permutation of the (ordered) symbols in digits.
	 * n is supposed to be less than the number of possible permutations (which is equal
	 * to (digits.size())!).
	 * @param n The 0-based index into the list of lexicographical permutations.
	 * @param digits The ordered list of symbols that are permuted.
	 * @return The symbols, concatenated into a single string of their Nth permutation.
	 */
	public static String getNthLexicoPerm(long n, LinkedList<String> digits)
	{
		// Base case: no more characters left, so just bail.
		if(digits.size() == 0)
			return "";
		
		// Recursive case: Figure out which element to take.
		// For each digit we choose at our current place, there are fact = (size-1)! possibilities.
		// Thus, we want digit n/fact, after which we need to advance another n%fact in the list.
		
		long fact = MathHelpers.factorial(digits.size()-1);
		
		return digits.remove((int) (n/fact))
			+ getNthLexicoPerm((n%fact), digits);
	}
}
