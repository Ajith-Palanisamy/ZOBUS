import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

public class SleeperBus extends Bus
{
	char[][] seatings={{'A','A'},{'A','A'},{'A','A'},{'A','A'},{'A','A'},{'A','A'}};
	public SleeperBus(String id, boolean ac, int available_seats, int booked, int cancelled, int fare, String name)
	{
		super(id,"sleeper",ac,available_seats,booked,cancelled,fare,name);
	}
	public void showStatus(Customer c)
	{
		if(getAvailable_seats()<=0)
		{
			System.out.println("Seat is not available..");
			return;
		}
		System.out.println("\n"+getName()+" - Seating Status ");
		System.out.println("Available seats : "+getAvailable_seats()+"");
		System.out.println("\nLower Deck");
		System.out.println("     A  B  ");
		
		for(int i=0;i<6;i++)
		{
			if(i==2 || i==5)
			{
				System.out.println("--------------------");
				System.out.println("--------------------");
			}
			if(i==3)
			{
				System.out.println("\nUpper Deck");
			}
			
			System.out.println("  "+(i+1)+"  "+seatings[i][0]+"  "+seatings[i][1]+"  ");
		}
		int n;
		System.out.println("\nEnter number of tickects:");
		do
		{
			n=Integer.parseInt(sc.nextLine());
			if(n>getAvailable_seats())
			{
				System.out.println("Available seats : "+getAvailable_seats()+".Enter a valid count");
				continue;
			}
			break;
		}
		while(true);
		ArrayList<String> seatNoList=new ArrayList<String>();
		ArrayList<String> genderList=new ArrayList<String>();
		ArrayList<String> nameList=new ArrayList<String>();
		
		for(int i=1;i<=n;i++)
		{
			System.out.println("Person "+i+" :");
			System.out.println("Enter name");
			String name=sc.nextLine();
			System.out.println("Enter Gender");
			String gender;
			do 
			{
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
			gender=gender.toUpperCase();
			System.out.println("Enter Seat Number:");
			String seatNo;
			do 
			{
				seatNo=sc.nextLine();
				if(!Pattern.matches("[AB][1-6]",seatNo))
				{
					System.out.println("Enter a valid SeatNo");
					continue;
				}
				else
				{
					int row=Integer.valueOf(seatNo.substring(1))-1;
					int column=seatNo.charAt(0)-65;
					
					int t=0;
					if(row==1 || row==4)
					{
						t=row-1;
					}
					else if(row==0 || row==3)
						t=row+1;
					
						if(seatings[row][column]!='A')
						{
							System.out.println(seatNo+" is not Available.Enter a valid seat number.");
							continue;
						}
						
						if(t!=0)
						{
						if(seatings[t][column]=='F' && gender.equalsIgnoreCase("M"))
						{
							boolean bookedByUser=false;
							Iterator<Ticket> itr=c.getTickets().iterator();
							while(itr.hasNext())
							{
								Ticket obj=itr.next();
								if(obj.getBus_id().equals(getId()) && obj.getSeat_number().equals(seatNo.substring(0,1)+t) )
								{
									bookedByUser=true;
									break;
								}
							}
							if(!bookedByUser)
							{
								System.out.println(seatNo+" is allocated for female passenger.Select a different seat.");
								continue;
							}
						}
							
						}
						if(!seatNoList.contains(seatNo))
						{
							seatNoList.add(seatNo);
							genderList.add(gender);
							nameList.add(name);
							System.out.println("Seat "+seatNo+" is selected.\n");
							break;
						}
						else
						{
							System.out.println("Seat "+seatNo+" is already selected.Select a different seat.");
						}
						
				
				}
			}
			while(true);	
		}
		
		int total=getFare()*n;
		System.out.println("\nNumber of tickets booked : "+n);
		System.out.println("Ticket Fare : "+getFare());
		System.out.println("Total amount : "+total);
		System.out.println("\nConfirm your tickets :");
		String op;
		do
		{
			System.out.println("1.Confirm");
			System.out.println("2.Cancel");
		    op=sc.nextLine();
			if(op.equals("2"))
				return;
			else if(op.equals("1"))
				break;
			else
			{
				System.out.println("Enter valid option..");
			}
		}
		while(true);
		
		String gender,seatNo;
		int row,column;
		for(int i=0;i<n;i++)
		{
				seatNo=seatNoList.get(i);
				gender=genderList.get(i);
				row=Integer.valueOf(seatNo.substring(1))-1;
				column=(seatNo.charAt(0))-65;
				String name=nameList.get(i);
				gender=genderList.get(i);
				seatings[row][column]=gender.charAt(0);
				setAvailable_seats(getAvailable_seats()-1);
				setBooked(getBooked()+1);
				Ticket t=new Ticket(c.getEmail(),getId(),seatNo,gender,name);
				c.addTicket(t);
				Main.tickets.put(getId()+"-"+seatNo,t);
				Transaction tr=new Transaction(t,c.getEmail(),"booked");
				Main.transaction.put(tr.getId(),tr);
		}
		System.out.println("Tickets booked successfully..");

	}
	
	public void cancelSeat(String seatNo)
	{
		int row=Integer.valueOf(seatNo.substring(1))-1;
		int column=(seatNo.charAt(0))-65;
		seatings[row][column]='A';
		setAvailable_seats(getAvailable_seats()+1);
		setBooked(getBooked()-1);
		setCancelled(getCancelled()+1);
	}
}
