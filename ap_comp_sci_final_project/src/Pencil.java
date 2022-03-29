public class Pencil extends Item 
{
	String HBLevel;

	public Pencil(int ID, String name, int quantity, double price, 
			      Location location, String LeadHBLevel) 
	{
		super(ID, name, quantity, price, location);
		HBLevel = LeadHBLevel;
	}

	//returns the HB Level of the pencil
	public String getUniqueInfo() 
	{
		return HBLevel;
	}

	public String toString() 
	{
		return super.toString() + " Item HB Level : " + HBLevel;
	}
}
