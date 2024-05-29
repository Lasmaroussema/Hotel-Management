package interfaces;

import javax.swing.*;

import com.mysql.jdbc.ResultSet;

import classes.Guest;

import java.awt.Image;
import java.awt.event.*;
import java.net.URL;
import java.sql.SQLException;

public class GuestManagementInterface extends JInternalFrame implements ActionListener {
    // Components
    private JTextField idField, nameField, contactField, nationalityField;
    private JButton addGuestButton, deleteGuestButton, searchButton, showGuests, backButton, GetService;
    private JTextArea outputArea;
    private MyTableModel model;
    private JTable table = new JTable();

    public GuestManagementInterface() {
        setTitle("Guest Management");
        setSize(Principale.InternWidth, Principale.InternHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setClosable(true);

        // Initialize components
        JLabel idLabel = new JLabel("Id:");
        idLabel.setBounds(20, 20, 80, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(120, 20, 200, 25);
        add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 60, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(120, 60, 200, 25);
        add(nameField);

        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setBounds(20, 100, 80, 25);
        add(contactLabel);

        contactField = new JTextField();
        contactField.setBounds(120, 100, 200, 25);
        add(contactField);

        JLabel nationalityLabel = new JLabel("Nationality:");
        nationalityLabel.setBounds(20, 140, 80, 25);
        add(nationalityLabel);

        nationalityField = new JTextField();
        nationalityField.setBounds(120, 140, 200, 25);
        add(nationalityField);

        addGuestButton = new JButton("Add Guest");
        addGuestButton.setBounds(120, 240, 120, 25);
        addGuestButton.addActionListener(this);
        add(addGuestButton);

        searchButton = new JButton("Search Guest");
        searchButton.setBounds(260, 240, 120, 25);
        searchButton.addActionListener(this);
        add(searchButton);

        deleteGuestButton = new JButton("Delete Guest");
        deleteGuestButton.setBounds(400, 240, 120, 25);
        deleteGuestButton.addActionListener(this);
        deleteGuestButton.setEnabled(false);
        add(deleteGuestButton);

        showGuests = new JButton("Show Guests");
        showGuests.setBounds(160, 420, 140, 25);
        showGuests.addActionListener(this);
        add(showGuests);

        backButton = new JButton("Back");
        backButton.setBounds(160, 460, 140, 25);
        backButton.addActionListener(this);
        add(backButton);

        GetService = new JButton("Get Service");
        GetService.setBounds(160, 500, 140, 25);
        GetService.addActionListener(this);
        add(GetService);

        URL url = getClass().getResource("../images/guest.jpg");
        ImageIcon img = new ImageIcon(url);
        Image original = img.getImage();
        Image resized = original.getScaledInstance(350, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedimg = new ImageIcon(resized);
        JLabel image = new JLabel(resizedimg);
        image.setBounds(350, 10, 300, 200);
        add(image);

        outputArea = new JTextArea();
        outputArea.setBounds(20, 280, 450, 100);
        add(outputArea);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addGuestButton) {
            String id = idField.getText();
            String name = nameField.getText();
            String contact = contactField.getText();
            String nationality = nationalityField.getText();
            outputArea.append(
                    "Guest added:\nId:" + id + " \nName: " + name + "\nContact: " + contact + "\nNationality: "
                            + nationality + "\n\n");

            int a = Guest.insertGuest(Integer.parseInt(id), name, contact, nationality);
            if (a == 0) {
                JOptionPane.showMessageDialog(null, "Error saving the new Guest");
            } else {
                clearFields();
                JOptionPane.showMessageDialog(null, "New Guest added succesfully !! ");
            }
        }
        if (e.getSource() == showGuests) {
            Principale.desktop.removeAll();

            Principale.desktop.add(new IHMAff(Guest.selection("SELECT * from Guest"), "Guests List :"));

        }
        if (e.getSource() == backButton) {
            Principale.desktop.removeAll();
            Principale.desktop.add(new AdminSettingsInterface());
        }
        if (e.getSource() == GetService) {
            Principale.desktop.removeAll();
            Principale.desktop.add(new Client.Client());
            Principale.desktop.add(new AdminSettingsInterface());

        }
        if (e.getSource() == searchButton) {
            if (idField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please add the Guest id", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                ResultSet rs = (ResultSet) Guest.selection("SELECT * FROM GUEST where id=" + idField.getText());
                try {

                    if (rs.next()) {
                        nameField.setText(rs.getString(2));
                        contactField.setText(rs.getString(3));
                        nationalityField.setText(rs.getString(4));
                        JOptionPane.showMessageDialog(null, "Guest Found ");
                        deleteGuestButton.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "No Guest Found ");
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                deleteGuestButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int id = Integer.parseInt(idField.getText());
                        int a = Guest.supprimerGuest(id);
                        if (a == 1) {
                            JOptionPane.showMessageDialog(null, "Guest Deleted ");
                            clearFields();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error deleting Guest");
                        }
                    }

                });
            }

        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        contactField.setText("");
        nationalityField.setText("");
    }

    public static void main(String[] args) {
        new GuestManagementInterface();
    }
}
