package assign1.generic;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

/**********************************************************************
 * 
 * Collin Price
 * cp06vz @ brocku.ca
 * 3814647
 * 
 * COSC 4V82 Assignment 1
 * 
 * Population
 * Data structure for storing the Chromosomes in a population.
 * 
 * Jan. 31, 2011
 * 
 **********************************************************************/

public class Population {

	private Vector<Chromosome> 	population;
	private int counter = 0;
	
	/**
	 * Creates an empty population
	 */
	public Population() {
		population = new Vector<Chromosome>();
	} // constructor
	
	/**
	 * Creates an initial population from an array of Chromosomes.
	 * 
	 * @param genes array of Chromosomes
	 */
	public Population(Chromosome[] genes) {
		population = new Vector<Chromosome>();
		population.addAll(Arrays.asList(genes));
	} // constructor
	
	
	
	/**
	 * Add a Chromosome to the population.
	 * 
	 * @param c Chromosome to be added to the population
	 */
	public void add(Chromosome c) {
		population.add(c);
	} // add
	
	public void add(Chromosome[] c) {
		for (Chromosome chromo : c) {
			this.add(chromo);
		}
	} // add
	
	/**
	 * Run Selection on the population. Performs a selection operation 
	 * on the population based on the given Selection type.
	 * 
	 * @param s Selection type to perform
	 * @param k	Tournament size
	 * @return Selected Chromosome
	 */
	public Chromosome select(Selection s, int k) {
		Chromosome won = null;
		
		switch(s) {
			case TOURNAMENT:
				if (population.size() < k) {
					k /= 2;
				}

				Chromosome[] contestants = new Chromosome[k];
				
				if (population.size() == 2) {
					System.out.println("population == 2, too small");
					System.exit(1);
				} else if (population.size() == 1) {
					System.out.println("population == 1, way to small");
					System.exit(1);
				} else {
					for (int i = 0; i < k; i++) {
						contestants[i] = population.get(GARandom.getRandom(population.size()));
					}
				}
				
				int winner = -1;
				int score = Integer.MAX_VALUE;
				
				for (int i = 0; i < contestants.length; i++) {
					if (contestants[i].getFitness() < score) {
						winner = i;
						score = (int) contestants[i].getFitness();
					}
				}
				
				if (winner == -1) {
					System.out.println("Population is spoiled. No ones fitness is less than Integer.MAX_VALUE.");
					System.exit(1);
				} else {
					won = contestants[winner];
				}
				
				break;
			case ROULETTE:
				Collections.shuffle(population);
				double[] probability = new double[population.size()];
				double sum = 0;
				
//				for (Chromosome c : population) {
//					total_fitness += c.getFitness(); 
//				}
				
				for (int i = 0; i < probability.length; i++) {
					double temp = 1.0/population.get(i).getFitness();
//					fitness[i] = (sum += temp);
					probability[i] = (sum += temp); 
					
//					probability[i] = 1.0/(sum_probabilities + (population.get(i).getFitness()/total_fitness));
//					sum_probabilities += probability[i];
//					System.out.println(probability[i]);
				}
				double rand = GARandom.getRandomDouble() * sum;
				//loop
				//if ( r<fitness[i]) break;
				//System.exit(1);
//				double spin = GARandom.getRandomDouble();
				
				for (int i = 0; i < probability.length; i++) {
					if (rand <= probability[i]) {
						won = population.get(i);
						break;
					}
				}
				
				break;
			case NONE:
//				System.out.println(population.size());
				won = population.get(counter++);
				
				break;
			default:
		}
		
		return won;
	} // select
	
	/**
	 * Returns the best n Chromosomes
	 * 
	 * @param n number of Chromosomes
	 * @return best Chromosomes
	 */
	public Chromosome[] getBestChromosomes(int n) {
		Collections.sort(population);
		Chromosome[] best = new Chromosome[n];
		
		for (int i = 0; i < n; i++) {
			best[i] = population.get(i);
		}
		
		return best;
	} // getBestChromosomes 
	
	/**
	 * 
	 * @return most fit chromosome in population
	 */
	public Chromosome getBestChromosome() {
		/*int index = -1;
		double fitness = Double.MAX_VALUE;
		
		for (int i = 0; i < population.size(); i++) {
			if (population.get(i).getFitness() < fitness) {
				index = i;
				fitness = population.get(i).getFitness();
			}
		}*/
		
		Collections.sort(population);
//		System.out.println(population.get(0).getFitness());
//		System.out.println(population.get(population.size()-1).getFitness());
		
		/*for (int i = 0; i < population.size(); i++) {
			System.out.println(population.get(i).getFitness());
		}*/
		
		
		return population.get(0);
	} // getBestChromosome
	
	/**
	 * 
	 * @return the average fitness of the population
	 */
	public double getAverageFitness() {
		double avg = 0.0;
		
		for (int i = 0; i < population.size(); i++) {
			avg += population.get(i).getFitness();
		}
		
		return avg / population.size();
	} // getAverageFitness
	
	/**
	 * 
	 * @return the size of the population
	 */
	public int size() {
		return population.size();
	} // size
	
	/**
	 * 
	 * @return population iterator
	 */
	public Iterator<Chromosome> getIterator() {
		return population.iterator();
	} // getIterator

	/**
	 * 
	 * @return true if population is zero.
	 */
	public boolean isEmpty() {
		return population.size() == 0;
	} // isEmpty
	
} // GAPopulation
