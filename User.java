import java.util.*;
import java.sql.*;
public abstract class User 
{
	private String name;
	private String email;
	private String password;
	static Scanner sc=new Scanner(System.in);

	public User(){}
	
	public User(String name,String email,String password)
	{
		this.name=name;
		this.email=email;
		this.password=password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static void signIn()
	{
		do
		{
			System.out.println("Enter username : ");
			String email=sc.nextLine();
			
			if(Main.user.containsKey(email))
			{
				System.out.println("Enter password:");
				String pwd=sc.nextLine();
				User obj=Main.user.get(email);
				if(pwd.equals(obj.getPassword()) && obj.getName().equals("admin"))
				{
					System.out.println("Welcome Admin");
					obj=(Admin)obj;
					obj.menu();
					break;
				}
				else if(pwd.equals(obj.getPassword()))
				{
					obj=(Customer)obj;
					String name=obj.name;
					System.out.println("\nWelcome "+name);
					obj.menu();
					break;
				}	
				else
				{
					System.out.println("Invalid Password\n");
					continue;
				}
						
			}
				else
				{
					System.out.println("User not exist..Please SignUp");
					break;
				}
		}
		while(true);
	}
	
	public abstract void menu();

}

