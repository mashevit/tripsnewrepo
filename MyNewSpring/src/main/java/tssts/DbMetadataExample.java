/*package tssts;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbMetadataExample {
    private static final String URL = "jdbc:mysql://localhost/kodejava";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws SQLException {
    	try{
    	    Class.forName("com.mysql.jdbc.Driver");
    	} catch (ClassNotFoundException ex){
    	    System.out.println("Error found " + ex);
    	    ex.printStackTrace();
    	}

    	// Here is where the error pops up
    	Connection connection1 =DriverManager.getConnection("jdbc:mysql//localhost:3306/project3", "root", "root");
    

        try (Connection connection =
                 DriverManager.getConnection("jdbc:mysql//localhost:3306/travel1", "root", "qwqw")) {

            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet resultSet =
                metadata.getColumns(null, null, "Trip", null);

            while (resultSet.next()) {
                String name = resultSet.getString("COLUMN_NAME");
                String type = resultSet.getString("TYPE_NAME");
                int size = resultSet.getInt("COLUMN_SIZE");

                System.out.println("Column name: [" + name + "]; " +
                    "type: [" + type + "]; size: [" + size + "]");
            }
            
            
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            while (rs.next()) {
              System.out.println(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
	*/

/*

import java.sql.*;
import java.io.*;
import java.util.*;

public class test1 {

    public static void main(String[] argv) throws Exception {
        try {
            Connection con = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/old","user","pass");
            Connection con1 = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/new","user","pass");

            String sql = "INSERT INTO users("+ "name,"+ "active,"+ "login,"+ "password)"+ "VALUES(?,?,?,?)";

            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            PreparedStatement pstmt = con1.prepareStatement(sql);

            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                String nm = rs.getString(2);
                Boolean ac = rs.getBoolean(3);
                String log = rs.getString(4);
                String pass = rs.getString(5);

                pstmt.setString(1, nm);
                pstmt.setBoolean(2, ac);
                pstmt.setString(3, log);
                pstmt.setString(4, pass);

                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("could not get JDBC connection: " +e);
        } finally {
            con.close();
            con1.close();
        }
    }

}*/