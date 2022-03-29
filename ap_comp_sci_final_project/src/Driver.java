import java.util.Scanner;

public class Driver 
{
	static Inventory inventory = null; 
	
	public static void main(String[] args) {
		
		boolean using = true;
		
		Scanner scan = new Scanner(System.in);
		
		inventory = Inventory.getInstance(); //To initiate the item arrayLists once
				
		Manager manager = new Manager();
		Customer customer = new Customer();

		int logInStatus;

		System.out.println("Hi! Good Morning \n");

		while(using) 
		{
			System.out.println("Sign in as a \n 1. Customer \n 2. Manager \n (Type 0 to sign out)");
			logInStatus = scan.nextInt();
			if (logInStatus == 1) 
			{
				customer.shopping();
			} 
			else if (logInStatus == 2) 
			{
				manager.managing();
			} 
			else if (logInStatus == 0) 
			{
				using = false;
			} 
			else 
			{
				System.out.println("Invalid choice. Try again");
			}
		}
		System.out.println("Good bye!");
		scan.close();
	}

}
