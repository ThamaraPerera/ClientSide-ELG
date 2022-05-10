package com;

public class Customer {
	private int id;
	private String name;
	private String address;
	private String phonenumber;
	private String username;
	private String password;
	
	public Customer(int id, String name, String address, String phonenumber, String username, String password) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phonenumber = phonenumber;
		this.username = username;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
