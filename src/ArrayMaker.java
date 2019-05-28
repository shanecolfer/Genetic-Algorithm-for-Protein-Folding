
public class ArrayMaker 
{
	private int length;
	
	ArrayMaker(int length)
	{
		this.length = length;
	}
	
	public int[][] ArrayReturn()
	{
		int[][] array = new int [100][100];
		return array;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
