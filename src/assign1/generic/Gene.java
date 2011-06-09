package assign1.generic;

/**********************************************************************
 * 
 * Collin Price
 * cp06vz @ brocku.ca
 * 3814647
 * 
 * COSC 3P71 Assignment 2
 * 
 * Abstract class Gene
 * An abstract class of a Genetic Algorithm's Gene.
 * 
 * Nov. 8, 2010
 * 
 **********************************************************************/

public abstract class Gene {

	protected String name;
	
	public Gene (String name) {
		this.name = name;
	} // constructor
	
	/**
	 * Returns the name of the Gene
	 * 
	 * @return name of the Gene
	 */
	public String name() {
		return name;
	}
	
	/**
	 * 
	 * @return a String representation of the Gene
	 */
	public abstract String toString();
	
} // Gene
