package com.youtube.dao;

import javax.naming.*;
import javax.sql.*;

import java.sql.Connection;

/**
 * This class returns the Oracle database connect object from
 * a CentOS Oracle Express Virtual Machine
 * 
 * The method and variable in this class are static to save resources
 * You only need one instance of this class running.
 * 
 * This was explained in episode 3 of the Java Rest Tutorial Series on YouTube
 * 
 * We can some significant changes to this episode 5.
 * 
 * @author 308tube
 *
 */

public class MySQLaccess {

	private static DataSource MySQLep3 = null;
	private static Context context = null;
	
	public static DataSource MySQLep3Conn() throws Exception {
		/**
		 * This is a public method that will return the 308tube database connection.
		 * 
		 * On Episode 5, I discussed that this method should not be private instead of public.
		 * This will make sure all sql/database relate code be place in the dao package.
		 * I am not doing this because I do not want to break the previous code... since this
		 * is just a tutorial project.
		 * 
		 * Pre-episode 6, updated this to a private scope, as it should be. That means, V1_inventory
		 * and V1_status methods needs to be updated.
		 * 
		 * @return Database object
		 * @throws Exception
		 */
		
		if (MySQLep3 != null){
			return MySQLep3;
		}
		try{
			/**
			 * This only needs to run one time to get the database object.
			 * context is used to lookup the database object in weblogic
			 * Oracle308tube will hold the database object
			 */
			
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

	protected static Connection oraclePcPartsConnection() {
		/**
		 * This method will return the connection to the Oracle 308tube schema
		 * Note that the scope is protected which means only java class in the
		 * dao package can use this method.
		 * 
		 * @return Connection to 308tube Oracle database.
		 */
		Connection conn = null;
		try {
			conn = MySQLep3Conn().getConnection();
			return conn;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
