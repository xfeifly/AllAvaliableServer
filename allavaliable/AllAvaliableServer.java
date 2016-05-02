package allavaliable;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AllAvaliableServer
 */
@WebServlet("/AllAvaliableServer")
public class AllAvaliableServer extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	private String message;
	private Dbase db;
	private StringTokenizer st;
     

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllAvaliableServer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//response.getOutputStream().println("Hurray !!!!!! This Servlet Works");
		//PrintWriter out = response.getWriter();
        //out.println("Hello Android !!!!");
       
        try {
            int length = request.getContentLength();
            byte[] input = new byte[50];
            ServletInputStream sin = request.getInputStream();
            
            int c, count = 0 ;
            while ((c = sin.read(input, count, input.length-count)) != -1) {
                count +=c;
            }
            sin.close();
           
            String recievedString = new String(input);       
            
            System.out.println("doGet:"+recievedString);
            
            response.setStatus(HttpServletResponse.SC_OK);
            OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
 
            writer.write("okok");
            writer.flush();
            writer.close();

        } catch (IOException e) {
            try{
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(e.getMessage());
                response.getWriter().close();
            } catch (IOException ioe) {
            }
        } 
        

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		db = new Dbase();
		//PrintWriter out = response.getWriter();
        //out.println("Hello Android !!!!");
		try {
            int length = request.getContentLength();
            byte[] input = new byte[length];
            ServletInputStream sin = request.getInputStream();
            
            int c, count = 0 ;
            while ((c = sin.read(input, count, input.length-count)) != -1) {
                count +=c;
            }
            sin.close();
           
            String recievedString = new String(input);  
            System.out.println("The whole received string: "+recievedString);
            System.out.println("After parsing, the first one: "+parseData(recievedString,":;").get(0));
            OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());

            switch(parseData(recievedString,":;").get(0)){
            case "login":
            	System.out.println(parseData(recievedString,":;").size());
            	
            	if(parseData(recievedString,":;").size()<5){
            		System.out.println("name or pass is null!");
            		break;
            	}
            	String loginusername = parseData(recievedString,":;").get(2);  	
            	String loginpassword = parseData(recievedString,":;").get(4);
            	System.out.println("loginname: " + loginusername);
            	System.out.println("loginpassword: " + loginpassword);
            	System.out.println("going to check in the database");
            	
      		    String logintp = db.login(loginusername, loginpassword);
      		    System.out.println(logintp);   
	            response.setStatus(HttpServletResponse.SC_OK);
	            	 
	            writer.write(logintp);
	            writer.flush();
	            writer.close();
	            break;
	            
            case "signup":
            	if(parseData(recievedString,":;").size()<5){
            		System.out.println("name or pass is null!");
            		break;
            	}
            	String signupusername = parseData(recievedString,":;").get(2);  	
            	String signuppassword = parseData(recievedString,":;").get(4);
            	System.out.println("signupname: " + signupusername);
            	System.out.println("signuppassword: " + signuppassword);
            	System.out.println("going to store in the database");
            	
      		    db.register(signupusername, signuppassword);
	            response.setStatus(HttpServletResponse.SC_OK);
	            writer.write("signup success!");
	            writer.flush();
	            writer.close();
	            break;
	            
            case "conferenceroomstatus":
            	String checkcofroomid = parseData(recievedString,":;").get(1);
            	String conferenceRoom;
            	System.out.println(checkcofroomid);
            	response.setStatus(HttpServletResponse.SC_OK);
            	conferenceRoom = db.checkConferenceRoomStatus(checkcofroomid);
            	System.out.println(conferenceRoom);
            	writer.write(conferenceRoom);
            	writer.flush();
            	writer.close();
            	break;
            	
            case "studyroomstatus":
            	String checkstyroomid = parseData(recievedString,":;").get(1);
            	String studyroomstatus;
            	System.out.println("studyroomid: " + checkstyroomid);
            	response.setStatus(HttpServletResponse.SC_OK);
            	studyroomstatus = db.checkStudyRoomStatus(checkstyroomid);
            	writer.write(studyroomstatus);
            	writer.flush();
            	writer.close();
            	break;
            	
            case"bookroom":
            	String bookroomid = parseData(recievedString,":;").get(1);
            	String bookRoomtimeslot = parseData(recievedString,":;").get(2);
            	String username = parseData(recievedString,":;").get(3);
            	Boolean bookcofFlag;
            	System.out.println("bookconfroomid: " + bookroomid);
            	response.setStatus(HttpServletResponse.SC_OK);
            	bookcofFlag = db.bookConferenceRoom(bookroomid, bookRoomtimeslot, username);
            	writer.write(bookcofFlag.toString());
            	writer.flush();
            	writer.close();
            	break;
            	
            case"cancelbookroom":
            	String cancelroomid = parseData(recievedString,":;").get(1);
            	String cancelroomtimeslot = parseData(recievedString,":;").get(2);
            	System.out.println("cancelroomid: " + cancelroomid);
            	response.setStatus(HttpServletResponse.SC_OK);
            	Boolean cancelBookConf;
            	
            	cancelBookConf = db.bookConferenceRoom(cancelroomid, cancelroomtimeslot, "avaliable");
            	writer.write(cancelBookConf.toString());
            	writer.flush();
            	writer.close();
            	break;
            	
            case"bookseat":
            	String bookseatroomid = parseData(recievedString,":;").get(1);
            	String bookseatid = parseData(recievedString,":;").get(2);
            	String bookseattimeslot = parseData(recievedString,":;").get(3);
            	String bookseatusername = parseData(recievedString,":;").get(4);
            	Boolean bookseatFlag;
            	System.out.println("book seat roomid and seatid: " + bookseatroomid+bookseatid);
            	response.setStatus(HttpServletResponse.SC_OK);
	           
            	bookseatFlag = db.bookSeatStudyRoom(bookseatroomid, bookseatid, bookseattimeslot, bookseatusername);
            	writer.write(bookseatFlag.toString());
            	writer.flush();
            	writer.close();
            	break;
            	
            case"cancelbookseat":
            	String cancleseatroomid = parseData(recievedString,":;").get(1);
            	String cancleseatid = parseData(recievedString,":;").get(2);
            	String cancleseatTimeslot = parseData(recievedString,":;").get(3);
            	String cancleseatUsername = parseData(recievedString,":;").get(4);
            	Boolean cancelseatFlag;
            	cancelseatFlag = db.bookSeatStudyRoom(cancleseatroomid, cancleseatid, cancleseatTimeslot, "avaliable");
            	System.out.println("roomid and seatid: " + cancleseatroomid+cancleseatid);
            	response.setStatus(HttpServletResponse.SC_OK);
            	
            	writer.write(cancelseatFlag.toString());
            	writer.flush();
            	writer.close();
            	break;
                      
            }
                     

        } catch (IOException e) {
            try{
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(e.getMessage());
                response.getWriter().close();
            } catch (IOException ioe) {
            }
        }   
    }
	
	protected ArrayList<String> parseData (String input, String delim){
		ArrayList<String>receivedAppdata = new ArrayList<String>();
		input.trim();
		st = new StringTokenizer(input,delim);
		while(st.hasMoreTokens()){
			receivedAppdata.add(st.nextToken().trim());
        }
		
		return receivedAppdata;
	}
		
}


