package yape.yape.java;


/**
 * Find the starting number under 1 million that produces the longest
 * hailstone sequence.
 */
public class Euler14 {
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		long ans = findLongestChain(1000000);
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("Ans = " + ans + " Executed in " + (endTime - startTime) / 1000.0 + "s");
	}
	
	/**
	 * Find the longest hailstone sequence that starts under the given limit.
	 * @param limit The maximum starting number.
	 * @return The starting number that produces the longest sequence.
	 */
	public static long findLongestChain(long limit)
	{
		long maxlen=2, maxidx=2, templen;
		for(long idx=(long)(limit/2 + 0.5); idx < limit; idx++)
		{
			templen = hailstoneChainLength(idx);
			if(templen > maxlen)
			{
				maxlen = templen;
				maxidx = idx;
			}
		}
		
		return maxidx;
	}

	/**
	 * Get the length of the hailstone sequence that starts with the given number.
	 * @param idx The starting number of the sequence.
	 * @return The length of the hailstone sequence.
	 */
	private static long hailstoneChainLength(long idx) {
		long len=1;
		
		while(idx != 1)
		{
			len++;
			idx = getNextHailstone(idx);
		}
		
		return len;
	}

	/**
	 * Given a number, get the next number in the hailstone sequence.
	 * @param idx The starting number.
	 * @return 3*idx + 1 if idx is odd; idx/2 if idx is even.
	 */
	private static long getNextHailstone(long idx) {
		if((idx & 1) == 1)
			return 3*idx + 1;
		else
			return idx / 2;
	}

}
