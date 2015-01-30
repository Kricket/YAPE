package yape.yape.java;

import yape.math.MathHelpers;

/**
 * Find the denominator of the product of all 4 fractions, less than one, where
 * the numerator and denominator are less than 100, and where one could mistakenly
 * remove the same digit from the numerator and denominator without changing the
 * value.
 */
public class Euler33 {
	
	public static void main(String[] args) {
		int prod_num = 1;
		int prod_denom = 1;
		int gcd;
		for(int num=10; num<100; num++)
			for(int denom=num+1; denom<100; denom++)
			{
				gcd = MathHelpers.gcd(num, denom);
				
				if(gcd == 1)
					continue;

				if(gcd % 10 == 0)
					continue; // "trivial" example ??
				
				// See if we can remove one digit from top and bottom, and still
				// maintain the same fraction.
				int num_1 = num%10;
				int num_10 = num / 10;
				int denom_1 = denom%10;
				int denom_10 = denom / 10;

				if(denom_10 == num_1)
					if(num * denom_1 == denom * num_10)
					{
						System.out.println(num + " / " + denom + " = " + num_10 + " / " + denom_1);
						prod_num *= num;
						prod_denom *= denom;
					}
				
				if(denom_1 == num_10)
					if(num * denom_10 == denom * num_1)
					{
						System.out.println(num + " / " + denom + " = " + num_1 + " / " + denom_10);
						prod_num *= num;
						prod_denom *= denom;
					}
			}
		
		// Now, reduce the product of all solutions to its lowest terms, and output the denominator.
		System.out.println("product's reduced denominator = " +
				prod_denom / MathHelpers.gcd(prod_num, prod_denom)
				);
	}
}
