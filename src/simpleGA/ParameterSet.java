package simpleGA;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import assign1.generic.Crossover;
import assign1.generic.Mutation;
import assign1.generic.Selection;

/**********************************************************************
 * 
 * Collin Price
 * cp06vz @ brocku.ca
 * 3814647
 * 
 * COSC 4V82 Assignment 1
 * 
 * Parameter Set
 * Stores the configuration information for a genetic algorithm.
 * 
 * Jan. 31, 2011
 * 
 **********************************************************************/

public class ParameterSet {

	private String file_name;
	private int runs_per_set = 10;
	private boolean elitism = true;
	private double crossover = 0.8;
	private double mutation = 0.2;
	private int generations = 1000;
	private int population = 50;
	private Selection selection_type = Selection.ROULETTE;
	private int tournament_size = 3;
	private Crossover crossover_type = Crossover.TWO_POINT;
	private Mutation mutation_type = Mutation.RECIPRICAL;
	private int seed;
	private String output_location = "./rsrc/output/";

	public String getFile_name() {
		DateFormat f = new SimpleDateFormat("yyyy.MM.dd 'at' HH.mm.ss");
		String date = "" + f.format(Calendar.getInstance().getTime());
		
		if (file_name == null) {
			return date + ".csv";
		} else {
			return file_name + "-" + date + ".csv";
		}
		
	}
	
	public void printParameters() {
		System.out.println("----------------------------------");
		System.out.println("Name: " + getFile_name());
		System.out.println("Number of runs: " + runs_per_set);
		System.out.println("Elitism: " + elitism);
		System.out.println("Crossover Rate: " + crossover);
		System.out.println("Mutation Rate: " + mutation);
		System.out.println("Number of Generations: " + generations);
		System.out.println("Size of Population: " + population);
		System.out.println("Selection Type: " + selection_type);
		System.out.println("Tournament Size: " + tournament_size);
		System.out.println("Crosover Type: " + crossover_type);
		System.out.println("Mutation Type: " + mutation_type);
		System.out.println("Seed: " + seed);
		System.out.println("Output Location: " + output_location);
		System.out.println("----------------------------------");
	} // printParameters

	public int getRuns_per_set() {
		return runs_per_set;
	}

	public boolean isElitism() {
		return elitism;
	}

	public double getCrossover() {
		return crossover;
	}

	public double getMutation() {
		return mutation;
	}

	public int getGenerations() {
		return generations;
	}

	public int getPopulation() {
		return population;
	}

	public Selection getSelection_type() {
		return selection_type;
	}

	public Crossover getCrossover_type() {
		return crossover_type;
	}

	public Mutation getMutation_type() {
		return mutation_type;
	}

	public int getSeed() {
		if (seed == 0) {
			return (int) System.currentTimeMillis();
		} else {
			return seed;
		}
	}
	
	public void setFile_name(String fileName) {
		file_name = fileName;
	}

	public void setRuns_per_set(int runsPerSet) {
		runs_per_set = runsPerSet;
	}

	public void setElitism(boolean elitism) {
		this.elitism = elitism;
	}

	public void setCrossover(double crossover) {
		this.crossover = crossover;
	}

	public void setMutation(double mutation) {
		this.mutation = mutation;
	}

	public void setGenerations(int generations) {
		this.generations = generations;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public void setSelection_type(Selection selectionType) {
		selection_type = selectionType;
	}

	public void setCrossover_type(Crossover crossoverType) {
		crossover_type = crossoverType;
	}

	public void setMutation_type(Mutation mutationType) {
		mutation_type = mutationType;
	}
	
	public void setSeed(int seed) {
		this.seed = seed;
	}

	public int getTournament_size() {
		return tournament_size;
	}

	public void setTournament_size(int tournamentSize) {
		tournament_size = tournamentSize;
	}

	public String getOutput_location() {
		return output_location;
	}

	public void setOutput_location(String outputLocation) {
		output_location = outputLocation;
	}
	
} // ParameterSet
