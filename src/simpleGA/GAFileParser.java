package simpleGA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Vector;

import assign1.generic.Crossover;
import assign1.generic.Generation;
import assign1.generic.Mutation;
import assign1.generic.GARandom;
import assign1.generic.Selection;

/**********************************************************************
 * 
 * Collin Price
 * cp06vz @ brocku.ca
 * 3814647
 * 
 * COSC 4V82 Assignment 1
 * 
 * GAFileParser
 * Utility class used for file reading and random population generation.
 * 
 * 
 * Jan. 31, 2011
 * 
 **********************************************************************/

public class GAFileParser {
	
	static Vector<Integer> numbers = new Vector<Integer>() {/**
		 * 
		 */
		private static final long serialVersionUID = -8531258075317773176L;

	{
		add(1);
		add(2);
		add(3);
		add(4);
		add(5);
		add(6);
		add(7);
		add(8);
		add(9);
	}};
	
	/**
	 * Reads in a parameter file and return the data.
	 * 
	 * @param fileName location and name of the configuration file
	 * @return set of parameters to be run
	 */
	public static Vector<ParameterSet> getParameterSets(File fileName) {
		Vector<String> fileData = new Vector<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			
			String line = "";
			while((line = in.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty() || line.charAt(0) == '#') continue;		// comment || blank line
				fileData.add(line);
			}
		} catch (FileNotFoundException e) {
			System.err.println("File Not Found : " + fileName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Error reading file : " + fileName);
			e.printStackTrace();
			System.exit(1);
		}
		
		Vector<ParameterSet> sets = new Vector<ParameterSet>();
		ParameterSet set = new ParameterSet();
		while(!fileData.isEmpty()) {
			String line = fileData.remove(0);
			StringTokenizer tokens = new StringTokenizer(line, " :", false);
			String temp = tokens.nextToken();
			if (temp.equalsIgnoreCase("file_name")) {
				set.setFile_name(tokens.nextToken());
			} else if (temp.equalsIgnoreCase("runs_per_set")) {
				set.setRuns_per_set(Integer.parseInt(tokens.nextToken()));
			} else if (temp.equalsIgnoreCase("elitism")) {
				set.setElitism(Boolean.parseBoolean(tokens.nextToken()));
			} else if (temp.equalsIgnoreCase("crossover")) {
				set.setCrossover(Double.parseDouble(tokens.nextToken()));
			} else if (temp.equalsIgnoreCase("mutation")) {
				set.setMutation(Double.parseDouble(tokens.nextToken()));
			} else if (temp.equalsIgnoreCase("generations")) {
				set.setGenerations(Integer.parseInt(tokens.nextToken()));
			} else if (temp.equalsIgnoreCase("population")) {
				set.setPopulation(Integer.parseInt(tokens.nextToken()));
			} else if (temp.equalsIgnoreCase("selection_type")) {
				set.setSelection_type(Selection.valueOf(Selection.class, tokens.nextToken().toUpperCase()));
			} else if (temp.equalsIgnoreCase("crossover_type")) {
				set.setCrossover_type(Crossover.valueOf(Crossover.class, tokens.nextToken().toUpperCase()));
			} else if (temp.equalsIgnoreCase("mutation_type")) {
				set.setMutation_type(Mutation.valueOf(Mutation.class, tokens.nextToken()));
			} else if (temp.equalsIgnoreCase("seed")) {
				set.setSeed(Integer.parseInt(tokens.nextToken()));
			} else if (temp.equalsIgnoreCase("tournament_size")) {
				set.setTournament_size(Integer.parseInt(tokens.nextToken()));
			} else if (temp.equalsIgnoreCase("--")) {
				sets.add(set);
				set = new ParameterSet();
			} else {
				System.out.println("Unknown line or error. Ignoring line.");
			}
		}
		
		return sets;
	} // getParameterSets
	
	/**
	 * Reads in a data file containing the Sudoku boards.
	 * 
	 * @param fileName file containing sudoku boards
	 * @return	set of Sudoku boards
	 */
	public static Vector<String> getSudokuBoards(File fileName) {
		Vector<String> fileData = new Vector<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			
			String line = "";
			while((line = in.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty() || line.charAt(0) == '#') continue;		// comment || blank line
				fileData.add(line);
			}
		} catch (FileNotFoundException e) {
			System.err.println("File Not Found : " + fileName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Error reading file : " + fileName);
			e.printStackTrace();
			System.exit(1);
		}
		
		Vector<String> boards = new Vector<String>();
		String board = "";
		
		while(!fileData.isEmpty()) {
			String temp = fileData.remove(0);
			if (temp.length() != 9 && temp.length() != 2) {
				System.err.println("File Error: Sudoku puzzle must have nine elements per row.");
				System.exit(1);
			} else if (temp.equalsIgnoreCase("--")) {
				if (board.length() != 81) {
					System.err.println("File Error: Sudoku puzzle must have nine rows.");
					System.exit(1);
				} else {
					boards.add(board);
					board = "";
				}
			} else if (Character.isDigit(temp.charAt(0))) {
				board += temp;
			}
		}
		
		return boards;
	} // getSudokuBoards
	
	/**
	 * Creates random boards from a given board configuration.
	 * 
	 * @param board initial board
	 * @param n number of random boards
	 * @return set of randomly created Sudoku boards
	 */
	@SuppressWarnings("unchecked")
	public static Vector<SudokuBoard> createSudokuPopulation(String board, int n) {
		Vector<SudokuBoard> population = new Vector<SudokuBoard>(n);
		
		String[] initialBoard = rowsToBoxes(board);
		
		for (int i = 0; i < n; i++) { // for each population
			Box[] boxes = new Box[9];
			for (int j = 0; j < 9; j++) { // for each box
				Vector<Integer> missingValues = (Vector<Integer>) numbers.clone();
				String temp = initialBoard[j];
				for (int k = 0; k < 9; k++) {
					if (temp.charAt(k) == '0') {
						continue;
					} else {
						missingValues.removeElement(Integer.parseInt("" + temp.charAt(k)));
					}
				}
				Collections.shuffle(missingValues, GARandom.getRandomObject());
				Square[] squares = new Square[9];
				for (int m = 0; m < 9; m++) {
					if (temp.charAt(m) == '0') {
						squares[m] = new Square(missingValues.remove(0), false);
					} else {
						squares[m] = new Square(Integer.parseInt("" + temp.charAt(m)), true);
					}
				}
				boxes[j] = new Box(squares);
			}
			population.add(new SudokuBoard(boxes));
		}
		
		return population;
	} // createSudokuPopulation
	
	/**
	 * Converts a Sudoku board from row based to box based
	 * @param board
	 * @return
	 */
	private static String[] rowsToBoxes(String board) {		
		String[] boxes = new String[9];
		
		int index = -1;
		for (int i = 0; i < 9; i += 3) {
			index++;
			boxes[index] = board.substring(i, i+3) + board.substring(i+9, i+9+3) + board.substring(i+18, i+18+3);
		}
		
		index = 2;
		for (int i = 27; i < 36; i += 3) {
			index++;
			boxes[index] = board.substring(i, i+3) + board.substring(i+9, i+9+3) + board.substring(i+18, i+18+3);
		}
		
		index = 5;
		for (int i = 54; i < 63; i += 3) {
			index++;
			boxes[index] = board.substring(i, i+3) + board.substring(i+9, i+9+3) + board.substring(i+18, i+18+3);
		}
		
		return boxes;
	} // rowsToBoxes
	
	public static void main(String args[]) {
		if (args.length != 3) {
			System.err.println("Three arguments required: configuration_file data_file output_folder_location");
			System.exit(1);
		}
		
		File config_file = new File(args[0]);
		File data_file = new File(args[1]);
		File output_folder = new File(args[2]);
		
		if (!config_file.exists() || !data_file.exists() || !output_folder.isDirectory()) {
			if (!config_file.exists()) {
				System.err.println("Configuration file '" + args[0] + "' does not exist.");
			}
			if (!data_file.exists()) {
				System.err.println("Data file '" + args[1] + "' does not exist.");
			}
			if (!output_folder.isDirectory()) {
				System.err.println("Output folder '" + args[2] + "' is not a valid directory.");
			}
			System.exit(1);
		}
		
		Vector<ParameterSet> param_file = getParameterSets(config_file);
		Vector<String> board_file = getSudokuBoards(data_file);
		Vector<SudokuBoard> boards = new Vector<SudokuBoard>();
		String test_board = board_file.get(0);
		
		for (int i = 0; i < param_file.size(); i++) {
			System.out.println("Test Parameters: " + i);
			GARandom.initSeed(param_file.get(i).getSeed());
			double[] average_fitness;
			double[] elite_fitness;
			
			if (param_file.get(i).getGenerations() == 0) {
				average_fitness = new double[1];
				elite_fitness = new double[1];
			} else {
				average_fitness = new double[param_file.get(i).getGenerations()];
				elite_fitness = new double[param_file.get(i).getGenerations()];
			}
			
			
			for (int j = 0; j < param_file.get(i).getRuns_per_set(); j++) {
				System.out.println("run #" + (j+1));
				boards = createSudokuPopulation(test_board,param_file.get(i).getPopulation());
				System.out.println("created population");
				SudokuGA su = new SudokuGA(boards, param_file.get(i));
				Vector<Generation> gens = su.getGenerations();
				for (int k = 0; k < gens.size(); k++) {
					average_fitness[k] += gens.get(k).average;
					elite_fitness[k] += gens.get(k).elite;
				}
			}
			
			if (param_file.get(i).getGenerations() == 0) {
				param_file.get(i).setGenerations(1);
			}
			Vector<Generation> gens = new Vector<Generation>(param_file.get(i).getGenerations());
			for (int m = 0; m < param_file.get(i).getGenerations(); m++) {
				average_fitness[m] /= param_file.get(i).getRuns_per_set();
				elite_fitness[m] /= param_file.get(i).getRuns_per_set();
				gens.add(new Generation(elite_fitness[m], average_fitness[m]));
			}
			SudokuGA.saveFile(gens, param_file.get(i));
			System.out.println("File Saved");
		}
		
		
		
	} // main
	
} // GAFileParser
