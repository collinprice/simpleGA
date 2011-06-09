package assign1.generic;

/**********************************************************************
 * 
 * Collin Price
 * cp06vz @ brocku.ca
 * 3814647
 * 
 * COSC 4V82 Assignment 1
 * 
 * Interface Chromosome extends Comparable<Chromosome>
 * An interface for creating Genetic Algorithm Chromosomes.
 * 
 * Jan. 31, 2011
 * 
 **********************************************************************/

public interface Chromosome extends Comparable<Chromosome>, Cloneable {

	/**
	 * Mutate the Chromosome with the given Mutation type.
	 * 
	 * @param type the type of Mutation
	 */
	public void mutate(Mutation type);
	
	/**
	 * Cross two Chromosomes together with the given Crossover type.
	 * 
	 * @param c1 First Parent Chromosome
	 * @param c2 Second Parent Chromosome
	 * @param type the type of Crossover
	 * @return assay containing two child Chromosomes
	 */
	public Chromosome[] crossover(Chromosome c1, Chromosome c2, Crossover type);
	
	/**
	 * Finds the fitness value for the Chromosome.
	 * 
	 * @return fitness value of Chromosome
	 */
	public double getFitness();

	/**
	 * 
	 * @return string representation of the Chromosome
	 */
	@Override
	public String toString();

	public Object clone();
	
} // IChromosome
