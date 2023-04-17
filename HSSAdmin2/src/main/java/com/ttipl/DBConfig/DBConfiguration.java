package com.ttipl.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfiguration {
	
	public static Connection Initialize() throws ClassNotFoundException, SQLException {
		Connection con =null;
		
//		String dbDriver = "com.mysql.jdbc.Driver";
//		String dbURL="jdbc:mysql://localhost:3306/hssc_groupc_online?characterEncoding=utf8";
//		 
//		String dbUsername="root";
//		String dbPassword="root";
		
//		String dbDriver = "com.mysql.jdbc.Driver";
//		String dbURL= 
//		"jdbc:mysql://ssb.cm0bksyl0mvc.ap-south-1.rds.amazonaws.com:3390/hssc_groupc_online?characterEncoding=utf8";
//		
//		 
//		String dbUsername="admin";
//		String dbPassword="Ttil-2020";
		
//		

		String dbDriver = "com.mysql.jdbc.Driver";
		String dbURL= 
		"jdbc:mysql://10.88.234.55:3306/hssc_groupc_online2023?characterEncoding=utf8";	 
		String dbUsername="root";
		String dbPassword="admin@hssc123";

		Class.forName(dbDriver);
        con = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
		
		return con;
		
	}

}
