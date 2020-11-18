package currencyconverter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class connectiondatabase {
	public Connection getconnection() {
		// TODO Auto-generated method stub
		try {
			java.sql.Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/currency_converter", "root","");
			System.out.println("Connected to database");
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
}
