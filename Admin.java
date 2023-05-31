import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
public class Admin extends User
{
	public Admin(String name,String email,String password)
	{
		super(name,email,password);
	}
	public Admin() { super();}
	public void menu()
	{
		
			System.out.println("\nBus Summary");
			for(Map.Entry<String,Bus> e:Main.bus.entrySet() )
			{
				Bus bus=e.getValue();
				System.out.println("\n"+bus.getName());
				int book=bus.getBooked();
				int cancel=bus.getCancelled();
				int fare=bus.getFare();
				boolean ac=bus.getAc();
				
				int total=book * fare;
				if(ac)
					fare=fare/2;
				else
					fare=fare/4;
				total=total+(fare * cancel);
				System.out.println("Number of seats filled: "+book);
				System.out.println("Total Fare Collected: "+total+" ("+book+" tickets + "+cancel+" cancellation )");
				System.out.println("Seat Details");
				int i=1;
				for(Map.Entry<String,Ticket> e1:Main.tickets.entrySet())
				{
					Ticket t=e1.getValue();
					if(t.getBus_id().equals(bus.getId()) )
					{
						System.out.println("\n"+i+". Ticket ID:"+t.getId());
						System.out.print("Bus : "+t.getBus_id()+", ");
						System.out.print("Seat Number : "+t.getSeat_number()+", ");
						System.out.print("Name : "+t.getName()+", ");
						System.out.println("Gender : "+t.getGender()+"");	
						i++;
					}
				}
				if(i==1)
					System.out.println("-");
				
			}
	}
}
