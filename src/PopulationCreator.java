import java.util.Random;

public class PopulationCreator 
{
	private String protein;   //Stores working protein sequence
	private String movements = ""; //Stores strings of movements randomly created
	private int population;   //Stores size of working population
	
	
	String movement = "UDLR";
	int n = movement.length();
	
	PopulationCreator(String protein, int population)
	{
		this.protein = protein;
		this.population = population;
	}
	
	public String[] PopulationReturn()
	{
		int p = protein.length();
		
		String[] movements_array = new String[population]; //Stores all movements in string array to be returned
		
		for(int i=0; i<population; i++)
		{
			movements = "";
			
			Random r = new Random();
			
			//For loop to create string of random movements
			for(int j=0; j<p; j++)
			{
				movements = movements + (movement.charAt(r.nextInt(n)));
			}
			
			//Store each movement in string of movements
			movements_array[i] = movements;
		}
		
		return movements_array;
	}

	public String getProtein() {
		return protein;
	}

	public void setProtein(String protein) {
		this.protein = protein;
	}

	public String getMovements() {
		return movements;
	}

	public void setMovements(String movements) {
		this.movements = movements;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public String getMovement() {
		return movement;
	}

	public void setMovement(String movement) {
		this.movement = movement;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}


	 
}
