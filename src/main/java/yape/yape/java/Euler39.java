package yape.yape.java;

import java.util.HashMap;

/**
 * Find the integral perimeter p of a right triangle, p <= 1000, such that there
 * exist a maximum number of integral solutions for each side.
 */
public class Euler39 {
	
	public final static int MAX_PERIM_LENGTH = 1001;
	
	public static void main(String[] args) {
		// perimeters[p] = number of solutions for perimeter of length p
		int[] perimeters = new int[MAX_PERIM_LENGTH];
		
		// map of (s^2 => s)
		HashMap<Integer, Integer> squares = new HashMap<Integer, Integer> ();
		for(int i=1; i<MAX_PERIM_LENGTH; i++)
			squares.put(i*i, i);
		
		for(int a=1; a<MAX_PERIM_LENGTH; a++)
			for(int b=a; b<MAX_PERIM_LENGTH; b++)
			{
				Integer c = squares.get(a*a + b*b);
				if(c == null)
					continue;
				int p = a + b + c;
				if(p >= MAX_PERIM_LENGTH)
					continue;
				perimeters[p]++;
			}
		
		int max = 0;
		int maxidx = 0;
		for(int i = 0; i < MAX_PERIM_LENGTH; i++)
			if(perimeters[i] > max)
			{
				max = perimeters[i];
				maxidx = i;
			}
		
		System.out.println("max p = " + maxidx + " combinations possible: " + max);
	}

}
