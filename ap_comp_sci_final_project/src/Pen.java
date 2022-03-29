public class Pen extends Item 
{
	String leadSize;

	public Pen(int ID, String name, int quantity, double price, 
			   Location location, String penLeadSize)
	{
		super(ID, name, quantity, price, location);
		leadSize = penLeadSize;
	}

	//returns the lead size of the pen
	public String getUniqueInfo() 
	{
		return leadSize;
	}

	public String toString() 
	{
		return super.toString() + " Pen Lead Size : " + leadSize;
	}
}