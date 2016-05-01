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
            System.out.println("The whole received string111: "+recievedString);
            System.out.println("After parsing, the first one111: "+parseData(recievedString,":;").get(0));
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
            	db = new Dbase();
      		    String logintp = db.login(loginusername, loginpassword);
      		    System.out.println(logintp);   
	            response.setStatus(HttpServletResponse.SC_OK);
	            //OutputStreamWriter loginwriter = new OutputStreamWriter(response.getOutputStream());
	 
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
            	System.out.println("signupname111: " + signupusername);
            	System.out.println("signuppassword111: " + signuppassword);
            	System.out.println("going to store in the database");
            	db = new Dbase();
            	
      		    db.register(signupusername, signuppassword);
	            response.setStatus(HttpServletResponse.SC_OK);
	           // OutputStreamWriter signupwriter = new OutputStreamWriter(response.getOutputStream());
	            writer.write("signup success!");
	            writer.flush();
	            writer.close();
	            break;
            case "conferenceroomstatus":
            	String checkcofroomid = parseData(recievedString,":;").get(1);
            	System.out.println("roomid: " + checkcofroomid);
            	response.setStatus(HttpServletResponse.SC_OK);
	            //OutputStreamWriter checkconferenceroomwriter = new OutputStreamWriter(response.getOutputStream());
            	writer.write("129a:timeslot1:avaliable;timeslot2:avaliable;timeslot3:Tom;timeslot4:avaliable");
            	writer.flush();
            	writer.close();
            case "studyroomstatus":
            	String checkstyroomid = parseData(recievedString,":;").get(1);
            	System.out.println("roomid: " + checkstyroomid);
            	response.setStatus(HttpServletResponse.SC_OK);
	            //OutputStreamWriter checkstudyroomwriter = new OutputStreamWriter(response.getOutputStream());
            	writer.write("129a:"
	            		+ "seat1:timeslot1:avaliable;timeslot2:avaliable;timeslot3:Tom;timeslot4:avaliable;"
	            		+ "seat2:timeslot1:avaliable;timeslot2:avaliable;timeslot3:Tom;timeslot4:avaliable;");
            	writer.flush();
            	writer.close();
            case"bookroom":
            	String bookroomid = parseData(recievedString,":;").get(1);
            	System.out.println("roomid: " + bookroomid);
            	response.setStatus(HttpServletResponse.SC_OK);
	           // OutputStreamWriter bookroomwriter = new OutputStreamWriter(response.getOutputStream());
            	writer.write("ok");
            	writer.flush();
            	writer.close();
            case"cancelbookroom":
            	String cancelroomid = parseData(recievedString,":;").get(1);
            	System.out.println("roomid: " + cancelroomid);
            	response.setStatus(HttpServletResponse.SC_OK);
	           // OutputStreamWriter bookroomwriter = new OutputStreamWriter(response.getOutputStream());
            	writer.write("ok");
            	writer.flush();
            	writer.close();
            case"bookseat":
            	String bookseatroomid = parseData(recievedString,":;").get(1);
            	String bookseatid = parseData(recievedString,":;").get(2);
            	System.out.println("roomid and seatid: " + bookseatroomid+bookseatid);
            	response.setStatus(HttpServletResponse.SC_OK);
	           // OutputStreamWriter bookroomwriter = new OutputStreamWriter(response.getOutputStream());
            	writer.write("ok");
            	writer.flush();
            	writer.close();
            case"cancelbookseat":
            	String cancleseatroomid = parseData(recievedString,":;").get(1);
            	String cancleseatid = parseData(recievedString,":;").get(2);
            	System.out.println("roomid and seatid: " + cancleseatroomid+cancleseatid);
            	response.setStatus(HttpServletResponse.SC_OK);
	           // OutputStreamWriter bookroomwriter = new OutputStreamWriter(response.getOutputStream());
            	writer.write("ok");
            	writer.flush();
            	writer.close();
                      
            }
            
//            if(parseData(recievedString,":;").get(0).equals("login")){
//            	String username = parseData(recievedString,":;").get(2);  	
//            	String password = parseData(recievedString,":;").get(4);
//            	System.out.println("name: " + username);
//            	System.out.println("password: " + password);
//            	System.out.println("going to check in the database");
//            	db = new Dbase();
//      		    String tp = db.login(username, password);
//      		    System.out.println(tp);   
//	            response.setStatus(HttpServletResponse.SC_OK);
//	            OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
//	 
//	            writer.write(tp);
//	            writer.flush();
//	            writer.close();
//            }
            

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
			receivedAppdata.add(st.nextToken());
        }
		
		return receivedAppdata;
	}
		
}


