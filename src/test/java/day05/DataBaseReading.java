package day05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
 
public class DataBaseReading {

	public static void main(String[] args) throws Exception {
		String sServerDetails = "jdbc:mysql://100.200.300:8888/emp";// Connection string
		String sUserName = "root";
		String sPassword = "admin123";
		String sQuery = "select * from student where rollNum = '1020';";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(sServerDetails,sUserName,sPassword);
		Statement state = con.createStatement();
		ResultSet result = state.executeQuery(sQuery);
		while(result.next()) {
			String username = result.getString("userName");
			String age = result.getString(2);
		}
		con.close();
	}

}



