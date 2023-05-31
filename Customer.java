import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.regex.Pattern;


public class Customer extends User
{
	
	private String gender;
	private String contact;
	private int tickets_booked;
	private ArrayList<Ticket> tickets;
	
	public Customer(){ super();}
	public Customer(String name,String email,String password,String gender,String contact,int tickets_booked)
	{
		super(name,email,password);
		this.gender=gender;
		this.contact=contact;
		this.tickets_booked=tickets_booked;
		tickets=new ArrayList<Ticket>();
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public int getTickets_booked() {
		return tickets_booked;
	}
	public void setTickets_booked(int tickets_booked) {
		this.tickets_booked = tickets_booked;
	}
	
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}
	public void addTicket(Ticket t) {
		tickets.add(t);
	}
	public static void signUp()
	{
		String name,email,contact,pwd,gender;
		System.out.println("Enter name:");
		name=sc.nextLine();

		do 
		{
			System.out.println("Enter email:");
			email=sc.nextLine();
			if(!Pattern.matches("[_a-zA-Z1-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*",email))
			{
				System.out.println("Enter valid Email");
				continue;
			}
			
				if(!Main.user.containsKey(email))
				break;
				else
				{
					System.out.println("User with this email already exist..");
				    continue;
				}
		}
		while(true);
		
		do
		{
			System.out.println("Enter password:");
		    pwd=sc.nextLine();
			if(!Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{5,20}$",pwd))
			{
				System.out.println("Password should have atleast one uppercase.one lower case,one digit,one special character.length should be 8-20");
				continue;
			}
			break;
		}
		while(true);
		
		do
		{
			System.out.println("Enter Contact number:");
		    contact=sc.nextLine();
			if(!Pattern.matches("[6-9]{1}[0-9]{9}",contact))
			{
				System.out.println("Invalid contact Number");
				continue;
			}
			break;
		}
		while(true);
		
		do
		{
			System.out.println("Enter your gender (M/F) : ");
			gender=sc.nextLine();
			if(gender.equalsIgnoreCase("M")||gender.equalsIgnoreCase("F"))
			{
				break;
			}
			else
			{
				System.out.println("Gender value should be 'M' or 'F'");
				continue;
			}
			 
		}
		while(true);
		
			Customer obj=new Customer(name,email,pwd,gender,contact,0);
			Main.user.put(email, obj);
			System.out.println("SignUp successful..You can SignIn");
		
	}
	
	public void menu()
	{
		int op;
		do
		{
			System.out.println("\n1.Book Tickets");
			System.out.println("2.View Tickets");
			System.out.println("3.Cancel Tickets");
			System.out.println("4.Exit\n");
			op=Integer.parseInt(sc.nextLine());
			switch(op)
			{
			case 1:
				bookTickets();
				break;
			case 2:
				viewTickets();
			    break;
			case 3:
				cancelTickets();
				break;
			case 4:
				break;
			default:
				System.out.println("Please enter a valid option\n");
			}
			
		}
		while(op!=4);
	}

	public void bookTickets()
	{
		List<Entry<String,Bus>> al=new ArrayList<Entry<String,Bus>>();
		for(Map.Entry<String,Bus> e:Main.bus.entrySet())
		{
			Bus bus=e.getValue();
			if(bus.getAvailable_seats()!=0)
			{
				al.add(e);
			}
		}
		Collections.sort(al,new BusComparator());
		int op;
		Bus bus;
		int i;
		for(i=0;i<al.size();i++)
		{
			bus=al.get(i).getValue();
			System.out.println((i+1)+" -  "+bus.getName()+" - "+bus.getAvailable_seats()+" seats");
		}
		System.out.println(++i+" - Exit");
		System.out.println("\nSelect the Bus:");
		do
		{
			op=Integer.parseInt(sc.nextLine());
			
			if(op>0 && op<=al.size())
			{
				bus=al.get(op-1).getValue();
				
				bus.showStatus(this);
				break;
			}
			else if(op==i)
			{
				break;
			}
			else
			{
				System.out.println("Please enter a valid option\n");
				continue;
			}
		}
		while(op!=i);
		tickets_booked=tickets.size();
	}
	
	public void viewTickets()
	{
		if(tickets.size()==0)
		{
			System.out.println("No Tickets Booked..\n");
			return;
		}
		System.out.println("Tickets Booked By You : "+tickets.size());
			for(int i=0;i<tickets.size();i++)
			{
				Ticket t=tickets.get(i);
				System.out.println("\n"+(i+1)+". Ticket ID:"+t.getId());
				Bus bus=Main.bus.get(t.getBus_id());
				System.out.print("Bus : "+bus.getName()+", ");
				System.out.print("Seat Number : "+t.getSeat_number()+", ");
				System.out.print("Name : "+t.getName()+", ");
				System.out.println("Gender : "+t.getGender()+"");	
			}
		
		
	}
	
	public void cancelTickets()
	{
		viewTickets();
		if(tickets_booked==0)
		{
			return;
		}
		System.out.println("\nEnter Ticket ID to be cancelled  OR type 'ALL' to cancel all the tickets");
		String s;
		do
		{
			s=sc.nextLine();
			Ticket t;
			if(s.equalsIgnoreCase("All"))
			{
				while(tickets.size()!=0)
				{
					t=tickets.get(0);
					cancelTickets(t);
					Main.tickets.remove(t.getId());
					tickets.remove(t);
				}
				break;
			}
			int f=0;
			for(int i=0;i<tickets.size();i++)
			{
				t=tickets.get(i);
				if(t.getId().equalsIgnoreCase(s))
				{
					cancelTickets(t);
					tickets.remove(t);
					Main.tickets.remove(t.getId());
					f=1;
					break;
				}
			}
			if(f==1)
				break;
			System.out.println("Enter valid Ticket Number : ");	
		}
		while(true);
		tickets_booked=tickets.size();
		System.out.println("Cancellation successful.\n");
	}
    
	public void cancelTickets(Ticket t)
	{
		Transaction tr=new Transaction(t,getEmail(),"cancelled");
		Main.transaction.put(tr.getId(),tr);
		
		Bus bus=Main.bus.get(t.getBus_id());
		bus.cancelSeat(t.getSeat_number());
		
		
	}
	
}

