package assign1.generic;

import java.util.Random;

/**********************************************************************
 * 
 * Collin Price
 * cp06vz @ brocku.ca
 * 3814647
 * 
 * COSC 4V82 Assignment 1
 * 
 * GARandom
 * This class is used for keeping a random seed consistent throughout the GA.
 * 
 * Jan. 31, 2011
 * 
 **********************************************************************/

public final class GARandom {

	private static Random rand;
	
	/**
	 * Initialize the random seed.
	 * @param seed
	 */
	public static void initSeed(int seed) {
		rand = new Random(seed);
	} // initSeed
	
	/**
	 * @param max
	 * @return random int between 0 and max
	 */
	public static int getRandom(int max) {
		return rand.nextInt(max);
	} // getRandom
	
	/**
	 * @return random value between 0 and 1
	 */
	public static double getRandomDouble() {
		return rand.nextDouble();
	} // getRandomDouble
	
	/**
	 * @return the Random object that has been initilized with the seed.
	 */
	public static Random getRandomObject() {
		return rand;
	} // getRandomObject
	
} // RandomInt
