import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static DBOperations dbop = new DBOperations();

    public static void showOption() {
        System.out.println(" Select option ");
        System.out.println("\t......Librarian Section......");
        System.out.println("1.Add books");
        System.out.println("2.View books");
        System.out.println("3.Issue books");
        System.out.println("4.View Issued books");
        System.out.println("5.Return books");
        System.out.println("6. Log out");


    }

    public static void main(String[] args) {
        boolean isRunning = true;
        int option;
        while (isRunning) {
            showOption();
            option = sc.nextInt();
            Main m = new Main();
            switch (option) {
                case 1:
                    m.add();
                    break;
                case 2:
                    m.view();
                    break;
                case 3:
                    m.issue();
                    break;
                case 4:
                    dbop.viewIssuedBook();
                    break;
                case 5:
                    // have to do
                case 6:
                    System.out.println("Have a nice day!");
                    isRunning = false;
                default:
                    System.out.println(" Invalid option , select Again!");
            }


        }

        System.out.println("Good Bye!");

    }

    public void add() {
        Book book=new Book();
        System.out.println("Enter Book id:");
        book.setBid(sc.next());
        System.out.println("Enter Book name: ");
        book.setBname(sc.nextLine());
        dbop.addBook(book);

    }

    public void view() {
        System.out.println("Books list");
        dbop.viewBook();
    }

    public void issue(){
        Issue issue=new Issue();
        System.out.println("Enter Member id:");
       issue.setMid(sc.next());
        System.out.println("Enter Book id: ");
        issue.setBid(sc.next());
        System.out.println("Enter Issue date: ");
        issue.setIssueDate(sc.next());
        dbop.issueBook(issue);
    }
}
