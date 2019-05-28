import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FitnessGraphics
{
	private String protein;
	private int[][] TwoDArray;
	private String movements;
	private double bonds;
	private double overlaps;
	private double fitness;

	public FitnessGraphics(String protein, String movements)
	{
		this.protein = protein;
		this.movements = movements;
	}

	public double[] FitnessCalculator()
	{
		ArrayMaker a1 = new ArrayMaker(protein.length());
		int [][] TwoDArray = a1.ArrayReturn();
		int width = 3840;
		int height = 2160;
		int x = width/2;
		int y = height/2;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		Font currentFont = g2.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 4F);
        g2.setFont(newFont);
		int cellSize = 50;
		int cellNum = 0;
		
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, width, height);
		
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(width/2, height/2, cellSize, cellSize);
		g2.setColor(Color.WHITE);
		g2.drawString(String.valueOf(cellNum), width/2 + cellSize/4, height/2 + cellSize - 6);
		cellNum++;

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
					if(current == '1') {
						x += cellSize;
						g2.drawLine(x, y+(cellSize/2), x + cellSize, y+(cellSize/2));
						g2.setColor(Color.BLUE);
						x += cellSize;
						g2.fillRect(x, y, cellSize, cellSize);
						g2.setColor(Color.WHITE);
						g2.drawString(String.valueOf(cellNum), x + cellSize/4, y + cellSize - 6);
						cellNum++;
					}
					else
					{
						x += cellSize;
						g2.drawLine(x, y+(cellSize/2), x + cellSize, y+(cellSize/2));
						g2.setColor(Color.GREEN);
						x += cellSize;
						g2.fillRect(x, y, cellSize, cellSize);
						g2.setColor(Color.WHITE);
						g2.drawString(String.valueOf(cellNum), x + cellSize/4, y + cellSize - 6);
						cellNum++;
					}
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
					
					if(current == '1') {
						x -= cellSize;
						g2.drawLine(x+cellSize, y+(cellSize/2), x, y+(cellSize/2));
						g2.setColor(Color.BLUE);
						x -= cellSize;
						g2.fillRect(x, y, cellSize, cellSize);
						g2.setColor(Color.WHITE);
						g2.drawString(String.valueOf(cellNum), x + cellSize/4, y + cellSize - 6);
						cellNum++;
					}
					else
					{
						x -= cellSize;
						g2.drawLine(x+cellSize, y+(cellSize/2), x, y+(cellSize/2));
						g2.setColor(Color.GREEN);
						x -= cellSize;
						g2.fillRect(x, y, cellSize, cellSize);
						g2.setColor(Color.WHITE);
						g2.drawString(String.valueOf(cellNum), x + cellSize/4, y + cellSize - 6);
						cellNum++;
					}

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

					if(current == '1') {
						y += cellSize;
						g2.drawLine(x+(cellSize/2), y, x+(cellSize/2), y+cellSize);
						g2.setColor(Color.BLUE);
						y += cellSize;
						g2.fillRect(x, y, cellSize, cellSize);
						g2.setColor(Color.WHITE);
						g2.drawString(String.valueOf(cellNum), x + cellSize/4, y + cellSize - 6);
						cellNum++;
					}
					else
					{
						y += cellSize;
						g2.drawLine(x+(cellSize/2), y, x+(cellSize/2), y+cellSize);
						g2.setColor(Color.GREEN);
						y += cellSize;
						g2.fillRect(x, y, cellSize, cellSize);
						g2.setColor(Color.WHITE);
						g2.drawString(String.valueOf(cellNum), x + cellSize/4, y + cellSize - 6);
						cellNum++;
					}
					
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
					
					if(current == '1') {
						y -= cellSize;
						g2.drawLine(x+(cellSize/2), y, x+(cellSize/2), y+cellSize);
						g2.setColor(Color.BLUE);
						y -= cellSize;
						g2.fillRect(x, y, cellSize, cellSize);
						g2.setColor(Color.WHITE);
						g2.drawString(String.valueOf(cellNum), x + cellSize/4, y + cellSize - 6);
						cellNum++;
					}
					else
					{
						y -= cellSize;
						g2.drawLine(x+(cellSize/2), y, x+(cellSize/2), y+cellSize);
						g2.setColor(Color.GREEN);
						y -= cellSize;
						g2.fillRect(x, y, cellSize, cellSize);
						g2.setColor(Color.WHITE);
						g2.drawString(String.valueOf(cellNum), x + cellSize/4, y + cellSize - 6);
						cellNum++;
					}
					
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
		
		fitness = fitness + 30;
		
		Font newFont2 = currentFont.deriveFont(currentFont.getSize() * 8F);
        g2.setFont(newFont2);
		g2.drawString("Bonds: " + String.valueOf(bonds), width/12, height/12);
		g2.drawString("Overlaps: " + String.valueOf(overlaps), width/12, height/12 + cellSize*2);
		g2.drawString("Fitness: " + String.valueOf(fitness), width/12, height/12 + (cellSize*4));
		
		String folder = "/tmp/";
		String filename = "C:\\Users\\Shane\\SomeDirectory\\protein.png";
		
		try {
			ImageIO.write(image, "png", new File(filename));
		}   catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		double[] fitness_array = {fitness, bonds, overlaps}; //Creates array to store fitness + log file data
		return fitness_array; //Returns Array
	}
}
