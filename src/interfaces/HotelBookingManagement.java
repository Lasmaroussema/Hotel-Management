package interfaces;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import classes.Book;
import classes.Room;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.Date;

public class HotelBookingManagement extends JInternalFrame implements ActionListener {
    // Components
    private JTextField nameField, roomNumberField, checkInDateField, checkOutDateField;
    private JButton addBookingButton, updateBookingButton, cancelBookingButton,BackButton;
    private JTable bookingsTable;

  
    

    public HotelBookingManagement() {
        setTitle("Hotel Booking Management");
        setSize(Principale.InternWidth, Principale.InternHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setClosable(true);

        // Initialize components
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(120, 20, 200, 25);
        add(nameField);

        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberLabel.setBounds(20, 60, 100, 25);
        add(roomNumberLabel);

        roomNumberField = new JTextField();
        roomNumberField.setBounds(120, 60, 100, 25);
        add(roomNumberField);

        JLabel checkInDateLabel = new JLabel("Check-In Date:");
        checkInDateLabel.setBounds(20, 100, 100, 25);
        add(checkInDateLabel);

        checkInDateField = new JTextField();
        checkInDateField.setBounds(120, 100, 100, 25);
        add(checkInDateField);

        JLabel checkOutDateLabel = new JLabel("Check-Out Date:");
        checkOutDateLabel.setBounds(20, 140, 100, 25);
        add(checkOutDateLabel);

        checkOutDateField = new JTextField();
        checkOutDateField.setBounds(120, 140, 100, 25);
        add(checkOutDateField);

        addBookingButton = new JButton("Add Booking");
        addBookingButton.setBounds(120, 280, 120, 25);
        addBookingButton.addActionListener(this);
        add(addBookingButton);

        updateBookingButton = new JButton("Update Booking");
        updateBookingButton.setBounds(260, 280, 120, 25);
        updateBookingButton.addActionListener(this);
        add(updateBookingButton);

        cancelBookingButton = new JButton("Cancel Booking");
        cancelBookingButton.setBounds(400, 280, 120, 25);
        cancelBookingButton.addActionListener(this);
        add(cancelBookingButton);

        URL url = getClass().getResource("../images/book.jpg");
        ImageIcon img = new ImageIcon(url);
        Image original = img.getImage();
        Image resized = original.getScaledInstance(350, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedimg = new ImageIcon(resized);
        JLabel image = new JLabel(resizedimg);
        image.setBounds(360, 10, 300, 250);
        add(image);

        // Table to display bookings
        String[] columnNames = { "Name", "Room Number", "Check-In Date", "Check-Out Date" };
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        bookingsTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(bookingsTable);
        scrollPane.setBounds(10, 320, 700, 200);
        add(scrollPane);

        BackButton = new JButton("Back");
        BackButton.setBounds(250, 560, 120, 25);
        BackButton.addActionListener(this);
        add(BackButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) bookingsTable.getModel();
        // Perform actions based on button clicks
        if (e.getSource() == addBookingButton) {
            String name = nameField.getText();
            String roomNumber = roomNumberField.getText();
            String checkInDate = checkInDateField.getText();
            String checkOutDate = checkOutDateField.getText();
           
            model.addRow(new Object[] { name, roomNumber, checkInDate, checkOutDate });
            int a = Book.insertBooking(Integer.parseInt(roomNumber), name, Date.valueOf(checkInDate),
                    Date.valueOf(checkOutDate));
            int b = Room.SetState(Integer.parseInt(roomNumber));
        } else if (e.getSource() == updateBookingButton) {
            int selectedRow = bookingsTable.getSelectedRow();
            if (selectedRow != -1) {
                String name = nameField.getText();
                String roomnumber = roomNumberField.getText();
                String checkin = checkInDateField.getText();
                String checkout = checkOutDateField.getText();
                model.setValueAt(name, selectedRow, 0);
                model.setValueAt(roomnumber, selectedRow, 1);
                model.setValueAt(checkin, selectedRow, 2);
                model.setValueAt(checkout, selectedRow, 3);
                int a = Book.modifierBooking(Integer.parseInt(roomnumber), name, Date.valueOf(checkin),
                        Date.valueOf(checkout));
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to update.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == cancelBookingButton) {
            int selectedRow = bookingsTable.getSelectedRow();
            if (selectedRow != -1) {
                int a = Integer.parseInt((String) model.getValueAt(selectedRow, 1));
                if (a == 1)
                {
                    JOptionPane.showMessageDialog(this, "Booking canceled");
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Booking canceled");

                }
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a booking to cancel.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == BackButton)
        {
            Principale.desktop.removeAll();
            Principale.desktop.add(new AdminSettingsInterface());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HotelBookingManagement();
            }
        });
    }
}
