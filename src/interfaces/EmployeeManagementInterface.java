package interfaces;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import classes.Employee;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class EmployeeManagementInterface extends JInternalFrame implements ActionListener {
    // Components
    private JTextField IdField,nameField, positionField;
    private JButton addButton, updateButton, deleteButton,BackButton;
    private JTable employeeTable;

    private MyTableModel model;
    

    public EmployeeManagementInterface() {
        setTitle("Employee Management");
        setSize(Principale.InternWidth, Principale.InternHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setClosable(true);

        // Initialize components
        JLabel IdLabel = new JLabel("Id:");
        IdLabel.setBounds(20, 20, 80, 25);
        add(IdLabel);

        IdField = new JTextField();
        IdField.setBounds(120, 20, 200, 25);
        add(IdField);


        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 50, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(120, 50, 200, 25);
        add(nameField);

        JLabel positionLabel = new JLabel("Position:");
        positionLabel.setBounds(20, 80, 80, 25);
        add(positionLabel);

        positionField = new JTextField();
        positionField.setBounds(120, 80, 200, 25);
        add(positionField);

        addButton = new JButton("Add");
        addButton.setBounds(20, 130, 80, 25);
        addButton.addActionListener(this);
        add(addButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(120, 130, 80, 25);
        updateButton.addActionListener(this);
        add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(220, 130, 80, 25);
        deleteButton.addActionListener(this);
        add(deleteButton);

        URL url = getClass().getResource("../images/employee.jpg");
        ImageIcon img = new ImageIcon(url);
        Image original = img.getImage();
        Image resized = original.getScaledInstance(350, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedimg = new ImageIcon(resized);
        JLabel image = new JLabel(resizedimg);
        image.setBounds(350, 10, 350, 200);
        add(image);


        // Table to display employee information
        String[] columnNames = { "Id","Name", "Position" };
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        employeeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBounds(10, 240, 700, 200);
        add(scrollPane);

        BackButton = new JButton("Back");
        BackButton.setBounds(220, 490, 80, 25);
        BackButton.addActionListener(this);
        add(BackButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();

        if (e.getSource() == addButton) {
            String id = IdField.getText();
            String name = nameField.getText();
            String position = positionField.getText();
            model.addRow(new Object[] { id, name, position });
            Employee.insertEmployee(Integer.parseInt(id), name, Integer.parseInt(position));
            clearFields();
        } else if (e.getSource() == updateButton) {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow != -1) {
                String id = IdField.getText();
                String name = nameField.getText();
                String position = positionField.getText();
                model.setValueAt(id, selectedRow, 0);
                model.setValueAt(name, selectedRow, 1);
                model.setValueAt(position, selectedRow, 2);
                int a=Employee.modifierEmployee(Integer.parseInt(id), name, Integer.parseInt(position));
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to update.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == deleteButton) {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow != -1) {
                
                model.removeRow(selectedRow);
                
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        
        } else if (e.getSource() == BackButton) {
            Principale.desktop.removeAll();
            Principale.desktop.add(new AdminSettingsInterface());
        }
    }

    private void clearFields() {
        IdField.setText("");
        nameField.setText("");
        positionField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EmployeeManagementInterface();
            }
        });
    }
}
