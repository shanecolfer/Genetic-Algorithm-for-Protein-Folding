import java.util.Arrays;
import java.util.Random;

public class Alter 
{
	private String[] movements; //Stores each string of movements in array
	private double[] fitnesses; //Stores their corresponding fitnesses in array (index's match)
	
	Alter(String[] movements, double[] fitnesses)
	{
		this.movements = movements;
		this.fitnesses = fitnesses;
	}
	
	Alter(String[] movements)
	{
		this.movements = movements;
	}
	
	//Method for Proportional Selection
	//Takes a array of fitnesses and selects one proportionally and returns that one string.
	
	public String ProportionalSelection()
	{
		double Random = 0;
		double probability_array[] = new double[movements.length];
		double current_probability = 0;
		String error = "NoSelection"; //Default to error message
		int i;
		double total_fitness = 0;
		
		for(i=0;i<fitnesses.length;i++)
		{
			total_fitness = total_fitness + fitnesses[i];
		}
		
		for(i=0;i<fitnesses.length;i++)
		{
			current_probability = fitnesses[i] / total_fitness;
			
			probability_array[i] = current_probability;
		}
		
			i = 0;
			
			//Generate between 1 and 0
			Random = 0 + Math.random() * (1 - 0);
			
			do
			{
				//Loop through probability array subtracting the prob of each item from rand
				Random = Random - (probability_array[i]);
				
				i++;
				
				if( i == probability_array.length)
				{
					i = 0;
				}
				
				
			}
			while ( Random > 0);
			
			if(i == 0)
			{
				return (movements[movements.length - 1]);
			}
			else
			{
				return (movements[i - 1]);
			}
		
		
		
		/*double rand = Math.random() * total_fitness + 0;
		System.out.println(rand);
		double partialSum = 0; 
		
		for (i = fitnesses.length-1; i>=0; i--)
		{
			partialSum = partialSum + fitnesses[i];
			
			if (partialSum >= rand)
			{
				return movements[i];
			}
		}*/
		
		//return error;
	}
	
	public String[] tournamentSelection()
    {
       
        int length = movements.length;
        String temp_sequence[] = new String[length];
        String tournament_array[] = new String[length];
        double new_fitnesses[];
        new_fitnesses = new double[length];
        double best_fitness = 0;
        int sequenceIndex = 0;
        int i,j,contestants,selection = 0;
       
        for(j = 0; j < length; j++)
        {
            contestants = (int) (2 + Math.random() * (length - 2));
           
            for(i = 0; i < contestants; i++)
            {
                selection = (int) (0 + Math.random() * (length  - 0));
                tournament_array[i] = movements[selection];
                new_fitnesses[i] = fitnesses[selection];
            }
           
            best_fitness = new_fitnesses[0];
           
            for(i = 0; i < contestants; i++)
            {
               
                if(best_fitness < new_fitnesses[i])
                {
                    best_fitness = new_fitnesses[i];
                    sequenceIndex = i;
                }
               
            }
           
            temp_sequence[j] = tournament_array[sequenceIndex];
        }
       
        movements = temp_sequence;
        return movements;
    }
	
	public String[] CrossOver()
	{
		String[] crossoverParents = new String[2];
		int i = 0;
		int[] parentLoc = new int[2];
		Random r = new Random();
		int rand, bit;
		
		
		rand = r.nextInt(movements.length);
		
		//System.out.println(rand + "Hi");
		
		
		for(i=0;i<2;i++)
		{
			rand = r.nextInt(movements.length);
			//System.out.println(rand + "rand");
			crossoverParents[i] = movements[rand];
			parentLoc[i] = rand;
		}
		
		String parent1 = crossoverParents[0];
		String parent2 = crossoverParents[1];
		String p1fh, p1sh, p2fh, p2sh;
		
		//System.out.println(crossoverParents[0] + "Story");
		//System.out.println(crossoverParents[1] + "Story2");
		
		//System.out.println(parent1 + "p1 before");
		//System.out.println(parent2 + "p2 before");
		
		bit = r.nextInt(parent1.length());
		
		//System.out.println(bit + "Bit");
		
		p1fh = parent1.substring(0, bit);
		p1sh = parent1.substring(bit, (parent1.length() ));
		
		//System.out.println(p1fh + "p1fh");
		//System.out.println(p1sh + "p1sh");
		
		p2fh = parent2.substring(0, bit);
		p2sh = parent2.substring(bit, (parent1.length() ));
		
		//System.out.println(p2fh + "p2fh");
		//System.out.println(p2sh + "p2sh");
		
		parent1 = p2fh.concat(p1sh);
		parent2 = p1fh.concat(p2sh);
		
		//System.out.println(parent1 + "p1 after");
		//System.out.println(parent2 + "p2 after");
		
		movements[parentLoc[0]] = parent1;
		movements[parentLoc[1]] = parent2;
		
		//System.out.println(parentLoc[0] + "Sh");
		//System.out.println(parentLoc[1] + "Sh2");
		//System.out.println(Arrays.toString(movements));
		
		return movements;
		
	}
	
	public String[] mutation()
	{
		Random r1 = new Random();
		int rand1;
		Random r2 = new Random();
		int rand2;
		String chosen;
		
		
		rand1 = r1.nextInt(movements.length);
		
		chosen = movements[rand1];
		
		rand2 = r2.nextInt(chosen.length());
		
		if(chosen.charAt(rand2) == 'U' )
		{
			char[] chosenChars = chosen.toCharArray();
			chosenChars[rand2] = 'D';
			chosen = String.valueOf(chosenChars);
			movements[rand1] = chosen;
			return movements;
		}
		
		if(chosen.charAt(rand2) == 'D' )
		{
			char[] chosenChars = chosen.toCharArray();
			chosenChars[rand2] = 'U';
			chosen = String.valueOf(chosenChars);
			movements[rand1] = chosen;
			return movements;
		}
		
		if(chosen.charAt(rand2) == 'L' )
		{
			char[] chosenChars = chosen.toCharArray();
			chosenChars[rand2] = 'R';
			chosen = String.valueOf(chosenChars);
			movements[rand1] = chosen;
			return movements;
		}
		
		if(chosen.charAt(rand2) == 'R' )
		{
			char[] chosenChars = chosen.toCharArray();
			chosenChars[rand2] = 'L';
			chosen = String.valueOf(chosenChars);
			movements[rand1] = chosen;
			return movements;
		}
		
		
		return movements;
	}
}
