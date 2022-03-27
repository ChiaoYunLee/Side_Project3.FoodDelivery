import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sql_Connection {
	// mysql 連線字串
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/dbms_group5?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	// userid&password
	static final String USER = "root";
	static final String PASS = "iris24228190";
	public static Connection connection_mysql() 
	{
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			System.out.println("mysql Connection Success");
			return conn;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
