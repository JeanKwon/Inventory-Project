public class Eraser extends Item
{
	String eraserColor;

	public Eraser(int ID, String name, int quantity, double price, 
			      Location location, String color) 
	{
		super(ID, name, quantity, price, location);
		eraserColor = color;
	}
	
	//returns the color of the eraser
	public String getUniqueInfo() 
	{
		return eraserColor;
	}

	public String toString() 
	{
		return super.toString() + " EraserColor : " + eraserColor ;
	}
	
}
