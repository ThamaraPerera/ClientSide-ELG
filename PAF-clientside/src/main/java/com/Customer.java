package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer{
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost/userdb", "root", "Thamara@123");

			// Successful connection
			System.out.print("Successfully connected");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	// Method to get Customer details
	public String readCustomer() {
		String output = "";

		try {

			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Customer Name</th>"
					+ "<th>Customer Address</th><th>Customer Phone number</th>" + "<th>Username</th>" + "<th>Password</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from logtbl";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				String customerID = Integer.toString(rs.getInt("customerID"));
				String customerName = rs.getString("customerName");
				String customerAddress = rs.getString("customerAddress");
				String customerPhone = rs.getString("customerPhone");
				String customerUname = rs.getString("customerUname");
				String customerPwd = rs.getString("customerPwd");

				// Add into the html table
				output += "<tr><td><input id='hidcustomerIDSave' name='hidcustomerIDSave' type='hidden' value='"
						+ customerID + "'>" + customerName + "</td>";

				output += "<td>" + customerAddress + "</td>";
				output += "<td>" + customerPhone + "</td>";
				output += "<td>" + customerUname + "</td>";
				output += "<td>" + customerPwd + "</td>";

				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-customerID='"
						+ customerID + "'>" + "</td></tr>";

			}

			con.close();

			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the customer data.";
			System.err.println(e.getMessage());
		}
		System.out.println(output);
		return output;
	}

	// Insert new Customer
	public String insertCustomer(String customerName, String customerAddress, String customerPhone, String customerUname, String customerPwd) 
	{
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into logtbl (`userid`,`username`,`useraddress`,`userphonenumber`,`userusername`,`userpassword`)"
					+ " values (?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// assigning values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customerName);
			preparedStmt.setString(3, customerAddress);
			preparedStmt.setString(4, customerPhone);
			preparedStmt.setString(5, customerUname);
			preparedStmt.setString(6, customerPwd);

			// execute the statement
			preparedStmt.execute();
			con.close();

			// Create JSON Object to show success message
			String newCustomer = readCustomer();
			output = "{\"status\":\"success\", \"data\":\"" + newCustomer + "\"}";
		} catch (Exception e) {
			// Create JSON Object to show Error message
			output = "{\"status\":\"error\", \"data\": \"Error while Inserting Customer data.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Update Customer Profile
	public String updateCustomer(String customerID, String customerName, String customerAddress, String customerPhone, String customerUname, String customerPwd) 
	{
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE logtbl SET username=?,useraddress=?,userphonenumber=?,userusername=?,userpassword=? WHERE userid=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, customerName);
			preparedStmt.setString(2, customerAddress);
			preparedStmt.setString(3, customerPhone);
			preparedStmt.setString(4, customerUname);
			preparedStmt.setString(5, customerPwd);
			preparedStmt.setInt(6, Integer.parseInt(customerID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			// create JSON object to show success message
			String newCustomer = readCustomer();
			output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";
		} catch (Exception e) {
			// create JSON object to show error message
			output = "{\"status\":\"error\", \"data\": \"Error while Updating Customer datails.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Delete Customer Profile
	public String deleteCustomer(String customerID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "DELETE FROM logtbl WHERE userid=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(customerID));
			// execute the statement
			preparedStmt.execute();
			con.close();

			// create JSON object to show success message
			String newCustomer = readCustomer();
			output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";
		} catch (Exception e) {
			// create JSON object to show error message
			output = "{\"status\":\"error\", \"data\": \"Error while Deleting Customer profile.\"}";
			System.err.println(e.getMessage());

		}

		return output;
	}
}

