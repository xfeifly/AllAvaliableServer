package allavaliable;

import java.io.IOException;
import java.io.OutputStreamWriter;
<<<<<<< HEAD
import java.sql.*;
=======
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
>>>>>>> 2b25962005bd624581752c17077d368f5d9057a3

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

	String message;
	Dbase db;
	public void init() throws ServletException
	  {
	      // Do required initialization
		  db = new Dbase();
		  
	  }
       

	private StringTokenizer st = new StringTokenizer("this is a test");
     

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

		String tp = db.login("testtest", "test");
		response.getOutputStream().println(tp);
		

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
		//doGet(request, response);
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
            System.out.println("doPost: "+parseData(recievedString,":;").get(0));
            
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


