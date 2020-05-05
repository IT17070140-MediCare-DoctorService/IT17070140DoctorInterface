package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctor {
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/medicare", "root", "");
//			con = DriverManager.getConnection(
//					"jdbc:mysql://127.0.0.1:3306/medicare?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
//					"root", "");
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertDoctor(String doctorName, String email, String location, String phoneNumber) {

		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting";
			}

			String query = " INSERT INTO doctor (doctorName, email, location, phoneNumber) VALUES (?, ?, ?, ?)";

			PreparedStatement preparedstmt = con.prepareStatement(query);

			preparedstmt.setString(1, doctorName);
			preparedstmt.setString(2, email);
			preparedstmt.setString(3, location);
			preparedstmt.setString(4, phoneNumber);

			preparedstmt.execute();
			con.close();
			
			String doctor = readDoctor();
			output = "{\"status\":\"success\", \"data\": \"" + doctor + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String readDoctor() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			output = "<table border='1'><tr><th>Doctor ID</th>" + "<th>Doctor Name</th><th>Email Address</th>"+ "<th>Location</th>"
					+ "<th>Doctor Mobile</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from doctor";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String doctorId = Integer.toString(rs.getInt("doctorId"));
				String doctorName = rs.getString("doctorName");
				String email = rs.getString("email");
				String location = rs.getString("location");
				String phoneNumber = rs.getString("phoneNumber");
				
				
				
				output += "<tr><td><input id='hidItemIDUpdate'" + "name='hidItemIDUpdate' type='hidden'" + "value='"
						+ doctorId + "'>" + doctorId + "</td>";
				//output += "<tr><td>" + doctorId + "</td>";
				//output += "<tr><td>" + doctorId + "</td>";
				output += "<td>" + doctorName + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + location + "</td>";
				output += "<td>" + phoneNumber + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button'" + "value='Update'"
						+ "class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove' type='button'"
						+ "value='Remove'" + "class='btnRemove btn btn-danger' data-itemid='" + doctorId + "'>"
						+ "</td></tr>";
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the doctor.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String updateDoctor(String doctorId, String doctorName, String email, String location, String phoneNumber) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating";
			}

			String query = "UPDATE doctor SET doctorName=?, email=?, location=?, phoneNumber=? WHERE doctorId=?";

			PreparedStatement preparedstmt = con.prepareStatement(query);

			preparedstmt.setString(1, doctorName);
			preparedstmt.setString(2, email);
			preparedstmt.setString(3, location);
			preparedstmt.setString(4, phoneNumber);
			preparedstmt.setInt(5, Integer.parseInt(doctorId));

			preparedstmt.execute();
			con.close();

			String doctor = readDoctor();
			output = "{\"status\":\"success\", \"data\": \"" + doctor + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteDoctor(String doctorId) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			String query = "delete from doctor where doctorId=?";

			PreparedStatement preparedstmt = con.prepareStatement(query);

			preparedstmt.setInt(1, Integer.parseInt(doctorId));

			preparedstmt.execute();
			con.close();

			String doctor = readDoctor();
			output = "{\"status\":\"success\", \"data\": \"" + doctor + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while Deleting the item.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}
}
