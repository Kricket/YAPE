package yape.yape.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;

/**
 * Find the number of words in the provided file, that are triangle words.
 */
public class Euler42 {
	private static String[] words;
	private static HashSet<Integer> triangleNumbers;

	public static void main(String[] args) throws Exception {
		buildTriangleNumbers();
		readStringFile();
		int total = 0;
		for(String w:words)
			if(isTriangle(w))
				total++;
		System.out.println("There are " + total + " triangle words.");
	}

	
	
	/**
	 * Build a list of triangle numbers.
	 */
	private static void buildTriangleNumbers() {
		triangleNumbers = new HashSet<Integer>(1000);
		for(int i=1; i<1000; i++)
			triangleNumbers.add((i*(i+1))/2);
	}



	/**
	 * Determine if the given word is a triangle word.
	 * @param w A word (containing only characters A-Z).
	 * @return Whether the sum of the positions of its letters is a triangle number.
	 */
	private static boolean isTriangle(String w) {
		int score = 0;
		for(int i=0; i<w.length(); i++)
		{
			score += (w.charAt(i) - 'A' + 1);
		}
		
		return triangleNumbers.contains(score);
	}



	/**
	 * Read the input word file.
	 * @throws Exception
	 */
	private static void readStringFile() throws Exception {
		BufferedReader reader = new BufferedReader(
				new FileReader(
						new File("src/resources/words.txt")
						));
		
		words = reader.readLine().split("[^a-zA-Z]+");
		reader.close();
	}

}
