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


@WebServlet("/ProfessorServlet")

public class ProfessorServlet extends HttpServlet {
	String professor_username;
	String course,name,surname,grade;
	int i;
	
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
 
				PreparedStatement ps = c.prepareStatement("select USERNAME ,PASSWORD from professors where USERNAME=? and PASSWORD=?");
				ps.setString(1, un);
				ps.setString(2, pw);
 
				ResultSet rs = ps.executeQuery();
 
					while (rs.next()) {
						HttpSession session = request.getSession();
						session.setAttribute("username", professor_username);
						response.sendRedirect("welcome_prof.html");
						professor_username = request.getParameter("username");
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
	         out.println("<!DOCTYPE html><html><head><title>View Graded Courses</title><link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet2.css\"/></head><body>");
	         
	            try {
	            	Class.forName("com.mysql.jdbc.Driver");
	            	Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1111");

	                PreparedStatement ps = c.prepareStatement("select courses.COURSETITLE,course_has_students.GradeCourseStudent,students.Name,students.SURNAME from professors INNER JOIN course_has_students INNER JOIN students INNER JOIN courses WHERE professors.USERNAME='"+professor_username+"' AND PROFESSORID=courses.PROFESSORS_PROFESSORID AND COURSEID=course_has_students.COURSES_COURSEID AND course_has_students.STUDENTS_STUDENTID=students.STUDENTID AND course_has_students.GradeCourseStudent<>'-'");

	            	ResultSet rs = ps.executeQuery();

	            	out.println("<table>");
	            	out.println("<tr><th colspan=3>Grades by Courses</th></tr>");
	            	out.println("<tr><td>Course Title</td><td>Student</td><td>Grade</td></tr>");
	            	
	            		while (rs.next()) {
	            		
	            			String COURSETITLE = rs.getString(1);
	            			String GradeCourseStudent = rs.getString(2);
	            			String Sname = rs.getString(3);
	            			String Ssurname = rs.getString(4);
	            		
	            			out.println("<tr><td>" + COURSETITLE +"</td><td>"+ Sname +" "+ Ssurname +"</td><td>"+ GradeCourseStudent +"</td></tr>");
	            			}
	            		
	            	out.println("<tr><td colspan=2><a href=\"http://localhost:8080/Phase_4/welcome_prof.html\">Return</a></td><td><a href=\"http://localhost:8080/Phase_4/Logout.jsp\">Logout</a></td></tr>");	
	            	out.println("</table>");
	            	out.println("</html></body>");
	            	}
	            catch (ClassNotFoundException | SQLException e) {
	             // TODO Auto-generated catch block
	             e.printStackTrace();
	            	}
	    }
		else if(requestType.equals("coursesnograde")) 
		{
			 PrintWriter out = response.getWriter();
	         response.setContentType("text/html");
	         out.println("<!DOCTYPE html><html><head><title>View Courses with no Grade</title><link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet2.css\"/></head><body>");
	         
	            try {
	            	Class.forName("com.mysql.jdbc.Driver");
	            	Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1111");

	                PreparedStatement ps = c.prepareStatement("select courses.COURSETITLE,course_has_students.GradeCourseStudent,students.Name,students.SURNAME from professors INNER JOIN course_has_students INNER JOIN students INNER JOIN courses WHERE professors.USERNAME='"+professor_username+"' AND PROFESSORID=courses.PROFESSORS_PROFESSORID AND COURSEID=course_has_students.COURSES_COURSEID AND course_has_students.STUDENTS_STUDENTID=students.STUDENTID AND course_has_students.GradeCourseStudent='-'");

	            	ResultSet rs = ps.executeQuery();

	            	out.println("<table>");
	            	out.println("<tr><th colspan=3><h3>Courses with no Grades</h3></th><tr>");
	            	out.println("<tr><td>Course Title</td><td>Student</td><td>Grade</td></tr>");
	            	
	            		while (rs.next()) {
	            			String COURSETITLE = rs.getString(1);
	            			String GradeCourseStudent = rs.getString(2);
	            			String Sname = rs.getString(3);
	            			String Ssurname = rs.getString(4);
	            			
	            			out.println("<tr><td>" + COURSETITLE +"</td><td>"+ Sname + " " + Ssurname +"</td><td>"+ GradeCourseStudent +"</td></tr>");
	            			}
	            		
	            	out.println("<tr><td><a href=\"http://localhost:8080/Phase_4/welcome_prof.html\">Return</a></td><td><a href=\"http://localhost:8080/Phase_4/Logout.jsp\">Logout</a></td></tr>");	
		            out.println("</table>");
	            	out.println("</html></body>");

	            	}
	            catch (ClassNotFoundException | SQLException e) {
	            	// TODO Auto-generated catch block
	            	e.printStackTrace();
	            	}
	    }
		else if(requestType.equals("grading")) {
			{
				PrintWriter out = response.getWriter();
	            response.setContentType("text/html");
	            
	            out.println("<html><body>");
	            out.println("<!DOCTYPE html><html><head><title>View Courses with no Grade</title><link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet2.css\"/></head><body>");
	            
				course = request.getParameter("course");
				name = request.getParameter("name");
				surname = request.getParameter("surname");
				grade = request.getParameter("grade");
				
					try {
						
						Class.forName("com.mysql.jdbc.Driver");
						Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1111");
						
						PreparedStatement ps = c.prepareStatement("UPDATE course_has_students INNER JOIN professors INNER JOIN courses INNER JOIN students SET GradeCourseStudent='"+grade+"' WHERE professors.USERNAME='"+professor_username+"' AND professors.PROFESSORID=professors.PROFESSORID AND courses.COURSETITLE='"+course+"' AND course_has_students.COURSES_COURSEID=courses.COURSEID AND students.NAME='"+name+"' AND students.Surname='"+surname+"' AND course_has_students.STUDENTS_STUDENTID=students.STUDENTID AND course_has_students.GradeCourseStudent='-'");
						
							if((grade.equals("0")) || (grade.equals("1")) || (grade.equals("2")) || (grade.equals("3")) || (grade.equals("4")) || (grade.equals("5")) || (grade.equals("6")) || (grade.equals("7")) || (grade.equals("8")) || (grade.equals("9")) || (grade.equals("10"))) {
								i=ps.executeUpdate();
							}
							if(i>0)
							{
								out.println("Grading Complete!");
								
							}
							else
							{
								out.println("stuck somewhere");
								response.sendRedirect("error.html");
							}
						}
					catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
					out.println("<a href=\"http://localhost:8080/Phase_4/welcome_prof.html\">Return</a></br><a href=\"http://localhost:8080/Phase_4/Logout.jsp\">Logout</a>");
					out.println("</html></body>");
			}
		}
	}
}