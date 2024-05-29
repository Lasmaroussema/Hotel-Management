package interfaces;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;



public class IHMAff extends JInternalFrame {
    MyTableModel model;
    JTable Info;
    JScrollPane jsp;
    public ResultSet rs;
    public String title;

    public JButton  backButton=new JButton("Back");

    public IHMAff(ResultSet rs, String title) {
        super(title); 
        model = new MyTableModel(rs);
        initializeComponents();
        createLayout();
        
    }

    private void initializeComponents() {
        Info = new JTable();
        Info.setModel(model);
        jsp = new JScrollPane(this.Info);
    }

    private void createLayout() {
        this.setLayout(null);
        jsp.setBounds(10, 0, 400, 420);
        this.add(jsp);
        backButton.setBounds(100, 480, 70, 30);
        backButton.addActionListener(null);
        this.add(backButton);
        setSize(800, 760);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setClosable(true);
        setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Principale.desktop.removeAll();
                Principale.desktop.add(new GuestManagementInterface());
            }
        });
    }

    public static void main(String[] args) {
       
    }
    
}

