package interfaces;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import classes.Room;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class RoomManagementInterface extends JInternalFrame implements ActionListener {
    // Components
    private JTextField roomNumberField, roomTypeField, priceField;
    private JButton addRoomButton, updateRoomButton, removeRoomButton,BackButton;
    private JTable roomTable;

    
    

    public RoomManagementInterface() {
        setTitle("Room Management");
        setSize(Principale.InternWidth, Principale.InternHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setClosable(true);

        // Initialize components
        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberLabel.setBounds(20, 20, 100, 25);
        add(roomNumberLabel);

        roomNumberField = new JTextField();
        roomNumberField.setBounds(120, 20, 100, 25);
        add(roomNumberField);

        JLabel roomTypeLabel = new JLabel("Room Type:");
        roomTypeLabel.setBounds(20, 60, 100, 25);
        add(roomTypeLabel);

        roomTypeField = new JTextField();
        roomTypeField.setBounds(120, 60, 100, 25);
        add(roomTypeField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 100, 100, 25);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(120, 100, 100, 25);
        add(priceField);

        addRoomButton = new JButton("Add Room");
        addRoomButton.setBounds(120, 280, 120, 25);
        addRoomButton.addActionListener(this);
        add(addRoomButton);

        updateRoomButton = new JButton("Update Room");
        updateRoomButton.setBounds(260, 280, 120, 25);
        updateRoomButton.addActionListener(this);
        add(updateRoomButton);

        removeRoomButton = new JButton("Remove Room");
        removeRoomButton.setBounds(400, 280, 120, 25);
        removeRoomButton.addActionListener(this);
        add(removeRoomButton);

        URL url = getClass().getResource("../images/room.jpeg");
        ImageIcon img = new ImageIcon(url);
        Image original = img.getImage();
        Image resized = original.getScaledInstance(350, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedimg = new ImageIcon(resized);
        JLabel image = new JLabel(resizedimg);
        image.setBounds(300, 10, 300, 250);
        add(image);

        // Table to display rooms
        String[] columnNames = { "Room Number", "Room Type", "Price" };
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        roomTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(roomTable);
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
        DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
     
        if (e.getSource() == addRoomButton) {
            String roomNumber = roomNumberField.getText();
            String roomType = roomTypeField.getText();
            String price = priceField.getText();
         
           
            model.addRow(new Object[] { roomNumber, roomType, price });
            int a = Room.insertRoom(Integer.parseInt(roomNumber), roomType, Float.parseFloat(price), true);
        } else if (e.getSource() == updateRoomButton) {
            int selectedRow = roomTable.getSelectedRow();
            if (selectedRow != -1) {
                String roomnumber = roomNumberField.getText();
                String type = roomTypeField.getText();
                String price = priceField.getText();
                model.setValueAt(roomnumber, selectedRow, 0);
                model.setValueAt(type, selectedRow, 1);
                model.setValueAt(price, selectedRow, 2);
                int a = Room.modifierRoom(Integer.parseInt(roomnumber), type, Float.parseFloat(price), false);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to update.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } 
        else if (e.getSource() == BackButton) {
            Principale.desktop.removeAll();
            Principale.desktop.add(new AdminSettingsInterface());
        } else if (e.getSource() == removeRoomButton) {
            int selectedRow = roomTable.getSelectedRow();
            if (selectedRow != -1) {
                
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a room to remove.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RoomManagementInterface();
            }
        });
    }
}
