package yape.yape.java;

import yape.math.primes.PrimeLister;

/**
 * Find the largetst n-digit pandigital prime number (for n < 10, of course).
 */
public class Euler41 {
	
	private static long largestNDigitPrime = 0;

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		PrimeLister.ensurePrimesUpTo(100000L);
		String digits = "123456789";
		while(largestNDigitPrime == 0)
		{
			iteratePermutations(digits, "");
			digits = digits.substring(0, digits.length() - 1);
		}
		
		System.out.println("Largest n-digit prime is " + largestNDigitPrime);
		
		System.out.println("Run time = " + (System.currentTimeMillis() - startTime) / 1000.0);
	}

	
	
	/**
	 * Recursively find all permutations of the given digits.
	 * @param digits The digits to permute.
	 * @param perm
	 */
	public static void iteratePermutations(String digits, String perm)
	{
		if(digits.length() == 0)
			checkSolution(perm);
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
	 * Check if the given number is prime. If so, potentially set it as the solution.
	 * @param perm
	 */
	private static void checkSolution(String perm) {
		// First, see if it's prime.
		switch(perm.charAt(perm.length()-1))
		{
		case '0': case '2': case '4': case '6': case '8': case '5':
			return;
		}
		
		long num = Long.parseLong(perm);
		if(PrimeLister.hasDivisor(num))
			return; // not prime
		
		if(num > largestNDigitPrime)
			largestNDigitPrime = num;
	}

}
