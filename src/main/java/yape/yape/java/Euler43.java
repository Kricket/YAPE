package yape.yape.java;

import java.util.LinkedList;

/**
 * Find the sum of all 0-9 pandigital numbers such that
 * 2 | d2d3d4
 * 3 | d3d4d5
 * 5 | d4d5d6
 * ...
 * 17 | d8d9d10
 * (where di = the ith digit)
 */
public class Euler43
{
	/**
	 * The answer we're trying to get.
	 */
	static long total;
	
	public static void main(String[] args)
	{
		total = 0;
		LinkedList<Integer> mult17 = new LinkedList<Integer>();
		LinkedList<Integer> mult13 = new LinkedList<Integer>();
		LinkedList<Integer> mult11 = new LinkedList<Integer>();
		
		for(int i = 17; i < 1000; i += 17)
			mult17.add(i);
		
		for(int i = 13; i < 1000; i += 13)
			mult13.add(i);
		
		// d6 == 0 or 5, so limit our choices for multiples of 11
		for(int i = 11; i < 100; i += 11)
			mult11.add(i);
		for(int i = 506; i < 600; i+= 11)
			mult11.add(i);
		
		for(int i17 : mult17)
			for(int i13 : mult13)
			{
				// First two digits of mult17 must equal last two of mult13
				if((int)(i17 / 10) != (int)(i13 % 100))
					continue;
				
				for(int i11 : mult11)
				{
					// First two digits of mul13 must equal last two of mult13
					if((int)(i13 / 10) != (int)(i11 % 100))
						continue;

					findCandidates(i17
							+ ( ((int) (i13/100)) * 1000)
							+ ( ((int) (i11/100)) * 10000)
							);
				}
			}
		
		System.out.println("total = " + total);
	}

	
	/**
	 * Build a candidate, given i = the last 5 digits.
	 * @param i The last 5 digits of a valid pandigital number.
	 */
	private static void findCandidates(int i)
	{
		String digits = String.format("%05d", i);

		String allDig = "0123456789";
		for(int idx = 0; idx < digits.length(); idx++)
		{
			String c = digits.substring(idx, idx+1);
			if(!allDig.contains(c))
				return;
			
			allDig = allDig.replaceFirst(c, "");
		}
		
		// Add another digit, check for divisibility by 7
		for(int idx = 0; idx < allDig.length(); idx++)
		{
			int d5 = allDig.charAt(idx) - '0';
			d5 *= 100;
			if((d5 + (i/1000)) % 7 != 0)
				continue;
			
			String c = allDig.substring(idx, idx+1);
			addD4(d5*1000 + i, allDig.replaceFirst(c, ""));
		}
	}

	
	/**
	 * Continue building a candidate...need to add 4th digit now.
	 * @param i The digits d5..d10
	 * @param addDig The remaining unused digits.
	 */
	private static void addD4(int i, String allDig)
	{
		// d4 must be even
		for(int idx=0; idx<allDig.length(); idx++)
		{
			int d4 = allDig.charAt(idx) - '0';
			if(d4 % 2 != 0)
				continue;
			
			String c = allDig.substring(idx, idx+1);
			addD3(d4*1000000 + i, allDig.replaceFirst(c, ""));
		}
	}


	/**
	 * Continue building a candidate by adding the first 3 digits.
	 * @param i The digits d4..d10
	 * @param allDig The remaining unused digits.
	 */
	private static void addD3(long i, String allDig)
	{
		for(int idx=0; idx<allDig.length(); idx++)
		{
			long with_d3 = allDig.charAt(idx) - '0';
			with_d3 *= 10000000L;
			with_d3 += i;
			
			if((with_d3 / 100000) % 3 != 0)
				continue;

			String c = allDig.substring(idx, idx+1);
			addD2D1(with_d3, allDig.replaceFirst(c, ""));
		}
	}


	/**
	 * Continue building a candidate by adding the remaining two digits.
	 * @param i The last 8 digits.
	 * @param allDig The remaining 2 digits.
	 */
	private static void addD2D1(long i, String allDig)
	{
		long d1d2 = Long.parseLong(allDig);
		
		System.out.println("Found " + (i + (d1d2 * 100000000)));
		total += i + (d1d2 * 100000000);
		
		d1d2 = (d1d2 / 10) + ((d1d2 % 10)*10);
		
		System.out.println("Found " + (i + (d1d2 * 100000000)));
		total += i + (d1d2 * 100000000);
	}

}
