package mainpackage;

public class Students extends Users{

	int registrationNumber=0;
    static int metritis=0;
    
	public Students(){
		 metritis ++;
	     registrationNumber = registrationNumber + metritis;
	}
	
	public static void gradesCheck(){
		System.out.println("With this method students can check their grades");
	}
}
