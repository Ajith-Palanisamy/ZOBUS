import java.sql.*;
import java.util.*;
public class Main 
{
	static Scanner sc=new Scanner(System.in);
	static LinkedHashMap<String,User> user=new LinkedHashMap<String,User>();
	static LinkedHashMap<String,Bus> bus=new LinkedHashMap<String,Bus>();
	static LinkedHashMap<String,Ticket> tickets=new LinkedHashMap<String,Ticket>();
	static LinkedHashMap<Integer,Transaction> transaction=new LinkedHashMap<Integer,Transaction>();
	
	static
	{
		User admin=new Admin("admin","admin@gmail.com","123");
		user.put(admin.getEmail(),admin);
		user.put("ajith@gmail.com",new Customer("Ajith","ajith@gmail.com","123","M","6382778409",0));
		user.put("abi@gmail.com",new Customer("Abi","abi@gmail.com","123","F","6382778409",0));
		
		String bus_id="AC001";
		Bus busObj=new SeaterBus(bus_id,true,12,0,0,550,"AC Seater");
		bus.put(bus_id, busObj);
		bus_id="AC002";
		busObj=new SleeperBus(bus_id,true,12,0,0,700,"AC Sleeper");
		bus.put(bus_id, busObj);
		bus_id="NON-AC001";
		busObj=new SeaterBus(bus_id,false,12,0,0,450,"Non-AC Seater");
		bus.put(bus_id, busObj);
		bus_id="NON-AC002";
		busObj=new SleeperBus(bus_id,false,12,0,0,600,"Non-AC Sleeper");
		bus.put(bus_id, busObj);
		
	}
	
	public static void main(String[] args) 
	{
		System.out.println("**********************");
		System.out.println("ZOBUS APPLICATION");
		System.out.println("**********************");
		int op;
		do
		{
			System.out.println("\n1.SignIn");
			System.out.println("2.SignUp");
			System.out.println("3.Exit");
			op=Integer.parseInt(sc.nextLine());
			switch(op)
			{
			case 1:
				User.signIn();
				break;
			case 2:
				Customer.signUp();
				break;
			case 3:
				System.out.println("Thank you !!Application Ended");
				break;
			default:
				System.out.println("Please enter a valid option\n");
			
			}
		}
		while(op!=3);
		
	}
}
