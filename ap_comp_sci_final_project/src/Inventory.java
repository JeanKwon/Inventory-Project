import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Inventory 
{
	private static Inventory single_instance = null;
	
	Scanner scan = new Scanner(System.in);

	private static ArrayList<Item> penList;
	private static ArrayList<Item> pencilList;
	private static ArrayList<Item> eraserList;
	
	final int PEN = 1;
	final int PENCIL = 2;
	final int ERASER = 3;
	
	final int ID = 1;
	final int NAME = 2;
	
	Integer INVALID_VALUE = new Integer(-1);

	public Inventory() 
	{
		penList    = new ArrayList<Item>();
		pencilList = new ArrayList<Item>();
		eraserList = new ArrayList<Item>();
	}
	
	//To initiate the inventory class only once
	 public static Inventory getInstance() 
	 { 
	    if (single_instance == null)
	    {
		   single_instance = new Inventory(); 
	    }	
	     return single_instance; 
	  } 
	 
	//selects the type of the item
	private int getItemSelction() 
	{
		int selectedItem = 0;

		System.out.println("1. Pen");
		System.out.println("2. Pencil");
		System.out.println("3. Eraser");

		while (true) 
		{
			while(true) //catches the error when a user accidently inputs wrong types of data          
			{
				 try 
				 {
					selectedItem = scan.nextInt();
					break;
				 }
				catch (InputMismatchException e) 
				{
				    System.out.println("Invalid input. Please try again");
			        scan.nextLine();
			    }
			}
			
			if ((selectedItem > 0) && (selectedItem < 4)) 
			{
				break;
			} 
			System.out.println("Invalid choice. Please try again.");
		}	

		return selectedItem;
	}

	//gets the item arrayList that user wants 
	private ArrayList<Item> getItemArrayList(int itemTypeNum) 
	{
		if (itemTypeNum == PEN) 
		{
			return penList;
		} 
		else if (itemTypeNum == PENCIL) 
		{
			return pencilList;
		} 
		else if (itemTypeNum == ERASER) 
		{
			return eraserList;
		}
		
		return null; 
	}

	//Commands to search the item (Manager'excess)
	public int managerSearchInventoryItem() 
	{
		int searchedItemIndex = INVALID_VALUE;
		
		System.out.println("Which Types of Item do you want to search");
		int itemTypeNum = getItemSelction();
		
		ArrayList<Item> items = getItemArrayList(itemTypeNum);

		searchedItemIndex = searchInventoryItemIDorName(items);
		
		if (searchedItemIndex >= 0)
		{
			System.out.println(items.get(searchedItemIndex));
		}

		return searchedItemIndex;
	}

	//gets the way to find the item (through the ID or Name) 
	private int getIdOrName(String message) 
	{
		int idOrName = 0;

		System.out.println(message);
		System.out.println("1: by ID or \n2: by Name");

		while (true) 
		{
			while(true)  //catches the error when a user accidently inputs wrong types of data
			{
				 try 
				 {
					idOrName = scan.nextInt();
					break;
				 }
				catch (InputMismatchException e) 
				{
				    System.out.println("Invalid input. Please try again");
			        scan.nextLine();
			    }
			}
			if ((idOrName > 0) && (idOrName < 3)) 
			{
				break;
			}
			System.out.println("Invaild Choice. Please try again.");
		}	
		
		return idOrName;
	}
	
	//returns the index of the item that searched
	private int searchInventoryItemIDorName(ArrayList<Item> items) 
	{
		int searchedItemIndex = INVALID_VALUE;
		int searchItemID;
		
		int idOrName = getIdOrName("Search the item by ...");
		
		if (idOrName == ID) 
		{
			System.out.println("Type the ID of the item that you want to search ");
			while (true)
			{
				try
				{
					searchItemID = scan.nextInt();
					break;
				}
				catch (InputMismatchException e)
				{
					 System.out.println("Invalid input. Please try again");
				        scan.nextLine();
				}
			}
			searchedItemIndex = searchItemByID(items, searchItemID);
		} 
		else if (idOrName == NAME)
		{
			System.out.println("Type the name of the item that you want to search");
			String searchItemName = scan.next();
			searchedItemIndex = searchItemByName(items, searchItemName);
		}
		
		if (searchedItemIndex == INVALID_VALUE) 
		{
			System.out.println("Item not found");
		}
		
		return searchedItemIndex;
	}

	//searches the item by ID
	private int searchItemByID(ArrayList<Item> itemList, int id) 
	{
		int itemIndexNum = INVALID_VALUE;
		
		//searching the item
		for (int i = 0; i < itemList.size(); i++) 
		{
			if ((itemList.get(i)).getID() == id) 
			{
				itemIndexNum = i;
				break;
			}
		}
		
		return itemIndexNum;
	}

	//searches the item by Name
	private int searchItemByName(ArrayList<Item> itemList, String name) 
	{
		int itemIndexNum = INVALID_VALUE;

		//Searching the item
		for (int i = 0; i < itemList.size(); i++) {
			if ((itemList.get(i)).getName().equalsIgnoreCase(name)) 
			{
				itemIndexNum = i;
				break;
			}
		}

		return itemIndexNum;
	}
	
	//Commands to search the item (Customer'excess)
	public int customerSearchInventoryItem() 
	{
		int searchedItemIndex = INVALID_VALUE;
		
		System.out.println("Which Types of Item do you want to search");
		int itemTypeNum = getItemSelction();
		
		ArrayList<Item> items = getItemArrayList(itemTypeNum);

		searchedItemIndex = searchInventoryItemIDorName(items);
		
		//Printing out limited info for customers
		if (searchedItemIndex >= 0)
		{
			System.out.print("ID : " + items.get(searchedItemIndex).getID() + "\t");
			System.out.print("Name : " + items.get(searchedItemIndex).getName() + "\t");
			System.out.print("Price : " + items.get(searchedItemIndex).getPrice() + "\t");
			if(items.get(searchedItemIndex).getQuantity()>0) 
			{
				System.out.print("Stock : available" + "\t");
			}
			else 
			{
				System.out.print("Stock : Out of stock" + "\t");
			}
				
			if(itemTypeNum == PEN) 
			{
				System.out.print("Lead Size : " + ((Pen) items.get(searchedItemIndex)).getUniqueInfo());
			} 
			else if(itemTypeNum == PENCIL)
			{
				System.out.print("HB Level : " + ((Pencil) items.get(searchedItemIndex)).getUniqueInfo());
			}
			else if(itemTypeNum == ERASER)
			{
				System.out.print("Eraser Color : " + ((Eraser) items.get(searchedItemIndex)).getUniqueInfo());
			}
		} 

		return searchedItemIndex;
	}

	//prints out the item lists
	public void printOutItemList() 
	{
		Iterator<Item> itrPen = penList.iterator();

		while (itrPen.hasNext()) 
		{
			System.out.println(itrPen.next());
		}

		for (Item pencilList : pencilList) 
		{
			System.out.println(pencilList);
		}

		for (int i = 0; i < eraserList.size(); i++) 
		{
			System.out.println(eraserList.get(i));
		}
		
		System.out.println("Do you want to get the text file Y/N");
		String choice = scan.next();
		
		if(choice.equalsIgnoreCase("Y"))
		{
			printOutItemListToTxtFile();
			System.out.println("Completed. Check the ItemList.txt File");
		}
	}
	
	//prints out the item lists and save it in to the text file
	private void printOutItemListToTxtFile()
	{
		try 
		{
		    OutputStream output = new FileOutputStream("ItemList.txt");  //It saves to the workspace folder 
		    
		    String penStr = penList.toString();
		    byte[] penBy = penStr.getBytes();
		    
		    String penclilStr = pencilList.toString();
		    byte[] pencilBy= penclilStr.getBytes();
		    
		    String eraserStr = eraserList.toString();
		    byte[] eraserBy = eraserStr.getBytes();
		    
		    String line = "\n";
		    byte[] lineBy = line.getBytes();
		    
		    output.write(penBy);
		    output.write(lineBy);
		    output.write(pencilBy);
		    output.write(lineBy);
		    output.write(eraserBy);
		    
			output.close();
				
		}
		catch (Exception e) 
		{
	         e.getStackTrace();
		}	
	 }

	//adds the new item 
	public void addNewItems() 
	{
		int itemID;
		int itemQuantity = INVALID_VALUE;
		double itemPrice = 0.0;
		int cabinetNumber;
		
		System.out.println("Which Types of Item do you want to add");
		int itemTypeNum = getItemSelction();

		System.out.println("Put the Item ID (Integer Number Only)");
		while(true)  //catches the error when user accidently inputs wrong types of data
		{
			 try 
			 {
				itemID = scan.nextInt();
				break;
			 }
			catch (InputMismatchException e) 
			{
			    System.out.println("Invalid input. Please Put the ID again");
		        scan.nextLine();
		    }
		}

		System.out.println("Put the item Name (String)");
		String itemName = (scan.next()).toLowerCase();

		System.out.println("Put the item quantity (Integer Number Only)");
		while(true) 
		{
			try 
			{
				while(true) 
				{
				 itemQuantity = scan.nextInt();
					 if (itemQuantity >= 0) 
					 {
						break;
					 } 
					 else 
					 {
						System.out.println("Invalid quantity. Please put the quantity again.");
					 }
				}
				 break;
			 }
			catch (InputMismatchException e) 
			{
			    System.out.println("Invalid input. Please put the item quantity agian");
		        scan.nextLine();
		    }
		}

		System.out.println("Put the item price (USD)");
		
		while(true) 
		{
			try 
			{
				while(true) 
				{
					itemPrice = scan.nextDouble();
					if (itemPrice >= 0.0) 
					{
						break;
					}
					 else 
					 {
						System.out.println("Invalid price. Please put the item price again.");
					 }
				}
				 break;
			 }
			catch (InputMismatchException e) 
			{
			    System.out.println("Invalid input. Please put the item price agian");
		        scan.nextLine();
		    }
		}

		System.out.println("Put the item storage section (Alpabet char)");
		char locationSection = scan.next().charAt(0);

		System.out.println("Put the cabinet number (Integer Number Only)");
		while(true) 
		{
			 try 
			 {
				cabinetNumber = scan.nextInt();
				break;
			 }
			catch (InputMismatchException e) 
			{
			    System.out.println("Invalid input. Please Put the cabinet number again");
		        scan.nextLine();
		    }
		}

		Location itemLocation = new Location(locationSection, cabinetNumber);

		//adding the unique info
		if (itemTypeNum == PEN) 
		{
			System.out.println("Put the pen lead size");
			String penLeadSize = scan.next();
			Pen newPen = new Pen(itemID, itemName, itemQuantity, itemPrice, itemLocation, 
					             penLeadSize);
			penList.add(newPen);
			System.out.println("New pen is added");
		} 
		else if (itemTypeNum == PENCIL)
		{
			System.out.println("Put the pencil Lead HB Level");
			String pencilLeadHBLevel = scan.next();
			Pencil newPencil = new Pencil(itemID, itemName, itemQuantity, itemPrice, 
					                      itemLocation, pencilLeadHBLevel);
			pencilList.add(newPencil);
			System.out.println("New pencil is added");
		} 
		else if (itemTypeNum == ERASER) 
		{
			System.out.println("Put the eraser color");
			String eraserColor = scan.next();
			Eraser newEraser = new Eraser(itemID, itemName, itemQuantity, itemPrice, 
					                      itemLocation, eraserColor);
			eraserList.add(newEraser);
			System.out.println("New eraser is added");
		}
	}

	//Deletes the item that manager wants to delete
	public void deleteItems() 
	{
		System.out.println("Which types of item do you want to delete");
		int itemTypeNum = getItemSelction();
		ArrayList<Item> items = getItemArrayList(itemTypeNum);

		System.out.println("Want to delete the item by typing 1. ID or 2. Name");
		int idOrName = scan.nextInt();

		if (idOrName == ID) 
		{
			System.out.println("Type the ID of the item that you want to delete");
			int id = scan.nextInt();
			items.remove(searchItemByID(items, id));
		} 
		else if (idOrName == NAME) 
		{
			System.out.println("Type the name of the item that you want to delete");
			String name = scan.next();
			items.remove(searchItemByName(items, name));
		}
		
		System.out.println("The item is removed");
	}

	//commands to check the item stocks
	public void checkInventoryItemStocks() 
	{
		System.out.println("Which Types of Item do you want to check the stocks");
		int itemTypeNum = getItemSelction();
		ArrayList<Item> items = getItemArrayList(itemTypeNum);

		int idOrName;
		int selectedItemStocks = INVALID_VALUE;
		idOrName = getIdOrName("Want to delete the item by 1. ID or 2. Name");

		if (idOrName == ID) 
		{
			System.out.println("Type the ID of the item that you want to check the stocks");
			int id = scan.nextInt();
			selectedItemStocks = (items.get(searchItemByID(items, id))).getQuantity();
			checkItemStocks(selectedItemStocks);
		} 
		else if (idOrName == NAME) 
		{
			System.out.println("Type the name of the item that you want to check the stocks");
			String name = scan.next();
			selectedItemStocks = (items.get(searchItemByName(items, name))).getQuantity();
			checkItemStocks(selectedItemStocks);
		}
	}

	//check the stocks of the item
	private void checkItemStocks(int selectedItemSotcks) 
	{
		int itemStocks = selectedItemSotcks;

		if (itemStocks > 0) 
		{
			System.out.println("It's available. " + itemStocks + " left");
		} 
		else if (itemStocks == 0) 
		{
			System.out.println("Out of sotcks");
		} 
		else
		{
			System.out.println("Item not found");
		}
	}

	//returns the total prices of the items that are in the inventory
	public void getItemsTotalPrice() 
	{
		System.out.println("Which Types of Item do you want to get the total price");
		int itemTypeNum = getItemSelction();
		ArrayList<Item> items = getItemArrayList(itemTypeNum);
		
		double totalPrice = 0;

		//getting the total price by multiplying the quantity and the price of each item
		for (int i = 0; i < items.size(); i++) 
		{
			totalPrice += ((items.get(i)).getPrice() * 
					      ((double) (items.get(i)).getQuantity()));
		}

		System.out.println("Total Price of all the selected items in the invnetory is $" + totalPrice);
	}

	//returns the total quantities of the items that are in the inventory
	public void getItemsTotalQuantity() 
	{
		System.out.println("Which Types of Item do you want to get the total quantities");
		int itemTypeNum = getItemSelction();
		ArrayList<Item> items = getItemArrayList(itemTypeNum);

		int totalQuantity = 0;

		for (int i = 0; i < items.size(); i++) 
		{
			totalQuantity += (items.get(i)).getQuantity();
		}

		System.out.println("Total quantity of all the selected itmes is " + totalQuantity);
	}

	//Commands to change the information of the item that manager wants to change
	public void chageInfo() 
	{
		System.out.println("Which Types of Item do you want to chage the info");
		int itemTypeNum = getItemSelction();
		ArrayList<Item> items = getItemArrayList(itemTypeNum);

		System.out.println("Search the item that you want to change");
		int itemIndexNum = searchInventoryItemIDorName(items);
		
		if (itemIndexNum == INVALID_VALUE)
		{
			return;
		}

		System.out.println("Which info do you want to change");
		System.out.println("1. ID \n 2. Name \n 3. quantity \n 4. price \n 5. location \n "
				         + "6. Unique Info");
		int changeInfo = scan.nextInt();

		if ((changeInfo > 0) && (changeInfo < 6)) 
		{
			chagneCommonInfo(items, itemIndexNum, changeInfo);
		} 
		//changing the each item's unique info
		else if (changeInfo == 6) 
		{
			if (itemTypeNum == PEN)
			{
			   System.out.println("Put the Item new lead size");
			   String leadSize = scan.next();
			   ((Pen) items.get(itemIndexNum)).leadSize = leadSize;
			}
			else if (itemTypeNum == PENCIL)
			{
			  System.out.println("Put the Item new Lead HB Level");
			  String leadHBLevel = scan.next();
			  ((Pencil) items.get(itemIndexNum)).HBLevel = leadHBLevel;
			}
			else if (itemTypeNum == ERASER)
			{
			  System.out.println("Put the Item new EraserColor");
			  String eraserColor = scan.next();
			  ((Eraser) items.get(itemIndexNum)).eraserColor = eraserColor;
			}
		}
	}

	//changes the ID, Name, quantity, price, or location information
	private void chagneCommonInfo(ArrayList<Item> itemList, int itemIndexNum, int changeInfo) 
	{
		switch (changeInfo) 
		{
			case 1:
				System.out.println("Put the Item new ID");
				int itemID = scan.nextInt();
				itemList.get(itemIndexNum).itemID = itemID;
				break;
			case 2:
				System.out.println("Put the Item new Name");
				String itemName = scan.next();
				itemList.get(itemIndexNum).itemName = itemName;
				break;
			case 3:
				System.out.println("Put the Item new quantity");
				int itemQuantity = scan.nextInt();
				itemList.get(itemIndexNum).itemQuantity = itemQuantity;
				break;
			case 4:
				System.out.println("Put the Item new price");
				double itemPrice = scan.nextDouble();
				itemList.get(itemIndexNum).itemPrice = itemPrice;
				break;
			case 5:
				System.out.println("Put the Item new Storage section");
				char locationSection = scan.next().charAt(0);
				System.out.println("Put the Item new Cabinet Number");
				int cabinetNumber = scan.nextInt();
				(itemList.get(itemIndexNum).itemLocation).setSection(locationSection);
				(itemList.get(itemIndexNum).itemLocation).setCabnit(cabinetNumber);
				break;
			default:
				System.out.println("Invalid Choice");
		}
	}

	//purchases the item that customer wants
	public void purchaseItems() 
	{
		System.out.println("Which Types of Item do you want to buy");
		int itemTypeNum = getItemSelction();
		ArrayList<Item> items = getItemArrayList(itemTypeNum);
		
		System.out.println("Search the item that you want to buy");
		int itemIndexNum = searchInventoryItemIDorName(items);
		
		if (itemIndexNum == INVALID_VALUE)
		{
			return;
		}
		
		System.out.println("How many items do you want to buy?");
		int itemPurchasedQuantity = scan.nextInt();

		if (items.get(itemIndexNum).getQuantity() >= itemPurchasedQuantity) 
		{
			//decreasing the quantity of the item
			items.get(itemIndexNum).itemQuantity -= itemPurchasedQuantity;
			System.out.println("Purchased");
		} 
		else
		{
			System.out.println("Not enough items. Please reduce the quantity or change the item");
		}
	}
	
}