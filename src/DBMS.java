import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBMS {
    Connection con;
    DBMS(){
        try {
            con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ projectManagement","root","root@1999");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
