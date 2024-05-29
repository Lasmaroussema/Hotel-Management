package classes;


import java.sql.*;


public class Guest {
    
    private int id;
    private String name, contact, nationality;
    static Connection con = MyConnection.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
    

    public Guest(int id, String name, String contact, String nationality) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.nationality = nationality;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
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
                    "--------------------------------\n" + "id  | Name    | Contact  | nationality\n");
            while (rs.next()) {
                int id = rs.getInt(1);
                String n = rs.getString(2);
                String c = rs.getString(3);
                String nat = rs.getString(4);
                
                

                System.out.println(id + "  | " + n + "    | " + c + "  | " + nat + " | ");
            }
            System.out.println("--------------------------------\n");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int insertGuest(int Id, String nom, String contact, String nationality) {
        int a = 0;
        String req1 = "insert into guest values ('" + Id + "','" + nom + "','"
                + contact + "','" + nationality + "')";
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

    public static int modifierGuest(int id, String nom, String contact, String nationality) {
        String req = "UPDATE guest SET nom = ?, contact = ?, nationality=?  WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, nom);
            ps.setString(2, contact);
            ps.setString(3, nationality);
            ps.setInt(4, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static int supprimerGuest(int id) {
        String req = "Delete from guest where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
}
