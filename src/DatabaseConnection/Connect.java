package DatabaseConnection;

import java.sql.*;

public class Connect {
	public PreparedStatement ps;
	public Connection connection;
	public ResultSet rs;

	public Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet userLogin(String username, String password) {

		try {
			ps = connection.prepareStatement("select*from user where username = ? and password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet addUser(String username, String email, String password, String role) {

		try {
			ps = connection.prepareStatement("insert into user (username, email, password, role) values (?,?,?,?)");
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, role);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet getUser() {

		try {
			ps = connection.prepareStatement("Select*from user");
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet deleteUser(String name, String email, String role) {

		try {
			ps = connection.prepareStatement("delete from user where username = ? and email = ? and role = ?");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, role);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet updateUser(String username, String email, String role, String userID) {

		try {
			ps = connection.prepareStatement("Update user set username = ?, email = ?, role = ? where userID = ?");
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, role);
			ps.setString(4, userID);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet getDoctor() {

		try {
			ps = connection.prepareStatement("Select*from user where role = 'Doctor'");
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return rs;
	}

	public ResultSet getPatient() {

		try {
			ps = connection.prepareStatement("Select*from patient");
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet addPatient(String id, String name, String number, String address) {

		try {
			ps = connection.prepareStatement("Insert into patient values (?,?,?,?)");
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, number);
			ps.setString(4, address);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet deletePatient(String id) {

		try {
			ps = connection.prepareStatement("Delete from patient where patientID = ?");
			ps.setString(1, id);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}

	// channel
	public ResultSet getChannel() {

		try {
			ps = connection.prepareStatement("Select*from channel");
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet addChannel(int channelNo, String doctorName, String patientName, String roomNo, String date) {

		try {
			ps = connection.prepareStatement("Insert into channel values (?,?,?,?,?)");
			ps.setInt(1, channelNo);
			ps.setString(2, doctorName);
			ps.setString(3, patientName);
			ps.setString(4, roomNo);
			ps.setString(5, date);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet deleteChannel(int channelNo) {

		try {
			ps = connection.prepareStatement("Delete from channel where channelNo = ?");
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet doctorChannel(String doctorName) {

		try {
			ps = connection.prepareStatement("Select*from channel where doctorName = ?");
			ps.setString(1, doctorName);
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet getPrescription() {

		try {
			ps = connection.prepareStatement("Select*from prescription");
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet getChannelPrescription(String channelNo) {

		try {
			ps = connection.prepareStatement("Select*from prescription where channelID = ?");
			ps.setString(1, channelNo);
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet addPrescription(String channelID, String deseaseType, String desc) {

		try {
			ps = connection
					.prepareStatement("insert into prescription (channelID, deseaseType, description) values (?,?,?)");
			ps.setString(1, channelID);
			ps.setString(2, deseaseType);
			ps.setString(3, desc);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet updatePrescription(String channelID, String deseaseType, String desc, String prescriptionID) {

		try {
			ps = connection.prepareStatement(
					"update prescription set channelID = ?, deseaseType = ?, description = ? where prescriptionID = ?");
			ps.setString(1, channelID);
			ps.setString(2, deseaseType);
			ps.setString(3, desc);
			ps.setString(4, prescriptionID);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet getDrug() {

		try {
			ps = connection.prepareStatement("select*from drug");
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet addDrug(String name, int price, int qty) {

		try {
			ps = connection.prepareStatement("insert into drug (drugName, price, qty) values (?,?,?)");
			ps.setString(1, name);
			ps.setInt(2, price);
			ps.setInt(3, qty);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet updateDrug(String name, int price, int qty, String drugID) {

		try {
			ps = connection.prepareStatement("update drug set drugname = ?, price = ?, qty = ? where drugID = ?");
			ps.setString(1, name);
			ps.setInt(2, price);
			ps.setInt(3, qty);
			ps.setString(4, drugID);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet deleteDrug(String id) {

		try {
			ps = connection.prepareStatement("delete from drug where drugID = ?");
			ps.setString(1, id);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}
	
	public ResultSet getSalesInventory() {

		try {
			ps = connection.prepareStatement("select*from salesinventory");
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}
	
	public ResultSet updateDrugStock(int qty, String drugID) {
		try {
			ps = connection.prepareStatement("update drug set qty = ? where drugID = ?");
			ps.setInt(1, qty);
			ps.setString(2, drugID);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return rs;
	}
	
	public ResultSet addSalesInventory(String id, String code, String name, int qty, int price, int totalPrice) {

		try {
			ps = connection.prepareStatement("insert into salesInventory (prescriptionID, drugCode, drugName, qty, price, totalPrice) values (?,?,?,?,?,?)");
			ps.setString(1, id);
			ps.setString(2, code);
			ps.setString(3, name);
			ps.setInt(4, qty);
			ps.setInt(5, price);
			ps.setInt(6, totalPrice);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}
	
	
	
	public ResultSet getDrugByID(String id) {
		try {
			ps = connection.prepareStatement("select*from drug where drugID = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}
	

}
