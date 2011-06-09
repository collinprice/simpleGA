package assign1.generic;

/**********************************************************************
 * 
 * Collin Price
 * cp06vz @ brocku.ca
 * 3814647
 * 
 * COSC 4V82 Assignment 1
 * 
 * Generation
 * This class is used to score information regarding each generation of a population.
 * 
 * Jan. 31, 2011
 * 
 **********************************************************************/

public class Generation {
	/* The best fitness of a population */
	public double elite;
	
	/* The average fitness of a population */
	public double average;
	
	public Generation(double e, double a) {
		elite = e;
		average = a;
	} // constructor
} // Generation
