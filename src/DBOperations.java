import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;

public class DBOperations {
    DBConnection mycon = new DBConnection();

    public void addBook(Book b) {

        try {
            String sql = "INSERT INTO book(bid,bname) VALUES (?,?)";

            PreparedStatement statement = mycon.getMyConnection().prepareStatement(sql);
            statement.setString(1, b.getBid());
            statement.setString(2, b.getBname());


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new Book was inserted successfully");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Primary key with that id already exists");
        } catch (SQLException e) {
            System.out.println("An error occured. Maybe user/password is incorrect");
            e.printStackTrace();
        }

    }

    public void viewBook() {
        try {
            String sql = "SELECT * FROM book";

            Statement statement = mycon.getMyConnection().createStatement();
            ResultSet result = statement.executeQuery(sql);

            int count = 0;

            while (result.next()) {
                String bid = result.getString(1);
                String bname = result.getString(2);
                String output = "Book #%d: %s - %s";
                System.out.println((String.format(output, ++count, bid, bname)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void issueBook(Issue objI) {
        String sql = "INSERT INTO issue(issuemid,issuebid,issueDate) VALUES (?,?,?)";
        ArrayList<String> Allmemberid = new ArrayList<>();
        ArrayList<String> Allbookid = new ArrayList<>();
        Allmemberid=getAllmemid();
        Allbookid=getAllbid();
        try {

            if (Allmemberid.contains(objI.getMid()) && Allbookid.contains(objI.getBid())) {
                String sql2="SELECT * FROM issue where stat='not returned' AND issuebid=(?)";
                PreparedStatement statement1 = mycon.getMyConnection().prepareStatement(sql2);
                statement1.setString(1,objI.getBid());
                ResultSet result=statement1.executeQuery();

                if(result.next()) {
                    PreparedStatement statement = mycon.getMyConnection().prepareStatement(sql);

                    statement.setString(1, objI.getMid());
                    statement.setString(2, objI.getBid());
                    statement.setString(3, objI.getIssueDate());
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("A Book was issued successfully");
                    }
                }else{
                    System.out.println("The book is already issued");
                }

            } else {
                System.out.println("The book or member is not there");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Primary key with that id already exists");
        } catch (SQLException e) {
            System.out.println("An error occured. Maybe user/password is incorrect");
            e.printStackTrace();
        }

    }

    public void viewIssuedBook() {
        try {
            String sql = "SELECT issue.issuebid,book.bname FROM issue inner join book on issue.issuebid=book.bid where stat='not returned'";

            Statement statement = mycon.getMyConnection().createStatement();
            ResultSet result = statement.executeQuery(sql);
            int count = 0;
            while (result.next()) {
                String bid = result.getString(1);
                String bname = result.getString(2);
                String output = "Issued book #%d: %s - %s";
                System.out.println((String.format(output, ++count, bid, bname)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<String> getAllmemid() {
        ArrayList<String> Allmembers = new ArrayList<>();
        ResultSet resulmemb = null;
        try {
            resulmemb = mycon.getMyConnection().createStatement().executeQuery("SELECT * FROM memb");
            while (resulmemb.next()) {
                Allmembers.add(resulmemb.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Allmembers;
    }

    public ArrayList<String> getAllbid() {
        ArrayList<String> Allbooks = new ArrayList<>();
        ResultSet resulbook = null;
        try {
            resulbook = mycon.getMyConnection().createStatement().executeQuery("SELECT * FROM book");
            while (resulbook.next()) {
                Allbooks.add(resulbook.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Allbooks;
    }




}
