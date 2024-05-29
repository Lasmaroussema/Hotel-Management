package interfaces;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class WelcomePage extends JInternalFrame {
    public WelcomePage() {
        setTitle("Welcome Page");
        setSize(Principale.InternWidth, Principale.InternHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setClosable(true);
        setLayout(null);

        JButton start = new JButton("Start");
        JLabel welcome = new JLabel("Welcome To Hotel Manager");

        URL url = getClass().getResource("../images/hotel.jpg");
        ImageIcon img = new ImageIcon(url);
        Image original = img.getImage();
        Image resized = original.getScaledInstance(350, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedimg = new ImageIcon(resized);
        JLabel image = new JLabel(resizedimg);
        image.setBounds(50, 30, 560, 250);

        welcome.setFont(new Font("Arial", Font.BOLD, 30));
        welcome.setBounds(130, 290, 500, 70);
        start.setBounds(260, 380, 140, 30);

        add(image);
        add(welcome);
        add(start);
        start.addActionListener(null);
        setVisible(true);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Principale.desktop.removeAll();
                Principale.desktop.repaint();
                Principale.desktop.add(new AdminSettingsInterface());
            }
        });
    }

    public static void main(String[] args) {
        new WelcomePage();
    }
}
