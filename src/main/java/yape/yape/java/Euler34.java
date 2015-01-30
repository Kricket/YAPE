package yape.yape.java;

import java.util.HashSet;

import yape.math.MathHelpers;

/**
 * Find the sum of all numbers which are equal to the sum of the factorial of their digits.
 * 
 * The logic here is very similar to that of problem 30. Let
 * X = xn*10^n + ... + x0*10^0, where the {xi} are the digits of X;
 * fX = xn! + ... + x0!
 * Their difference is
 * diff = xn(10^n - (xn-1)!) + ... + x0(1 - (x0-1)!)
 */
public class Euler34 {
	
	public static void main(String[] args) {
		/*
		buildDiffs();
		for(int d=0; d<10; d++)
		{
			System.out.print(d + " : ");
			for(int p=0; p<MAX_POW; p++)
				System.out.print(diffs[d][p] + " ");
			System.out.println("");
		}
*/
		long startTime = System.currentTimeMillis();
		
		buildDiffs();
		walk(0, 0);
		long sum = 0;
		for(long x : solutions)
			sum += x;
		System.out.println(solutions);
		System.out.println("sum = " + sum);
		System.out.println("Runtime: " + (System.currentTimeMillis() - startTime) / 1000.0);

	}

	
	private static final int MAX_POW = 7;
	private static long[][] diffs = new long[10][MAX_POW];
	/**
	 * Global variable to keep track of our current "position".
	 */
	private static int[] digits = new int[MAX_POW];
	/**
	 * All solutions found.
	 */
	private static HashSet<Long> solutions = new HashSet<Long>();

	private static void buildDiffs()
	{
		long[] facts = new long[10];
		for(int i=0; i<10; i++)
			facts[i] = MathHelpers.factorial(i);
		
		
		// for a digit in the nth place, calculate xn(10^n - (xn-1)!)
		int pow10 = 1;
		for(int p=0; p<MAX_POW; p++)
		{
			diffs[0][p] = -1; // xn*10^n = 0, and 0! = 1...
			for(int d=1; d<10; d++)
				diffs[d][p] = d * (pow10 - facts[d-1]);

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
		if(pow > 0)
			if(digits[pow-1] != 0) // Ignore leading zeroes! They throw the result off...
				if(total == 0)
					addFoundResult(pow);

		// Optimization: The highest pow where we can still add a negative
		// number to the total is 4. And actually, this can only be done with
		// a 9 in that case. So, if we're on 4 and the value is positive,
		// our only choice is to walk to 9...
		if(pow == 4 && total > 0)
		{
			digits[pow] = 9;
			walk(total + diffs[digits[pow]][pow], pow+1);
			return;
		}

		// ...Otherwise, if we're on 5, then the only way to continue is
		// if the total is negative.
		if(pow > 4 && total >= 0)
			return;

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

		if(value > 2)
			solutions.add(value);
	}
}