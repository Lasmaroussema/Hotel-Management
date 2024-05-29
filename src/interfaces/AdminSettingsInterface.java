package interfaces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminSettingsInterface extends JInternalFrame implements ActionListener {
    // Components
    private JButton ManageGuestButton, ManageEmployeesButton, ManageBookingsButton,ManageRoomsButton;
    

    public AdminSettingsInterface() {
        setTitle("Admin Settings Interface");
        setSize(Principale.InternWidth, Principale.InternHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));
        

        // Initialize components
        ManageGuestButton = new JButton("Manage Guests");
        ManageEmployeesButton = new JButton("Manage Employees");
        ManageBookingsButton = new JButton("Manage Bookings");
        ManageRoomsButton = new JButton("Manage Rooms");

        // Add action listeners
        ManageGuestButton.addActionListener(this);
        ManageEmployeesButton.addActionListener(this);
        ManageBookingsButton.addActionListener(this);
        ManageRoomsButton.addActionListener(this);

        // Add components to the frame
        add(ManageGuestButton);
        add(ManageEmployeesButton);
        add(ManageBookingsButton);
        add(ManageRoomsButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ManageGuestButton) {
            Principale.desktop.removeAll();
            Principale.desktop.add(new GuestManagementInterface());
        } else if (e.getSource() == ManageEmployeesButton) {
           
            Principale.desktop.removeAll();
            Principale.desktop.add(new EmployeeManagementInterface());
        } else if (e.getSource() == ManageBookingsButton) {
            
            Principale.desktop.removeAll();
            Principale.desktop.add(new HotelBookingManagement());
        
        } else if (e.getSource() == ManageRoomsButton) {
            
            Principale.desktop.removeAll();
            Principale.desktop.add(new RoomManagementInterface());
        }
    }

    public static void main(String[] args) {
         new AdminSettingsInterface();
    }
}
