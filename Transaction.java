
public class Transaction 
{
	static int i;
	int id;
	Ticket t;
	String email;
	String status;
	public Transaction(Ticket t, String email, String status) 
	{
		super();
		this.t = t;
		this.email = email;
		this.status = status;
		this.id=i++;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Ticket getT() {
		return t;
	}
	public void setT(Ticket t) {
		this.t = t;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
