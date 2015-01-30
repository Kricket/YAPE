package yape.yape.java;

import java.util.HashSet;

/**
 * Find all numbers that can be written as the sum of the fifth power of their digits.
 * 
 * The idea:
 * X = xn*10^n + ... + x0*10^0
 * digits^5 (X) = xn^5 + ... + x0^5
 * 
 * X - digits^5 (X) = xn*(10^n - xn^4) + ... + x0*(1 - x0^4)
 * 
 * For this diff, there are only a limited number of negative values possible.
 * We construct the table diffs, where diffs[i][xi] = xi*(10^i - xi^4).
 */
public class Euler30 {
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		buildDiffs();
		walk(0, 0);
		long sum = 0;
		for(long x : solutions)
			sum += x;
		//System.out.println(solutions);
		System.out.println("sum = " + sum);
		System.out.println("Runtime: " + (System.currentTimeMillis() - startTime) / 1000.0);
	}

	
	
	/**
	 * Max number of digits to search.
	 */
	public static final int MAX_POW = 7;
	/**
	 * The exponent applied to each digit.
	 */
	public static final int DIGIT_POW = 5;
	/**
	 * The quantity xi*(10^i - xi^(DIGIT_POW-1)) for xi = 0..9 and i=0..MAX_POW-1.
	 */
	private static final long[][] diffs = new long[10][MAX_POW];
	/**
	 * Global variable to keep track of our current "position".
	 */
	private static int[] digits = new int[MAX_POW];
	/**
	 * All solutions found.
	 */
	private static HashSet<Long> solutions = new HashSet<Long>();
	
	
	
	/**
	 * Build the table diffs, where diffs[xi][i] = xi*(10^i - xi^(DIGIT_POW-1)).
	 */
	public static void buildDiffs()
	{
		long pow10 = 1;
		for(long i = 0; i < MAX_POW; i++)
		{
			for(long xi = 0; xi < 10; xi++)
			{
				diffs[(int)xi][(int)i] = xi * (pow10 - (long) Math.pow(xi, DIGIT_POW - 1));
			}
			
			pow10 *= 10;
		}
	}
	
	
	/**
	 * Recursively search for solutions, by selecting one digit at a time.
	 * @param total
	 * @param pow
	 */
	public static void walk(long total, int pow)
	{
		if(total == 0)
			addFoundResult(pow);
		
		if(pow == MAX_POW)
			return;

		for(digits[pow]=0; digits[pow]<10; digits[pow]++)
		{
			walk(total + diffs[digits[pow]][pow], pow+1);
		}
	}
	
	
	/**
	 * If a solution is found, capture it. The solution is stored in the
	 * private variable digits, and contains pow digits.
	 * @param pow
	 */
	private static void addFoundResult(int pow)
	{
		long value = 0;
		long pow10 = 1;

		for(int i=0; i<pow; i++)
		{
			value += digits[i] * pow10;
			pow10 *= 10;
		}

		if(value > 1)
			solutions.add(value);
	}
}