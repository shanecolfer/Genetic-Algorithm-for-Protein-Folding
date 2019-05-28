import java.io.IOException;
import java.util.Arrays;

public class Control {

	public static void main(String[] args) throws IOException 
	{
		String protein = "221221122112222211111111112222221122112212211111";
		int population_size = 1000; //Size of working population
		int generation_size = 500;
		String[] population = new String[population_size];  //Stores population (movements) in string array
		double[] fitnesses = new double[population_size];   //Stores fitnesses of population
		double[] fitness_array = new double[3];     		//Stores info returned from fitness calculator
		double best_bonds = 0;								//Stores the bonds of the best candidate 
		double best_overlaps = 0;							//Stores the overlaps of the best candidate
		double best_fitness = -100;							//Stores the best fitness in this generation
		double total_fitness = 0;
		double average_fitness = 0;
		double mutate_rate = population_size / 100 * 2;
		double mutate_endrate = population_size / 100 * 3;
		double crossover_rate = population_size / 100 * 20;
		double mutate_addition = mutate_endrate - mutate_rate;
		mutate_addition = mutate_addition / generation_size;
		double generation_num = 0;
		double best_fitness_g = -100;
		int selection = 0;									//0 for proportional selection, 1 for tournament
		String best_movements = ""; 								//Stores best overall movements
		
		
		
		System.out.println(mutate_rate);
		System.out.println(mutate_addition);
		
		//Create random first population
		PopulationCreator p1 = new PopulationCreator(protein, population_size);
		population = p1.PopulationReturn();
		
		
		for (int i=0; i<population.length; i++) 
		{
			//System.out.println(population[i]);
		}
		
		System.out.println("Initial Population Generated");
		
		//Grade that populations fitness
		for(int i=0;i<population.length;i++)
		{
			Fitness f1 = new Fitness(protein, population[i]); //Create fitness object with one protein and folding just for testing
			fitness_array = f1.FitnessCalculator();			  //Calculate it's fitness
			fitnesses[i] = fitness_array[0];
			
			if (fitnesses[i] > best_fitness)
			{
				best_fitness = fitnesses[i];
				best_bonds = fitness_array[1];
				best_overlaps = fitness_array[2];
			}
		}
		
			
		for(int j=0; j<generation_size; j++) //Generation Loop
		{
			//Reset the best fitness in this generation
			best_fitness_g = -100;
			//Increment Generation Number
			generation_num++;
			System.out.println(selection);
			if(selection == 0)
			{
				//Start of Proportional
				Alter a1 = new Alter(population,fitnesses);
				
				for(int i=0;i<fitnesses.length;i++)
				{
					population[i]=a1.ProportionalSelection();
				}
				
				//Grade fitness again 
				for(int i=0;i<population.length;i++)
				{
					total_fitness = 0;
					average_fitness = 0;
					Fitness f1 = new Fitness(protein, population[i]); //Create fitness object with one protein and folding just for testing
					fitness_array = f1.FitnessCalculator();			  //Calculate it's fitness
					fitnesses[i] = fitness_array[0];
				}
				//End of proportional
			}
			else if(selection == 1)
			{
				//Start of Tournament
				Alter a1 = new Alter(population,fitnesses);
				
				population = a1.tournamentSelection();
				
				//Grade fitness again 
				for(int i=0;i<population.length;i++)
				{
					total_fitness = 0;
					average_fitness = 0;
					Fitness f1 = new Fitness(protein, population[i]); //Create fitness object with one protein and folding just for testing
					fitness_array = f1.FitnessCalculator();			  //Calculate it's fitness
					fitnesses[i] = fitness_array[0];
				}
				//End of Tournament
				
			}
			

			//Start of Crossover
			for(int i=0;i<crossover_rate;i++)
			{
				Alter a2 = new Alter(population);
				population = a2.CrossOver();
			}
			
			//Grade fitness again 
			for(int i=0;i<population.length;i++)
			{
				total_fitness = 0;
				average_fitness = 0;
				Fitness f1 = new Fitness(protein, population[i]); //Create fitness object with one protein and folding just for testing
				fitness_array = f1.FitnessCalculator();			  //Calculate it's fitness
				fitnesses[i] = fitness_array[0];
			}
			
			//Start of Mutation
			for(int i=0;i<mutate_rate;i++)
			{
				Alter a3 = new Alter(population);
				population = a3.mutation();
			}
			
			//Grade fitness again 
			for(int i=0;i<population.length;i++)
			{
				total_fitness = 0;
				average_fitness = 0;
				Fitness f1 = new Fitness(protein, population[i]); //Create fitness object with one protein and folding just for testing
				fitness_array = f1.FitnessCalculator();			  //Calculate it's fitness
				fitnesses[i] = fitness_array[0];
				
				//Check the best fitness overall (never gets reset to -100)
				if (fitnesses[i] > best_fitness)
				{ 
					best_fitness = fitnesses[i];
					best_bonds = fitness_array[1];
					best_overlaps = fitness_array[2];
					best_movements = population[i];				//Store the best overall movements
				}
				
				//Check for the best fitness in this generation (gets reset each gen)
				if (fitnesses[i] > best_fitness_g)
				{
					best_fitness_g = fitnesses[i];
				}
			}
			
			//End of mutation
			for (int i=0;i<population.length;i++)
			{
				total_fitness = total_fitness + fitnesses[i];
			}
			
			average_fitness = total_fitness / population.length;
			mutate_rate = mutate_rate + mutate_addition; 
			
			//Create array for log writer
			double[] log_array = {generation_num,average_fitness,best_fitness_g, best_fitness, best_bonds, best_overlaps};
			
			LogWriter l1 = new LogWriter(log_array);
			l1.writeLog();
			
			
			//System.out.println(average_fitness);
			
		}
		System.out.println(mutate_rate);
		System.out.println(best_movements);
		
		//Print Graphic
		FitnessGraphics fg1 = new FitnessGraphics(protein, best_movements);
		fg1.FitnessCalculator();
	}
		

}
