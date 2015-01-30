package yape.yape.java;

import yape.math.primes.PrimeLister;

/**
 * Construct a spiral in the following way:
 * 37 36 35 34 33 32 31
 * 38 17 16 15 14 13 30
 * 39 18  5  4  3 12 29
 * 40 19  6  1  2 11 28
 * 41 20  7  8  9 10 27
 * 42 21 22 23 24 25 26
 * 43 44 45 46 47 48 49
 * 
 * The length of a side of this particular spiral, is 7. Note that 8 of the 13
 * numbers (~62%) along the diagonals are prime. What side length is required
 * for the ratio to drop below 10%?
 */
public class Euler58 {
	public static void main(String[] args) {
		PrimeLister.ensurePrimesUpTo(1000000);
		System.out.println("Primes generated.");
		
		double totalPrimes = 0;
		for(long width = 3; ; width += 2)
		{
			totalPrimes += numPrimesOnCorners(width);
			double ratio = totalPrimes / (2*width-1);
			if(width % 1001 == 0)
				System.out.println("width: " + width + " ratio: " + ratio + " totalPrimes = " + totalPrimes);
			if(ratio < 0.1)
			{
				System.out.println("Ratio drops below 10% at width = " + width);
				break;
			}
		}
	}

	/**
	 * For a spiral of the given width, determine how many of the four corner values
	 * are primes.
	 * @param width The width of the spiral.
	 * @return The number of primes along the corners.
	 */
	public static long numPrimesOnCorners(long width) {
		long corner = width*width;
		width -= 1;
		
		long count = 0;
		
		for(int i=0; i<3; i++)
		{
			corner -= width;
			if(PrimeLister.isPrime(corner))
				count++;
		}
		
		return count;
	}
}
