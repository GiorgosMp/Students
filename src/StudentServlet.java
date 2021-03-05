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

@WebServlet("/StudentServlet")

public class StudentServlet extends HttpServlet {
	
	String student_username;
	
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
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1111"); // gets a new connection
 
					PreparedStatement ps = c.prepareStatement("select USERNAME ,PASSWORD from students where USERNAME=? and PASSWORD=?");
					ps.setString(1, un);
					ps.setString(2, pw);
 
					ResultSet rs = ps.executeQuery();
 
						while (rs.next()) {
						
							HttpSession session = request.getSession();
							session.setAttribute("username", student_username);
							response.sendRedirect("welcome_stud.html");
							student_username = request.getParameter("username");
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
		else if(requestType.equals("score_courses")) 
		{
	            PrintWriter out = response.getWriter();
	            response.setContentType("text/html");
	            out.println("<!DOCTYPE html><html><head><title>View Score per Course</title><link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet2.css\"/></head><body>");
	            
	            	try {
	            		Class.forName("com.mysql.jdbc.Driver");
	            		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1111");

	            		PreparedStatement ps = c.prepareStatement("select COURSETITLE,GradeCourseStudent from courses INNER JOIN course_has_students INNER JOIN students WHERE students.username='"+student_username+"' AND course_has_students.STUDENTS_STUDENTID=students.STUDENTID AND COURSEID=course_has_students.COURSES_COURSEID");
	            		ResultSet rs = ps.executeQuery();

	            		out.println("<table>");
	            		out.println("<tr><th colspan=2>Courses</th><tr>");
	            		
	            			while (rs.next()) {
	                    
	            				String COURSETITLE = rs.getString(1);
	            				int GradeCourseStudent = rs.getInt(2);
	                    
	            				out.println("<tr><td>" + COURSETITLE +"</td><td>"+ GradeCourseStudent + "</td></tr>");
	            			}
	            			
	            		out.println("<tr><td ><a href=\"http://localhost:8080/Phase_4/welcome_stud.html\">Return</a></td><td><a href=\"http://localhost:8080/Phase_4/Logout.jsp\">Logout</a></td></tr>");	
	        	        out.println("</table>");
	        	        out.println("</html></body>");

	            		}
	            	catch (ClassNotFoundException | SQLException e) {
	            		// TODO Auto-generated catch block
	            		e.printStackTrace();
	            	}
	    }
		else if(requestType.equals("score_semester"))
		{
			PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<!DOCTYPE html><html><head><title>View Score per Semester</title><link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet2.css\"/></head><body>");
            	
            	try {
            		Class.forName("com.mysql.jdbc.Driver");
            		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1111"); 

            		PreparedStatement ps = c.prepareStatement("select COURSETITLE,GradeCourseStudent,SEMESTER from courses INNER JOIN course_has_students INNER JOIN students WHERE students.username='"+student_username+"' AND course_has_students.STUDENTS_STUDENTID=students.STUDENTID AND COURSEID=course_has_students.COURSES_COURSEID");
            		ResultSet rs = ps.executeQuery();
            	
            		out.println("<table>");
            		out.println("<tr><th colspan=2>Courses By Semester</th><tr>");
            		
            			while (rs.next()) {
            		
            				String COURSETITLE = rs.getString(1);
            				String GradeCourseStudent = rs.getString(2);
            				int SEMESTER = rs.getInt(3);
            				out.println("<tr><th colspan=2>"+"SEMESTER "+SEMESTER+"</th><tr>");
                    
            					for(int i=1; i<9; i++){
            							if(SEMESTER==i) {
            									out.println("<tr><td>" + COURSETITLE +"</td><td>"+ GradeCourseStudent + "</td></tr>");
            								}
            					}
            			}
            			
            	out.println("<tr><td ><a href=\"http://localhost:8080/Phase_4/welcome_stud.html\">Return</a></td><td><a href=\"http://localhost:8080/Phase_4/Logout.jsp\">Logout</a></td></tr>");	
	        	out.println("</table>");
	        	out.println("</html></body>");

            		}
            	catch (ClassNotFoundException | SQLException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            	}
		}
		else if(requestType.equals("score")) 
        {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<!DOCTYPE html><html><head><title>Total Score</title><link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet2.css\"/></head><body>");
            out.println("<table>");
    		out.println("<tr><th>Total Average Score</th><tr>");
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1111");

                PreparedStatement ps = c.prepareStatement("SELECT CONVERT(GradeCourseStudent,BINARY),AVG(GradeCourseStudent)SEMESTER from courses INNER JOIN course_has_students INNER JOIN students WHERE students.username='"+student_username+"' AND course_has_students.STUDENTS_STUDENTID=students.STUDENTID AND GradeCourseStudent<>'-'");
                ResultSet rs = ps.executeQuery();
                
                out.println("<table>");
        		out.println("<tr><th>Total Average Score</th><tr>");
                 	
                	while (rs.next()) {
                	 	float AVG = rs.getFloat(2);
                        out.println("<tr><td>"+ AVG + "</td></tr>");
                    }
                    
                out.println("<tr><td ><a href=\"http://localhost:8080/Phase_4/welcome_stud.html\">Return</a></td><td><a href=\"http://localhost:8080/Phase_4/Logout.jsp\">Logout</a></td></tr>");	
    	        out.println("</table>");
    	        out.println("</html></body>");

                }
            catch (ClassNotFoundException | SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
	
	}

}
