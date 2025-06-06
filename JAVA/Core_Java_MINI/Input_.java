import java.util.Scanner;
public class Input_{
   
    public static void main(String[] args) {
       // System.out.println("Hello, World!");

       Scanner sc= new Scanner(System.in);
       // ask ur first name 
       System.out.println("Enter ur first_name");
       String f_name = sc.nextLine();
       // ask ur first name 
       System.out.println("Enter ur last_name");
       String l_name = sc.nextLine();

       //display the full name 
       String Fullname = f_name+" "+l_name;
       System.out.println("Full Name is ..." + Fullname);
       sc.close();
    }
    
}
