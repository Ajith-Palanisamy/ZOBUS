
public class Ticket 
{
	private String id;
	private String email;
	private String bus_id;
	private String seat_number;
	private String gender;
	private String name;
	
	public Ticket(String email, String bus_id, String seat_number, String gender, String name) {
		super();
		this.id=bus_id+"-"+seat_number;
		this.email = email;
		this.bus_id = bus_id;
		this.seat_number = seat_number;
		this.gender = gender;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBus_id() {
		return bus_id;
	}
	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}
	public String getSeat_number() {
		return seat_number;
	}
	public void setSeat_number(String seat_number) {
		this.seat_number = seat_number;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId()
	{
		return this.id;
	}
	

}
