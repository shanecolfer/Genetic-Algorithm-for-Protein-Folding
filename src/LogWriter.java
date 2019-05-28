import java.io.FileWriter;
import java.io.IOException;


public class LogWriter 
{
	private double[] fitness_array;
	private String filename = "C:\\Users\\Shane\\SomeDirectory\\logfile.csv";
	
	public LogWriter(double[] fitness_array)
	{
		this.fitness_array = fitness_array;
	}
	
	public void writeLog() throws IOException
	{
		String LogFile = (fitness_array[0] + "," + fitness_array[1] + "," + fitness_array[2] + "," + fitness_array[3] + "," + fitness_array[4] + "," + fitness_array[5] + "\n");
		String titles = ("Gen" + "," + "A_Fitness" + "," + "B_G_Fitness" + "," + "B_O_Fitness" + "," + "H/H Bonds" + "," + "Overlaps" + "\n");
		
		if(fitness_array[0] == 1)
		{
			FileWriter writer = new FileWriter(filename,true);
			writer.write(titles);
			writer.close();
		}
		
		
		FileWriter writer1 = new FileWriter(filename,true);
		writer1.write(LogFile);
		writer1.close();
	}
}
