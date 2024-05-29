package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Employee {
    private int id,position;
    private String name;
    static Connection con = MyConnection.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
    public Employee(int id, int position, String name) {
        this.id = id;
        this.position = position;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static ResultSet selection(String req) {
        try {
            Statement st = con.createStatement();
            return (ResultSet) st.executeQuery(req);
        } catch (SQLException e) {
            return null;
        }
    }

    public static void afficheResultSet(ResultSet rs) {
        try {
            System.out.println(
                    "--------------------------------\n" + "id  | Name    | Position  |\n");
            while (rs.next()) {
                int id = rs.getInt(1);
                String n = rs.getString(2);
                int p  = rs.getInt(3);
                
                
                

                System.out.println(id + "  | " + n + "    | " + p + "  | " );
            }
            System.out.println("--------------------------------\n");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int insertEmployee(int Id, String nom, int P) {
        int a = 0;
        String req1 = "insert into employee values ('" + Id + "','" + nom + "','"
                + P + "')";
        try {
            Statement st = con.createStatement();
            if (st != null) {
                a = st.executeUpdate(req1);
                
                System.out.println(a);
                return a;
            }
        } catch (SQLException e) {
            System.out.println("Erreur" + e.getMessage());
        }
        return a;
    }

    public static int modifierEmployee(int id, String nom, int p) {
        String req = "UPDATE employee SET name = ?, position = ?   WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, nom);
            ps.setInt(2, p);
            ps.setInt(3, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static int supprimerEmployee(int id) {
        String req = "Delete from employee where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
     
}
