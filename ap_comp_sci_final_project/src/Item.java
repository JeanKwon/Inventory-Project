public abstract class Item 
{	
	int 	 itemID;
	String   itemName;
	int 	 itemQuantity;
	double 	 itemPrice;
	Location itemLocation;

	public Item(int ID, String name, int quantity, double price, Location location) 
	{
		itemID 		 = ID;
		itemName 	 = name;
		itemQuantity = quantity;
		itemPrice 	 = price;
		itemLocation = location;
	}

	//returns the ID
	public int getID()
	{
		return itemID;
	}
	
	//returns the Name
	public String getName()
	{
		return itemName;
	}
	
	//returns the quantity of the item
	public int getQuantity()
	{
		return itemQuantity;
	}
	
	//returns the price of the item
	public double getPrice()
	{
		return itemPrice;
	}
	
	//returns the storage location of the item
	public Location getLocation()
	{
		return itemLocation;
	}
	
	//returns the unique informations of the each item
	public abstract String getUniqueInfo();
		
	public String toString()
	{
		String info = "Item ID : " + this.getID() + " Item Name : " + this.itemName + 
				      " Item quantity : " + this.getQuantity() + 
				      " Item price : " + this.getPrice() + 
				      " Location : " + this.getLocation();
		return info;
	}
}