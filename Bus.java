import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class Bus 
{
	private String id;
	private String name;
	private String type;
	private boolean ac;
	private int available_seats;
	private int booked;
	private int cancelled;
	private int fare;
	
	
	Scanner sc=new Scanner(System.in);
	
	
	
	public Bus(String id, String type, boolean ac, int available_seats, int booked, int cancelled, int fare, String name)
	{
		this.id = id;
		this.name = name;
		this.type = type;
		this.ac = ac;
		this.available_seats = available_seats;
		this.booked = booked;
		this.cancelled = cancelled;
		this.fare = fare;
		
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean getAc() {
		return ac;
	}

	public void setAc(boolean ac) {
		this.ac = ac;
	}

	public int getAvailable_seats() {
		return available_seats;
	}

	public void setAvailable_seats(int available_seats) {
		this.available_seats = available_seats;
	}

	public int getBooked() {
		return booked;
	}

	public void setBooked(int booked) {
		this.booked = booked;
	}
	public int getCancelled() {
		return cancelled;
	}
	public void setCancelled(int cancelled) {
		this.cancelled = cancelled;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}

	public abstract void showStatus(Customer c);
	public abstract void cancelSeat(String seatNo);
	
	
}
