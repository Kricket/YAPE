package yape.yape.java;

/**
 * Find the smallest triangle number Tn greater than T_285, such that
 * Tn is also hexagonal and pentagonal.
 */
public class Euler45
{
	
	public static void main(String[] args)
	{
		long n = 286;
		long Tn;
		while(true)
		{
			Tn = getTriangleNumber(n++);
			if(isPenta(Tn) && isHexa(Tn))
				break;
		}
		System.out.println("n = " + n + " Tn = " + Tn);
	}

	
	/**
	 * Get the ith triangle number.
	 * @param i The index
	 * @return T_i
	 */
	public static long getTriangleNumber(long i)
	{
		return i*(i+1L) / 2L;
	}
	
	
	/**
	 * Determine if the given number is a pentagonal number.
	 * @param k
	 * @return
	 */
	public static boolean isPenta(long k)
	{
		// If 3n^2 - n - 2k has a positive integer root, then k is pentagonal.
		long sq = 1L + 24L*k;
		long sqrt = (long) Math.sqrt(sq);
		if(sqrt * sqrt != sq)
			return false;
		if(sqrt == 1)
			return false;
		if(sqrt > 1)
			return (1L + sqrt) % 6 == 0;
		return 	(1L - sqrt) % 6 == 0;
	}

	
	
	/**
	 * Determine if the given number is a hexagonal number.
	 * @param k The number to test.
	 * @return true if it's hexagonal; false otherwise.
	 */
	public static boolean isHexa(long k)
	{
		// If 3n^2 - n - 2k has a positive integer root, then k is pentagonal.
		long sq = 1L + 8L*k;
		long sqrt = (long) Math.sqrt(sq);
		if(sqrt * sqrt != sq)
			return false;
		if(sqrt == 1)
			return false;
		if(sqrt > 1)
			return (1L + sqrt) % 4 == 0;
		return 	(1L - sqrt) % 4 == 0;
	}
}
