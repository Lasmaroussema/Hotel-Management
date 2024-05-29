package classes;

import java.sql.*;

public class Room {
    private int number;
    private String type, price;
    private boolean available;
    static Connection con = MyConnection.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
    public Room(int number, String type, String price, boolean available) {
        this.number = number;
        this.type = type;
        this.price = price;
        this.available = available;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public static ResultSet selection(String req) {
        try {
            Statement st = con.createStatement();
            return (ResultSet) st.executeQuery(req);
        } catch (SQLException e) {
            return null;
        }
    }

    

    public static int insertRoom(int number, String type, Float price, Boolean available) {
        int a = 0;
        String req1 = "insert into room values ('" + number + "','" + type + "','"
                + price + "','" + available + "')";
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

    public static int SetState(int id)
    {
        String req = "UPDATE room SET  available=?  WHERE roomnumber = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
           
            ps.setBoolean(1, false);
            ps.setInt(2, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int modifierRoom(int number, String type, Float price, Boolean available) {
        String req = "UPDATE room SET type = ?, price = ?, available=?  WHERE roomnumber = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, type);
            ps.setFloat(2, price);
            ps.setBoolean(3, available);
            ps.setInt(4, number);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static int supprimerRoom(int id) {
        String req = "Delete from room where roomnumber = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
}
