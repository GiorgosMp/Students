package mainpackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Secretaries extends Professors{

	public void studentRegister(String username,String name,String surname,String department, int number) throws IOException {
        File f = new File("src/mainpackage/StudentRegister.txt");
        FileWriter writer = new FileWriter(f,true);
        writer.write(username+" "+name+" "+surname+" "+department+" "+number+ System.lineSeparator());
        writer.close();
    }
	
	public void professorRegister(String username,String name,String surname,String department) throws IOException {
        File f = new File("src/mainpackage/ProfessorRegister.txt");
        FileWriter writer = new FileWriter(f,true);
        writer.write(username+" "+name+" "+surname+" "+department+System.lineSeparator());
        writer.close();
    }
	
	public void courseRegister(String name, String department) throws IOException {
        File f = new File("src/mainpackage/courses.txt");
        FileWriter writer = new FileWriter(f,true);
        writer.write(name+" "+department+ System.lineSeparator());
        writer.close();
    }
	
	public void profCourse(String course_name, String department) {
		
	}
}
	

