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
				  preparedStmt.setString (1, username);
			      preparedStmt.setString (2, password);
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
	
	public String getMail(String username){
		String query = "SELECT email FROM user where username=\""+username+"\"";
		ResultSet rs = null;
		String mail = "";
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
							mail = rs.getString("email");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
							

								  }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return mail;
	}
	
	public String getNumber(String username){
		String query = "SELECT phonenumber FROM user where username=\""+username+"\"";
		ResultSet rs = null;
		String number = "";
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
							number = rs.getString("phonenumber");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
							

								  }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return number;
	}
	
	public String getPassword(String username){
		String query = "SELECT password FROM user where username=\""+username+"\"";
		ResultSet rs = null;
		String password = "";
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
							password = rs.getString("password");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
							

								  }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return password;
	}
	
	public void putMail(String username,String mail){
		String query = "UPDATE user set email=? WHERE username = \""+username+"\"";
		       
		
		   
		
		      try {
		    	  PreparedStatement preparedStmt = conn.prepareStatement(query);
				  preparedStmt.setString (1, mail);
			      // execute the preparedstatement
			      preparedStmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void putNumber(String username,String number){
		String query = "UPDATE user set phonenumber=? WHERE username = \""+username+"\"";
	       
		
		   
		
	      try {
	    	  PreparedStatement preparedStmt = conn.prepareStatement(query);
			  preparedStmt.setString (1, number);
		      // execute the preparedstatement
		      preparedStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean bookConferenceRoom(String roomid, String timeslot, String username){
		String query = "UPDATE conferenceroom set "+ timeslot +"=? WHERE roomid = \""+roomid+"\"";
	       
		
		   
		
	      try {
	    	  PreparedStatement preparedStmt = conn.prepareStatement(query);
			  preparedStmt.setString (1, username);
		      // execute the preparedstatement
		      preparedStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean bookSeatStudyRoom(String roomid, String seatid, String timeslot, String username){
		String query = "UPDATE studyroom set "+ timeslot +"=? WHERE roomid = \""+roomid+"\" AND seatid = \""+seatid+"\"";
	       
		
		   
		
	      try {
	    	  PreparedStatement preparedStmt = conn.prepareStatement(query);
			  preparedStmt.setString (1, username);
		      // execute the preparedstatement
		      preparedStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public String checkConferenceRoomStatus(String roomid){
		String query = "SELECT timeslot1,timeslot2,timeslot3,timeslot4 FROM conferenceroom where roomid=\""+roomid+"\"";
		
	      // create the mysql insert preparedstatement
	ResultSet rs = null;
	String timeslot1 = "";
	String timeslot2 = "";
	String timeslot3 = "";
	String timeslot4 = "";
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
						timeslot1 = rs.getString("timeslot1");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						timeslot2 = rs.getString("timeslot2");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						timeslot3 = rs.getString("timeslot3");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						timeslot4 = rs.getString("timeslot4");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					

				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return roomid + ":timeslot1:" + timeslot1 + ";timeslot2:" + timeslot2 + ";timeslot3:" + timeslot3 +";timeslot4:" + timeslot4;
	}
	
	public String checkStudyRoomStatus(String roomid){
		String query = "SELECT seatid,timeslot1,timeslot2,timeslot3,timeslot4 FROM studyroom where roomid=\""+roomid+"\"";
		
	      // create the mysql insert preparedstatement
	
	ResultSet rs = null;
	String seatid = "";
	String timeslot1 = "";
	String timeslot2 = "";
	String timeslot3 = "";
	String timeslot4 = "";
	String response = roomid + ":";
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
						seatid= rs.getString("seatid");
					} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				
					try {
						timeslot1 = rs.getString("timeslot1");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						timeslot2 = rs.getString("timeslot2");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						timeslot3 = rs.getString("timeslot3");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						timeslot4 = rs.getString("timeslot4");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
						response = response + seatid +":timeslot1:"+timeslot1 +";timeslot2:" + timeslot2 + ";timeslot3:" + timeslot3 + ":timeslot4:" + timeslot4 + ";\n";
					
					

				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return response;
	}
	
	

}
