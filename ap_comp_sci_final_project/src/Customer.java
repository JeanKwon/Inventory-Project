import java.util.Scanner;

public class Customer implements Functions 
{
	int customerAct;
	
	Scanner scan = new Scanner(System.in);

	Inventory inventory = null;

	public Customer()
	{
		inventory = Inventory.getInstance();
	}

	public void shopping() 
	{
		boolean isCustomerUsing = true;

		while (isCustomerUsing) 
		{
			//Customer's choices
			System.out.println("");
			System.out.println("Welcome. What do you want to do?");
			System.out.println("1. Search the item");
			System.out.println("2. Check the stock of the item");
			System.out.println("3. Buy the items");
			System.out.println("9. Sign Out");

			System.out.println("Type the number that you want to do?");

			customerAct = scan.nextInt();

			switch (customerAct) 
			{
				case 1:
					searchItem();
					break;
				case 2:
					checkStocks();
					break;
				case 3:
					inventory.purchaseItems();
					break;
				case 9:
					System.out.println("Thank You");
					isCustomerUsing = false;
					break;
				default:
					System.out.println("Invalid choice");
			}
		}
	}

	@Override
	public void searchItem() 
	{
		inventory.customerSearchInventoryItem();
	}

	@Override
	public void checkStocks() 
	{
		inventory.checkInventoryItemStocks();		
	}
}
