import java.util.Comparator;
import java.util.Map.Entry;


public class BusComparator implements Comparator<Entry<String,Bus>>
{
	public int compare(Entry<String,Bus> e1,Entry<String,Bus> e2)
	{
		Bus b1=e1.getValue();
		Bus b2=e2.getValue();
		if(b1.getAvailable_seats()>b2.getAvailable_seats())
			return -1;
		else if(b1.getAvailable_seats()<b2.getAvailable_seats())
			return 1;
		else
		{
			if(b1.getAc() && !b2.getAc())
				return -1;
			else if(!b1.getAc() && b2.getAc())
				return 1;
			else
			{
				if(b1.getType().equals("sleeper"))
				{
					return -1;
				}
				else  if(b2.getType().equals("sleeper"))
					return 1;
				else
					return 0;
			}
		}
	}
}
