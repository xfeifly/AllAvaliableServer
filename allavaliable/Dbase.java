package allavaliable;

import java.sql.*;

public class Dbase {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/all_available";
    static final String USER = "root";
	static final String PASS = "root";
	Connection conn;
	
	public Dbase(){
		conn = DbCreate(JDBC_DRIVER, DB_URL, USER, PASS);
	}
	
	public void register(String username,String password){
		String query = " insert into user (username, password)"
		        + " values (?, ?)";
		 
		      // create the mysql insert preparedstatement
		
		      try {
		    	  PreparedStatement preparedStmt = conn.prepareStatement(query);
				  preparedStmt.setString (1, "testtest");
			      preparedStmt.setString (2, "test");
			      //preparedStmt.setDouble (3, a.getBaseprice());
			 
			      // execute the preparedstatement
			      preparedStmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public Connection DbCreate(String JDBC_DRIVER,String DB_URL,String USER, String PASS){
		
		 Connection conn = null;
		 // Statement stmt = null;
		   
		      //STEP 2: Register JDBC driver
		      try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      try {
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	return conn;	
	}
	
	public String login(String username,String password){
		String query = "SELECT username,password FROM user where username=\""+username+"\"";
		 
		      // create the mysql insert preparedstatement
		ResultSet rs = null;
		String pass = "";
		String uname = "";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		      
		      try {
				while (rs.next()) {

						
						try {
							pass = rs.getString("password");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						try {
							uname = rs.getString("username");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						

					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      if(uname.contentEquals(username)&&pass.contentEquals(password)){
		    	  return "ptrue";
		      }else if(uname.contentEquals(username)&&(!pass.contentEquals(password))){
		    	  return "pfalse";
		      }else if((!uname.contentEquals(username))){
		    	  return "ufalse";
		      }
		return "ERROR";
	}
	
	

}
