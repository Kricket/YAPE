package yape.yape.java;

/**
 * Find the sum of all numbers less than 1 million which are palindromes
 * in both base 10 and base 2.
 */
public class Euler36 {
	
	public static void main(String[] args) {
		// Loop through decimal palindromes. Count the ones that are also
		// binary palindromes.
		
		generatePalindromes("");
		
		System.out.println("total = " + total);
	}
	
	
	private static String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	private static int total = 0;
	
	
	private static void generatePalindromes(String halfPalin)
	{

		String secondHalfPalin = reverseString(halfPalin);

		if(halfPalin.length() != 0)
		{
			if(halfPalin.charAt(0) == '0')
				return;
			countIfBinaryPalindrome(halfPalin + secondHalfPalin);
		}

		if(halfPalin.length() > 2)
			return;

		for(String digit:digits)
		{
			countIfBinaryPalindrome(
					halfPalin + digit + secondHalfPalin
					);
			generatePalindromes(halfPalin + digit);
		}
	}

	
	
	/**
	 * Reverse the string, e.g. "123" => "321"
	 * @param str The string to reverse.
	 * @return str, backwards.
	 */
	private static String reverseString(String str) {
		int len = str.length();
		StringBuffer ans = new StringBuffer(len);
		for(int i = len-1; i >= 0; i--)
			ans.append(str.charAt(i));
		return ans.toString();
	}



	private static void countIfBinaryPalindrome(String numStr)
	{
		int num = Integer.parseInt(numStr);
		if(num % 2 == 0)
			return; // No leading zeroes allowed
		
		String bits = Integer.toBinaryString(num);
		if(bits.compareTo(reverseString(bits)) == 0)
		{
			//System.out.println(num + " " + bits);
			total += num;
		}
	}
}
