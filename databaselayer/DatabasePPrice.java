package databaselayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.sql.SQLException;

import modellayer.*;

public class DatabasePPrice implements IDbPPrice {
	
	//Hardcoded for now. TODO: Use database
	public PPrice getCurrentPrice() {
		return new PPrice();
	}
	
	public int getPriceByZoneId(int zoneId) throws DatabaseLayerException {
		int foundPrice = 0;
		
		Calendar calendar = Calendar.getInstance();
		java.sql.Date dateNow = new java.sql.Date(calendar.getTime().getTime());
		
		Connection con = DBConnection.getInstance().getDBcon();
		String baseSelect = "select top 1 price, pZone_id from PPrice ";
		baseSelect += "where pZone_id = " + zoneId + " and starttime < '" + dateNow + "' ";
		baseSelect += "order by starttime desc";
		System.out.println(baseSelect);
		ResultSet rs = null; 
		
		
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rs = stmt.executeQuery(baseSelect);
			
			if (rs.next()) {				
				foundPrice = rs.getInt(1);
			}
			stmt.close();
		} catch (SQLException ex) {
			foundPrice = 0;
			DatabaseLayerException dle = new DatabaseLayerException("Error retrieving data");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} catch (NullPointerException ex) {
			foundPrice = 0;
			DatabaseLayerException dle = new DatabaseLayerException("Null pointer exception - possibly Connection object");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} catch (Exception ex) {
			foundPrice = 0;
			DatabaseLayerException dle = new DatabaseLayerException("Data not retrieved! Technical error");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} finally {
			DBConnection.closeConnection();
		}
				
		return foundPrice;
	}
	

}
