package classes;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {


    

    public static Connection getConnection(String url,String username,String password){

       
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("Driver charged...");
        } catch (ClassNotFoundException e) {
            System.out.println("erreur Driver "+ e.getMessage());
        }
        

        Connection con =null;
        Statement st = null;
        try {
            con = DriverManager.getConnection(url,username,password);
            st=con.createStatement();
            //System.out.println("connected...");
        } catch (SQLException e) {
            System.out.println("erreur connexion"+e.getMessage());
        }
        return con;

    }
}
