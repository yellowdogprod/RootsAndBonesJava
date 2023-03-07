package com.ydp.rnb.views;

public class UserView {

	private long id;
	private String firstName;
	private String lastName;
	
	public UserView(long i, String fn, String ln) {
		id = i;
		firstName = fn;
		lastName = ln;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
}
