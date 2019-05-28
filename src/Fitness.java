
public class Fitness
{
	private String protein;
	private int[][] TwoDArray;
	private String movements;
	private double bonds;
	private double overlaps;
	private double fitness;

	public Fitness(String protein, String movements)
	{
		this.protein = protein;
		this.movements = movements;
	}

	public double[] FitnessCalculator()
	{
		ArrayMaker a1 = new ArrayMaker(protein.length());
		int [][] TwoDArray = a1.ArrayReturn();

		bonds = 0;
		overlaps = 0;
		fitness = 0;

		int starti = 100/2; //Array Size divided by 2
		int startj = 100/2; //Array Size divded by 2

		//Plot Movements and Calculate Fitness
		for (int i=0; i<protein.length();i++)
		{
			char current = protein.charAt(i);

			if(movements.charAt(i) == 'R')
			{
				int temp = Character.getNumericValue(current);
				starti = starti + 1; //Move Right


				if(TwoDArray[starti][startj] == 0)
				{
					TwoDArray[starti][startj] = temp;

					if(temp == 1)
					{
						if(TwoDArray[starti + 1][startj] == 1 || //Looks Above
						   TwoDArray[starti - 1][startj] == 1 || //Looks Below
						   TwoDArray[starti][startj + 1] == 1)	 //Looks Right
						{
							fitness = fitness + 1;
							bonds = bonds + 1;
						}
					}
				}
				else		//Else it's an overlap
				{
					TwoDArray[starti][startj] = temp;
					fitness = fitness - 1.5;
					overlaps++;
				}
			}

			if(movements.charAt(i) == 'L')
			{
				int temp = Character.getNumericValue(current);
				starti = starti - 1;

				if(TwoDArray[starti][startj] == 0)
				{
					TwoDArray[starti][startj] = temp;

					if(temp == 1)
					{
						if(TwoDArray[starti + 1][startj] == 1 || //Looks Above
						   TwoDArray[starti - 1][startj] == 1 || //Looks Below
						   TwoDArray[starti][startj - 1] == 1)	 //Looks Left
								{
									fitness = fitness + 1;
									bonds = bonds + 1;

								}
					}

				}
				else //Else it's an overlap
				{
					TwoDArray[starti][startj] = temp;
					fitness = fitness - 1.5;
					overlaps++;

				}
			}

			if(movements.charAt(i) == 'D')
			{
				int temp = Character.getNumericValue(current);
				startj = startj + 1;

				if(TwoDArray[starti][startj] == 0)
				{
					TwoDArray[starti][startj] = temp;

					if (temp == 1)
					{
						if(TwoDArray[starti - 1][startj] == 1 || //Looks Below
						   TwoDArray[starti][startj + 1] == 1 || //Looks Right
						   TwoDArray[starti][startj - 1] == 1)	 //Looks Left
							{
								fitness = fitness + 1;
								bonds = bonds + 1;


							}
					}
				}
				else
				{
					TwoDArray[starti][startj] = temp;
					fitness = fitness - 1.5;
					overlaps++;

				}
			}

			if(movements.charAt(i) == 'U')
			{
				int temp = Character.getNumericValue(current);
				startj = startj - 1;

				if(TwoDArray[starti][startj] == 0)
				{
					TwoDArray[starti][startj] = temp;

					if (temp == 1)
					{
						if(TwoDArray[starti + 1][startj] == 1 || //Looks Up
						   TwoDArray[starti][startj + 1] == 1 || //Looks Right
						   TwoDArray[starti][startj - 1] == 1)	 //Looks Left
						{
							fitness = fitness + 1;
							bonds = bonds + 1;


						}
					}
				}
				else
				{
					TwoDArray[starti][startj] = temp;
					fitness = fitness - 1.5;
					overlaps++;

				}
			}


		}//End of fitness calculation

		/*for (int i=0;i<50;i++)
		{
			System.out.print('\n');

			for (int j=0;j<50;j++)
			{
				System.out.print(TwoDArray[j][i]);
			}
		}
		*/
		//System.out.print(fitness);

		fitness = fitness + 30;
		
		double[] fitness_array = {fitness, bonds, overlaps}; //Creates array to store fitness + log file data
		return fitness_array; //Returns Array
	}
}
