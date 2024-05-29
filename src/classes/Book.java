package classes;

import java.sql.*;

public class Book {
    private int id, roomNumber;
    private String name;
    private Date chekInDate, checkOutDate;
    static Connection con = MyConnection.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);

    public Book(int id, int roomNumber, String name, Date chekInDate, Date checkOutDate) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.name = name;
        this.chekInDate = chekInDate;
        this.checkOutDate = checkOutDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getChekInDate() {
        return chekInDate;
    }

    public void setChekInDate(Date chekInDate) {
        this.chekInDate = chekInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public static ResultSet selection(String req) {
        try {
            Statement st = con.createStatement();
            return (ResultSet) st.executeQuery(req);
        } catch (SQLException e) {
            return null;
        }
    }

    public static int insertBooking(int number, String nom, Date in, Date out) {
        int a = 0;
        String req1 = "insert into booking values ('" + number + "','" + nom + "','"
                + in + "','" + out + "')";
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

    public static int modifierBooking(int number, String nom, Date in, Date out) {
        String req = "UPDATE booking SET name = ?, in = ?, out=?  WHERE roomnumber = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, nom);
            ps.setDate(2, in);
            ps.setDate(3, out);

            ps.setInt(4, number);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static int supprimerBooking(int number) {
        String req = "Delete from booking where roomnumber = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, number);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
