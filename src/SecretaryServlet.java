import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SecretaryServlet")

public class SecretaryServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		
		String requestType= request.getParameter("requestType");
		
			if (requestType == null) {
				requestType="forlog";
			}
			
			if(requestType.equals("forlog")) {
				
				String un=request.getParameter("username");
				String pw=request.getParameter("password");
			
				try {
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1111");
 
					PreparedStatement ps = c.prepareStatement("select USERNAME ,PASSWORD from secretaries where USERNAME=? and PASSWORD=?");
					ps.setString(1, un);
					ps.setString(2, pw);
 
					ResultSet rs = ps.executeQuery();
 
						while (rs.next()) {
							
							HttpSession session = request.getSession();
							session.setAttribute("username", un);
							response.sendRedirect("welcome.html");
							return;
							}
						
					response.sendRedirect("error.html");
					return;
						
					}
				catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
			}
			else if(requestType.equals("courses")) 
			{
			
	            	PrintWriter out = response.getWriter();
	            	response.setContentType("text/html");
	            	out.println("<!DOCTYPE html><html><head><title>View Courses</title><link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet2.css\"/></head><body>");
	            	
	            		try {
	            			Class.forName("com.mysql.jdbc.Driver");
	            			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1111"); // gets a new connection

	            			PreparedStatement ps = c.prepareStatement("select COURSETITLE,SEMESTER from courses");
	                        ResultSet rs = ps.executeQuery();

	                        out.println("<table>");
	                        out.println("<tr><th colspan=2>Courses</th><tr>");
	                        out.println("<tr><td>Course Title</td><td>Semester</td><tr>");
	                	
	                        	while (rs.next()) {
	                        
	                        		String COURSETITLE = rs.getString(1);
	                        		int SEMESTER = rs.getInt(2);
	                		
	                        		out.println("<tr><td>" + COURSETITLE +"</td><td>"+ SEMESTER +"</td></tr>");
	                        	}
	                        	
	                        out.println("<tr><td><a href=\"http://localhost:8080/Phase_4/welcome.html\">Return</a></td><td><a href=\"http://localhost:8080/Phase_4/Logout.jsp\">Logout</a></td></tr>");	
	        	            out.println("</table>");
	        	            out.println("</html></body>");

	            		}
	            		catch (ClassNotFoundException | SQLException e) {
	            			// TODO Auto-generated catch block
	            			e.printStackTrace();
	            		}
	    }
		else if(requestType.equals("profpercourse"))
		{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            
            out.println("<!DOCTYPE html><html><head><title>View Professor per Course</title><link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet2.css\"/></head><body>");
            
            	try {
            		Class.forName("com.mysql.jdbc.Driver");
            		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1111"); // gets a new connection

            		PreparedStatement ps = c.prepareStatement("select NAME,COURSETITLE,SURNAME from professors INNER JOIN courses WHERE PROFESSORID=COURSES.PROFESSORS_PROFESSORID ");
            		ResultSet rs = ps.executeQuery();
            		
            		out.println("<table>");
            		out.println("<tr><th  colspan=3>Courses and Professors</th><tr>");
            		out.println("<tr><td>Course Title</td><td>Professor Name</td><td>Professor Surname</td><tr>");
            		
            			while (rs.next()) {
            				
            				String COURSETITLE = rs.getString(2);
            				String NAME = rs.getString(1);
            				String SURNAME = rs.getString(3);
            				
            				out.println("<tr><td>"+ COURSETITLE +"</td><td>"+ NAME + "</td><td>" + SURNAME + "</td></tr>");
            			}
            			
            		out.println("<tr><td ><a href=\"http://localhost:8080/Phase_4/welcome.html\">Return</a></td><td><a href=\"http://localhost:8080/Phase_4/Logout.jsp\">Logout</a></td></tr>");	
	        	    out.println("</table>");
            		out.println("</body></html>");

            		}
            	catch (ClassNotFoundException | SQLException e) {
            		// TODO Auto-generated catch block
            		e.printStackTrace();
            		}
		}
		 else if(requestType.equals("proftocourse")) 
	        {
	            PrintWriter out = response.getWriter();
	            response.setContentType("text/html");
	            
	            out.println("<!DOCTYPE html><html><head><title>Assign Professor to Course</title><link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet2.css\"/></head><body>");
	            
	            String ct = request.getParameter("class");
	            String ct2 = request.getParameter("pname");
	            String ct3 = request.getParameter("psurname");
	            
	            	try {
	            			Class.forName("com.mysql.jdbc.Driver");
	            			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1111");

	            			PreparedStatement ps = c.prepareStatement("UPDATE courses INNER JOIN professors SET PROFESSORS_PROFESSORID=professors.PROFESSORID WHERE COURSETITLE='"+ct+"' AND professors.NAME='"+ct2+"' AND professors.SURNAME='"+ct3+"'");
	            			ps.executeUpdate();
	            			
	            			int i=ps.executeUpdate();
	            			
	            				if(i>0)
	            				{
	            					out.println("Professor assigned to course!");
	            					out.println("<a href=\"http://localhost:8080/Phase_4/welcome.html\">Return</a><a href=\"http://localhost:8080/Phase_4/Logout.jsp\">Logout</a>");	
	            					out.println("</html></body>");
	            				}
	            				else
	            				{
	            					response.sendRedirect("error.html");
	            					return;
	            				}
	            		}
	            	catch (ClassNotFoundException | SQLException e) {
	            		// TODO Auto-generated catch block
	            		e.printStackTrace();
	            		}
	            
	        }
	}
		
	

}


