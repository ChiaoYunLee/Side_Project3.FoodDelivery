import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Courier {

	private String Courier_ID;
	private String Courier_Name;
	private String Courier_Sex;
	private String Courier_Phone;
	private String Courier_Email;
	private String Courier_Password;
	private String Courier_JoinDate;
	private int Courier_Point;
	private int Courier_Salary;
	private int Courier_Bonus;

	public Courier() {
	}

	public String getInfo() {
		String a = "ID: " + Courier_ID + "\nName: " + Courier_Name + "\nSex: " + Courier_Sex + "\nPhone: "
				+ Courier_Phone + "\nEmail: " + Courier_Email + "\nPW: " + Courier_Password + "\nJoin Date: "
				+ Courier_JoinDate + "\nPoint: " + Courier_Point + "\nSalary: " + Courier_Salary + "\nBonus: "
				+ Courier_Bonus;
		return a;
	}

	public void addCourier(String name, String sex, String phone, String email, String pw) throws SQLException {
		this.Courier_Name = name;
		this.Courier_Sex = sex;
		this.Courier_Phone = phone;
		this.Courier_Email = email;
		this.Courier_Password = pw;
		Date dNow = new Date();
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
		this.Courier_JoinDate = fd.format(dNow);
		Connection conn = null;
		conn = Sql_Connection.connection_mysql();
		try {
			Statement st = conn.createStatement();
			ResultSet resultSet = st.executeQuery("SELECT * FROM COURIER ORDER BY Courier_ID DESC limit 1");
			if (resultSet.next()) {
				String lastID = resultSet.getString("Courier_ID");
				String lastNum = lastID.substring(1, lastID.length());
				int currentNum = Integer.parseInt(lastNum) + 1;
				this.Courier_ID = "C" + currentNum;
			}
			PreparedStatement st2 = conn.prepareStatement("INSERT INTO COURIER VALUES (?,?,?,?,?,?,?,0,25000,0)");
			st2.setString(1, Courier_ID);
			st2.setString(2, Courier_Name);
			st2.setString(3, Courier_Sex);
			st2.setString(4, Courier_Phone);
			st2.setString(5, Courier_Email);
			st2.setString(6, Courier_Password);
			st2.setString(7, Courier_JoinDate);
			st2.executeUpdate();
		} finally {
			conn.close();
		}

	}

	public void logIn(String phone, String pw) throws SQLException {
		Connection conn = null;
		conn = Sql_Connection.connection_mysql();
		try {
			PreparedStatement st = conn.prepareStatement("SELECT * FROM COURIER WHERE Courier_Phone=?");
			st.setString(1, phone);
			ResultSet rs = st.executeQuery();
			if (rs.next() && (rs.getString("Courier_Password")).equals(pw + "")) {
				this.Courier_ID = rs.getString("Courier_ID");
				this.Courier_Name = rs.getString("Courier_Name");
				this.Courier_Sex = rs.getString("Courier_Sex");
				this.Courier_Phone = phone;
				this.Courier_Email = rs.getString("Courier_Email");
				this.Courier_Password = rs.getString("Courier_Password");
				this.Courier_JoinDate = rs.getString("Courier_JoinDate");
				this.Courier_Point = rs.getInt("Courier_Point");
				this.Courier_Salary = rs.getInt("Courier_Salary");
				this.Courier_Bonus = rs.getInt("Courier_Bonus");
			}
		} finally {
			conn.close();
		}
	}
}

