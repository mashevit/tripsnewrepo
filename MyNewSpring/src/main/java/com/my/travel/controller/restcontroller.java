package com.my.travel.controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.travel.components.ToAndApp;
import com.my.travel.dao.CityRepository;
import com.my.travel.dao.PicRepository;
import com.my.travel.dao.TripSightseeingRepository;
import com.my.travel.model2.Pic;
import com.my.travel.model2.TripSightseeing;
import com.my.travel.service.GetTableNames;
import com.mysql.jdbc.Statement;

//import main.java.com.my.model.Dish;
@CrossOrigin
@RestController
@RequestMapping(value = "/rest")
public class restcontroller {
 //"jdbc:mysql://localhost:3306/travel1?useLegacyDatetimeCode=false&serverTimezone=Asia/Jerusalem&useSSL=false";
	private static final String URL ="jdbc:mysql://mytrips81.coizdbobchof.us-east-1.rds.amazonaws.com:3306/travel1?useLegacyDatetimeCode=false&serverTimezone=Asia/Jerusalem&useSSL=false";
	private static final String USERNAME = "ilia";//root
	private static final String PASSWORD = "rds8811b";//qwqw

	
	/*
	 * @Autowired // Which is auto-generated by Spring, we will use it to handle the
	 * data private TripRepository tripRepository;
	 * 
	 * @Autowired // Which is auto-generated by Spring, we will use it to handle the
	 * data private SightseeingRepository sightseeingRepository;
	 * 
	 * @Autowired // Which is auto-generated by Spring, we will use it to handle the
	 * data private CityRepository cityRepository;
	 */
	@Autowired // Which is auto-generated by Spring, we will use it to handle the data
	private TripSightseeingRepository tripSightseeingRepository;

	@Autowired // Which is auto-generated by Spring, we will use it to handle the data
	private PicRepository picRepository;

	@Autowired // Which is auto-generated by Spring, we will use it to handle the data
	private CityRepository cityRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/tststst")
	public List<String> getCustomResult() {
		GetTableNames getTableNames = new GetTableNames();
		try {
			Object o = JdbcUtils.extractDatabaseMetaData(jdbcTemplate.getDataSource(), getTableNames);
			System.out.println(o);
		} catch (MetaDataAccessException e) {
			System.out.println(e);
		}
		return Arrays.asList("One", "Two", "Three");
	}

	public class CustomResultRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			List<String> customResult = new ArrayList<String>();
			customResult.add(rs.getString(rowNum));

			return customResult;
		}
	}

	@GetMapping("/trippic/{id}")
	public List<ToAndApp> retrievefortrip(@PathVariable int id) {

		System.out.println("  trippic 	");
		List<TripSightseeing> tripSightseeings = tripSightseeingRepository.findAllByTripCityIdcities(id);
		String globalc = cityRepository.findById(id).get().getCityName();
		List<ToAndApp> tmp = new ArrayList<ToAndApp>();
		tripSightseeings.forEach(a -> {
			List<Pic> pictmp = picRepository.findAllBytripSightseeingIdtripSightseeingIn(a.getIdtripSightseeing());
			ToAndApp andApp = new ToAndApp();
			andApp.setSightname(a.getSightseeing().getSightseeingsname());
			andApp.setAddr(pictmp.stream().map(bp -> bp.getPicsAddr()).collect(Collectors.toList()));
			// pictmp.stream().forEach(ab->{andApp.setImgaddr(ab.getPicsAddr());});

			andApp.setImgaddr("www://");
			andApp.setTrip(globalc);
			andApp.setSight(a.getSightseeing().getSightseeingsname());
			tmp.add(andApp);

		});

		return tmp;

	}

	@GetMapping("/tststst11")
	public List<String> getres() throws SQLException {

		List<String> ans = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Error found " + ex);
			ex.printStackTrace();
		}

		// Here is where the error pops up
		Connection connection1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {

			DatabaseMetaData md = connection.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			while (rs.next()) {
				String tbl = rs.getString(3);
				ans.add(tbl);

				DatabaseMetaData metadata = connection.getMetaData();
				ResultSet resultSet = metadata.getColumns(null, null, tbl, null);

				while (resultSet.next()) {
					String name = resultSet.getString("COLUMN_NAME");
					String type = resultSet.getString("TYPE_NAME");
					int size = resultSet.getInt("COLUMN_SIZE");
					String tmp = "Column name: [" + name + "]; " + "type: [" + type + "]; size: [" + size + "]";
					ans.add(tmp);
					/*
					 * System.out.println("Column name: [" + name + "]; " + "type: [" + type +
					 * "]; size: [" + size + "]");
					 */
				}

				ans.add("       ");
				// System.out.println(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ans;
	}

/*	@GetMapping("/tbll/{id}")
	public List<String> getAllStudents(@PathVariable String id) {
		// List<String> students = new ArrayList<String>();
		List<String> ans2=new ArrayList<String>();
		List<List<String>> ls2d = new ArrayList<List<String>>();

		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			DatabaseMetaData metadata = conn.getMetaData();
			ResultSet resultSet = metadata.getColumns(null, null, id, null);

			// ans.add(" ");

			//
	

				List<String> student = new ArrayList<String>();

				while (resultSet.next()) {
					String name = resultSet.getString("COLUMN_NAME");
					String type = resultSet.getString("TYPE_NAME");
					int size = resultSet.getInt("COLUMN_SIZE");
					String tmp = "Column name: [" + name + "]; " + "type: [" + type + "]; size: [" + size + "]";
					// ans.add(tmp);
					System.out.println("Column name: [" + name + "]; " + "type: [" + type + "]; size: [" + size + "]");
					
					
					java.sql.Statement statement = conn.createStatement();

					String query =  statement.executeQuery(  "select * from travel1.trip";// );
					PreparedStatement preparedStatement = conn.prepareStatement(query);
			//		preparedStatement.setString(1, id);
					ResultSet rs = preparedStatement.executeQuery();
					preparedStatement.close();
					//ans2.add(resultSet1.toString());/*
					while (resultSet1.next()) {
					student.add(resultSet1.getString(name));
					// student.setFirstName( resultSet1.getString( "f_name" ) );
					// student.setLastName( resultSet1.getString( "l_name" ) );

					// student.add(student);
				}
				//	ls2d.add(student);

					
				//	List allRows = new ArrayList();
				    while(rs.next()){
				    //    String[] currentRow = new String[3];
				        for(int i = 1;i<=4;i++){
				            ans2.add(rs.getObject(i)+"  ");
				        }
				      //  rows.add(row);
				    }
				    

					//resultSet1.close();
					rs.close();

				}
				resultSet.close();


				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
	
		
		return ans2;
	}*/
	
	
	@GetMapping("/tbll1/{id}")
	public List<String> getAllStudents1(@PathVariable String id) {
		List<String> students = new ArrayList<String>();
        try {
        	
        	Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            java.sql.Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery( "select * from travel1."+id+"");
            while( resultSet.next() ) {
               // Student student = new Student();
            //	String Student="  ";
                students.add( "1 = "+(String) resultSet.getObject( 1 ).toString() );
                
                students.add("2 = "+ (String) resultSet.getObject( 2).toString() );
                students.add( "3 = "+(String) resultSet.getObject(3 ).toString() );

               // student.setFirstName( resultSet.getString( "f_name" ) );
               // student.setLastName( resultSet.getString( "l_name" ) );
                
              //  students.add(student);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return students;
	}

	
	@GetMapping("/tbll12/{from}/{to}")
	public List<String> getAllStudents122(@PathVariable String from,@PathVariable String to) {
		List<String> students = new ArrayList<String>();
        try {
        	
        	Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //java.sql.Statement statement = conn.createStatement();
           // ResultSet resultSet = statement.executeQuery( "INSERT INTO travel1.trip SELECT * FROM travel1.TRIP");
            
            
            java.sql.Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO travel1.trip SELECT * FROM travel1.TRIP");
            st.close();

            
            
            /*   while( resultSet.next() ) {
               // Student student = new Student();
            //	String Student="  ";
                students.add( "1 = "+(String) resultSet.getObject( 1 ).toString() );
                
                students.add("2 = "+ (String) resultSet.getObject( 2).toString() );
                students.add( "3 = "+(String) resultSet.getObject(3 ).toString() );

               // student.setFirstName( resultSet.getString( "f_name" ) );
               // student.setLastName( resultSet.getString( "l_name" ) );
                
              //  students.add(student);
            }
            resultSet.close();
            statement.close();
       */ } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return students;
	}	
	

	/*
	 * @GetMapping("/tst") public List<Object> get3pics() { return
	 * picRepository.findFrontPics(PageRequest.of(0, 3, Sort.by(...))); }
	 */
}
