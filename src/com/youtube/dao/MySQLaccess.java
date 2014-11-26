package com.youtube.dao;

import javax.naming.*;
import javax.sql.*;

public class MySQLaccess {

	private static DataSource MySQLep3 = null;
	private static Context context = null;
	
	public static DataSource MySQLep3Conn() throws Exception {
		
		if (MySQLep3 != null){
			return MySQLep3;
		}
		try{
			if (context == null){
				context = new InitialContext();
			}
			MySQLep3 = (DataSource) context.lookup("java:comp/env/jdbc/mkyongdb");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return MySQLep3;
	}
}
