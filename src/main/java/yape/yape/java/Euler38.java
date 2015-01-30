package yape.yape.java;

import java.util.LinkedList;

/**
 * Find the largest 1-9 pandigital number possible by concatenating
 * x, x*2, x*3, ..., x*n for n>1.
 */
public class Euler38
{
	
	public static void main(String[] args)
	{
		long max_pandig = 0;
		long p;
		for (long i = 1; i < 10000; i++)
		{
			if (i > 1000)
			{
				// i is 4 digits, so n=2
				p = i * (100002);
				if (isPandigital(p))
				{
					System.out.println("Found " + i + " pandig = " + p);
					max_pandig = Math.max(max_pandig, p);
				}
			} else if (i > 100)
			{
				// i is 3 digits, so n = 3
				p = i * (1002003);
				if (isPandigital(p))
				{
					System.out.println("Found " + i + " pandig = " + p);
					max_pandig = Math.max(max_pandig, p);
				}
			} else if (i > 10)
			{
				// i is 2 digits. n=3 is impossible, so n=4
				p = i * (10203004);
				if (isPandigital(p))
				{
					System.out.println("Found " + i + " pandig = " + p);
					max_pandig = Math.max(max_pandig, p);
				}
			} else
			{
				// i is 1 digit, so n=5 or more
				p = i * (12030405);
				if (isPandigital(p))
				{
					System.out.println("Found " + i + " pandig = " + p);
					max_pandig = Math.max(max_pandig, p);
				} else
				{
					p = i * (12340506);
					if (isPandigital(p))
					{
						System.out.println("Found " + i + " pandig = " + p);
						max_pandig = Math.max(max_pandig, p);
					}
				}
			}
		}

		System.out.println("max = " + max_pandig);
	}
	
	
	private static boolean isPandigital(long l)
	{
		LinkedList<Character> allDig = new LinkedList<Character>();
		for (char c = '1'; c <= '9'; c++)
			allDig.add(c);

		while (l > 0)
		{
			Character c = (char) ((l % 10) + '0');
			if (!allDig.remove(c))
				// The character was not in the list, so it's a double.
				return false;
			l /= 10;
		}

		// No repeats, so just make sure that l was big enough.
		return (allDig.size() == 0);
	}
}
