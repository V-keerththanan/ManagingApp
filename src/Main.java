import java.util.Scanner;

public class Main {

    public static void showOption(){
            System.out.println(" Select option ");
            System.out.println("\t Librarian Section");
            System.out.println("1. Add books");
            System.out.println("2. View books");
            System.out.println("3. Issue books");
            System.out.println("4. View Issued books");
            System.out.println("5. Return books");
            System.out.println("6. Log out");


    }
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int option;
        while (true){
            showOption();
            option=scan.nextInt();
            if(option==6){
                break;
            }else{
                switch (option){}
                // have to implement
            }


        }

        System.out.println("Good Bye!");

    }
}
