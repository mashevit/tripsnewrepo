package tssts;

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
	