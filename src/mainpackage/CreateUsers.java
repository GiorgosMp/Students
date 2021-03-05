package mainpackage;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateUsers {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	    Users newuser = new Users();
        newuser.username = "G1";
        newuser.name = "Giorgos";
        newuser.surname = "Brown";
        newuser.department = "P1";
        
        System.out.println("user created:"+" " + newuser.username+" " +
                newuser.name+" " + newuser.surname+" " + newuser.department+" "
                 +" " + newuser.usersCounter);
        
        Students newstudent = new Students();
        newstudent.username = "N1";
        newstudent.name = "Nick";
        newstudent.surname = "Green";
        newstudent.department = "P2";
        
        System.out.println("student created:"+" " + newstudent.username+" " +
                newstudent.name+" " + newstudent.surname+" " + newstudent.department+" "
                +newstudent.registrationNumber +" " + newstudent.usersCounter);
                
        /*
        System.out.println("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        newuser.username = scanner.nextLine();
        System.out.println("Your username is " + newuser.username);       
        */
        
        Professors newprofessor = new Professors();
        newprofessor.username = "J1";
        newprofessor.name = "John";
        newprofessor.surname = "Smith";
        newprofessor.department = "P1";
        
        System.out.println("professor created:"+" " + newprofessor.username+" " +
                newprofessor.name+" " + newprofessor.surname+" " + newprofessor.department+" "
                 +" " + newprofessor.usersCounter);
        
        Secretaries newsecretary = new Secretaries();
        newsecretary.surname = "M1";
        newsecretary.name = "Mary";
        newsecretary.surname = "Black";
        newsecretary.department = "P3";
        
        System.out.println("secretary created:"+" " + newsecretary.username+" " +
                newsecretary.name+" " + newsecretary.surname+" " + newsecretary.department+" "
                 +" " + newsecretary.usersCounter);
        
        Courses newcourse = new Courses();
        newcourse.name = "mathima1";
        newcourse.department = "pli";
        
        System.out.println("course created:"+" " + newcourse.name+" " + newcourse.professor+" " + newcourse.department);
		
		Students newstudent1 = new Students();
		Scanner scanner = new Scanner(System.in);
				
		do {
	        System.out.println("Enter username: ");
	        newstudent1.username = scanner.nextLine();
	        try {
	            if(newstudent1.username.isEmpty()) {
	                throw new NullPointerException("Username cannot be empty \n");
	            	}
	        	}
	        catch(NullPointerException e) {
	            System.out.println(e.getMessage());
	        	}
	        }while (newstudent1.username.isEmpty());
		
		
		/*
		System.out.println("Enter Name: ");
		fll(newstudent1.name);
		System.out.println("Enter Surname: ");
		fll(newstudent1.surname);
		System.out.println("Enter Department: ");
		fll(newstudent1.department);
		*/
		
		
		do {
	        System.out.println("Enter Name: ");
	        newstudent1.name = scanner.nextLine();
	        try {
	            if(newstudent1.name.isEmpty()) {
	                throw new NullPointerException("Name cannot be Blank \n");
	            	}
	            else if (!newstudent1.name.matches("[a-zA-Z]+")) {
	            	throw new InputMismatchException("Name cannot contain numbers \n");
	            	}
	        	}
	        catch(NullPointerException | InputMismatchException e) {
	            System.out.println(e.getMessage());
	        	} 
	        }while (newstudent1.name.isEmpty() || !newstudent1.name.matches("[a-zA-Z]+"));
		
		do {
	        System.out.println("Enter Surname: ");
	        newstudent1.surname = scanner.nextLine();
	        try {
	            if(newstudent1.surname.isEmpty()) {
	                throw new NullPointerException("Surname cannot be Blank \n");
	            	}
	            else if (!newstudent1.name.matches("[a-zA-Z]+")) {
	            	throw new InputMismatchException("Surname cannot contain numbers \n");
	            	}
	        	}
	        catch(NullPointerException | InputMismatchException e) {
	            System.out.println(e.getMessage());
	        	} 
	        }while (newstudent1.surname.isEmpty() || !newstudent1.surname.matches("[a-zA-Z]+"));
		
		do {
			System.out.println("Enter department: ");
	        newstudent1.department = scanner.nextLine();
	        try {
	            if(newstudent1.department.isEmpty()) {
	                throw new NullPointerException("Department cannot be Blank \n");
	            	}
	            else if (!newstudent1.department.matches("[a-zA-Z]+")) {
	            	throw new InputMismatchException("Department cannot contain numbers \n");
	            	}
	        	}
	        catch(NullPointerException | InputMismatchException e) {
	            System.out.println(e.getMessage());
	            }
	        }while (newstudent1.department.isEmpty() || !newstudent1.department.matches("[a-zA-Z]+"));
       
        /*
         while(true){
        	try {
        	 System.out.println("Enter registrationNumber: ");
        	 newstudent1.registrationNumber = scanner.nextInt();
        	 break;
        }
        	catch(InputMismatchException e) {
        	System.out.println("No letters must be used in the Registration Number \n");
        	scanner.next();       	
        }
        	}
        */
		
        System.out.println("Student created:"+" " + newstudent1.username+" " +
        newstudent1.name+" " + newstudent1.surname+" " + newstudent1.department+" "
        +newstudent1.registrationNumber+" " + newstudent1.usersCounter + "\n");
        scanner.close();
        
        /*
        File file = new File("src/mainpackage/Students.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        String s= null;
        while ((st = br.readLine()) != null)
        System.out.println(st);
        
        s = br.readLine();

        String[] arr = s.split(" ");

        for ( String ss : arr) 
        {

              System.out.println(ss);
        }
        br.close();
        */
		
        File f = new File("src/mainpackage/Students.txt");
        
        Scanner scanner1 = new Scanner(f);

        int k=0;
        while(scanner1.hasNext())
        {
            k = k + 1;
            scanner1.nextLine();
        }
        scanner1.close();
        
        Students[] egStudent  = new Students[k];

        Scanner scanner2 = new Scanner(f);
        int i=0;
        while(scanner2.hasNext()){
            egStudent[i]= new Students();
            String[] tokens = scanner2.nextLine().split(" ");
            egStudent[i].username = tokens[tokens.length -4];
            egStudent[i].name =tokens[tokens.length -3];
            egStudent[i].surname = tokens[tokens.length -2];
            egStudent[i].department = tokens[tokens.length -1];
            System.out.println("student created:"+" " + egStudent[i].username+" " +
                    egStudent[i].name+" " + egStudent[i].surname+" " + egStudent[i].department+" "
                    +egStudent[i].registrationNumber +" " + egStudent[i].usersCounter);
            i++;
        	}
        scanner2.close();
        
        newstudent.gradesCheck();
        
        newprofessor.grading();
        
        newprofessor.profGradeCheck();
        
        newsecretary.studentRegister(newstudent.username, newstudent.name, newstudent.surname, newstudent.department, newstudent.registrationNumber);
        
        newsecretary.studentRegister(newstudent1.username, newstudent1.name, newstudent1.surname, newstudent1.department, newstudent1.registrationNumber);
        
        for (int j=0;j<k;j++) {
        	newsecretary.studentRegister(egStudent[j].username,egStudent[j].name,egStudent[j].surname,egStudent[j].department, egStudent[j].registrationNumber);
        }
        
        newsecretary.professorRegister(newprofessor.username, newprofessor.name, newprofessor.surname, newprofessor.department);
        
        newsecretary.courseRegister(newcourse.name,newcourse.department);
        }
		
}
	
	

	
	
	
	 /*
	 public static void fll(String x) {
		Scanner scanner3 = new Scanner(System.in);
		do {
			x = scanner3.nextLine();
				try {
					if(x.isEmpty()) {
						throw new NullPointerException("Field cannot be Blank\nTry Again:");
						}
					else if (!x.matches("[a-zA-Z]+")) {
						throw new InputMismatchException("Field cannot contain numbers\nTry Again:");
						}
					}
				catch(NullPointerException | InputMismatchException e) {
					System.out.println(e.getMessage());
					}
	    	}while (x.isEmpty() || !x.matches("[a-zA-Z]+"));
		scanner3.close();
		return;}
		*/
	
