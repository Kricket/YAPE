package yape.yape.java;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import yape.math.primes.Decomposer;
import yape.math.primes.PrimeLister;

public class Euler56 {
	/**
	 * Dynamic list of powers of a given base number. Caches base^n for various
	 * values of n. When getPower(m) is called, if base^m hasn't yet been calculated,
	 * then it is calculated dynamically before being returned.
	 */
	private static class PowerList {
		private ArrayList<BigInteger> powers;
		
		public PowerList(Long base) {
			powers = new ArrayList<BigInteger>();
			powers.add(BigInteger.ONE);
			powers.add(BigInteger.valueOf(base));
			for(int i=2; i<100; i++)
				powers.add(powers.get(i-1).multiply(powers.get(1)));
		}
		
		/**
		 * Make sure that the list of powers goes up at least to the given size (i.e.,
		 * has a size of (s+1)). Fill in missing slots with null.
		 */
		private void fillList(int s) {
			for(int i=powers.size(); i<=s; i++)
				powers.add(null);
		}
		
		/**
		 * Get base^pow.
		 * @param pow The exponent to which to raise base.
		 * @return base^pow, as a BigInteger.
		 */
		public BigInteger getPower(int pow) {
			if(pow >= powers.size())
				fillList(pow);
			
			if(powers.get(pow) == null)
			{
				int lessPow = pow / 2;
				BigInteger lessPowBI = getPower(lessPow);
				lessPowBI = lessPowBI.multiply(lessPowBI);
				if(lessPow * 2 < pow)
					lessPowBI = lessPowBI.multiply(powers.get(1));
				powers.set(pow, lessPowBI);
			}
			
			return powers.get(pow);
		}
	}
	
	/**
	 * Represents a single Prime raised to a power. This makes it easier to use the cache
	 * to calculate, e.g. 16^10, which is really (2^4)^10 = 2^40. This class requires that
	 * "candidates" be initialized before using!
	 */
	private static class PrimeToPow {
		private Long prime;
		private int pow;
		
		public PrimeToPow(Long prime, int pow) {
			this.prime = prime;
			this.pow = pow;
		}
		
		/**
		 * Get n^pow, where n is the number represented by (this).
		 */
		public BigInteger getPower(int pow) {
			return candidates.get(prime).getPower(this.pow * pow);
		}
	}
	
	
	private static HashMap<Long, PowerList> candidates;
	private static long maxSum = 0;
	private static int maxPow = 0;
	private static long maxVal = 0;
	
	public static void main(String[] args) {
		PrimeLister.ensurePrimesUpTo(100);
		candidates = new HashMap<Long, PowerList>();
		for(Long p : PrimeLister.getPrimeList())
			candidates.put(p, new PowerList(p));
		
		// Little optimization: bigger numbers will make bigger decimal representations, which will
		// have more digits...thus, no need to start too low.
		for(long n=51; n<100; n++)
		{
			if(n%10 != 0) // No point in checking multiples of 10, since they just add zeroes.
				checkNumber(n);
		}
		
		System.out.println("The winner is " + maxVal + "^" + maxPow + " sum is " + maxSum);
	}
	
	
	private static void checkNumber(long n) {
		LinkedList<PrimeToPow> factors = new LinkedList<PrimeToPow>();
		Map<Long, Long> divisors = Decomposer.getPrimeDivisorsWithExponents(n);
		for(Long d : divisors.keySet()) {
			factors.add(new PrimeToPow(d, divisors.get(d).intValue()));
		}
		
		// So, here's a little optimization: I assume that only the last few powers are really interesting.
		// This should make sense...the higher the power, the more digits, and thus the higher the digit sum.
		for(int pow = 90; pow < 100; pow++)
		{
			BigInteger bi = BigInteger.ONE;
			for(PrimeToPow p : factors)
			{
				bi = bi.multiply(p.getPower(pow));
			}
			checkDigitSum(bi, n, pow);
		}
	}
	
	
	private static void checkDigitSum(BigInteger bi, long n, int pow) {
		String s = bi.toString();
		long sum = 0;
		for(int i=0; i<s.length(); i++)
		{
			sum += (s.charAt(i) - '0');
		}
		
		if(sum > maxSum)
		{
			maxSum = sum;
			maxPow = pow;
			maxVal = n;
		}
	}
}
