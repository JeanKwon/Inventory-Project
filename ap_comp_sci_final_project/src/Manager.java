import java.util.Scanner;

public class Manager implements Functions 
{
	int managerAct;    
	
	Scanner scan = new Scanner(System.in);
	
	Inventory inventory = null;

	public Manager()
	{
		inventory = Inventory.getInstance();
	}
	
	public void managing() 
	{
		boolean isManagerWorking = true;

		while (isManagerWorking) 
		{
			//manager's choices
			System.out.println("");
			System.out.println("Welcome. What do you want to do?");
			System.out.println("1. Search for an item");
			System.out.println("2. Get the whole inventory items list");
			System.out.println("3. Check the stock of the item");
			System.out.println("4. Add the new item");
			System.out.println("5. Delete the item");
			System.out.println("6. Change the info of the item");
			System.out.println("7. Get the total price");
			System.out.println("8. Get the total quantities");
			System.out.println("9. Sign Out");

			System.out.println("Type the number that you want to do?");

			managerAct = scan.nextInt();

			switch (managerAct) 
			{
				case 1:
					searchItem();
					break;
				case 2:
					inventory.printOutItemList();
					break;
				case 3:
					checkStocks();
					break;
				case 4:
					inventory.addNewItems();
					break;
				case 5:
					inventory.deleteItems();
					break;
				case 6:
					inventory.chageInfo();
					break;
				case 7:
					inventory.getItemsTotalPrice();
					break;
				case 8:
					inventory.getItemsTotalQuantity();
					break;
				case 9:
					System.out.println("Good work Today");
					isManagerWorking = false;
					break;
				default:
					System.out.println("Invalid choice");
			}
		}
	}

	@Override
	public void searchItem() 
	{
		inventory.managerSearchInventoryItem();
	}

	@Override
	public void checkStocks() 
	{
		inventory.checkInventoryItemStocks();
	}

}
