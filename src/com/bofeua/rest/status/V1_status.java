package com.bofeua.rest.status;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.sql.*;

import com.bofeua.dao.*;


@Path("/v1/status")
public class V1_status {

	private static final String api_version= "00.01.00";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle(){
		return "<p>Java Web Service</p>";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion(){
		return "<p>Version:</p>" + api_version;
	}

	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception{
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		
		try{
			conn = MySQLaccess.MySQLep3Conn().getConnection();
			query = conn.prepareStatement("select * from mkyongdb.PC_PARTS");
			ResultSet rs = query.executeQuery();
			
			while (rs.next()){
				myString = rs.getString(2);
			}
			
			query.close();
			
			returnString = "<p>Level Values: " + myString + "</p>";
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			if (conn != null) conn.close();
		}
		
		return returnString;
	}
}

