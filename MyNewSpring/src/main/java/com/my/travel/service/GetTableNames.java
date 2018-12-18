package com.my.travel.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.MetaDataAccessException;

import com.mysql.jdbc.DatabaseMetaData;

public class GetTableNames implements DatabaseMetaDataCallback {


	@Override
	public Object processMetaData(java.sql.DatabaseMetaData dbmd) throws SQLException, MetaDataAccessException {
		  ResultSet rs = dbmd.getTables("%", "travel1", "%", new String[]{"%"});
	        ArrayList l = new ArrayList();
	        while (rs.next()) {
	            l.add(rs.getString(3));
	        }
	        return l;
	}

	

}