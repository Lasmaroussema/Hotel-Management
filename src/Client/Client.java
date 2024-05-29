package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import interfaces.Principale;

public class Client extends JInternalFrame {

    public Client() {
        setTitle("Admin Settings Interface");
        setSize(Principale.InternWidth, Principale.InternHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        JTextArea textArea = new JTextArea(40, 40);
        JLabel msgLabel = new JLabel("Enter your Choice Here");
        JTextField msg = new JTextField(40);
        JLabel GuestLabel = new JLabel("Enter The Guest Here");
        JTextField GuestName = new JTextField(40);
        JButton BackButton = new JButton("Back");
        JButton button = new JButton("Submit Answer");
        textArea.setBounds(0, 0, 500, 200);
        msgLabel.setBounds(20, 250, 280, 30);
        msg.setBounds(20, 290, 280, 30);
        GuestLabel.setBounds(20, 330, 280, 30);
        GuestName.setBounds(20, 370, 280, 30);
        button.setBounds(310, 410, 200, 30);
        BackButton.setBounds(160, 450, 200, 30);

        add(textArea);
        add(msg);
        add(button);
        add(BackButton);
        add(msgLabel);
        add(GuestLabel);
        add(GuestName);
        setVisible(true);

        button.addActionListener(null);
        BackButton.addActionListener(null);

        Scanner sc = new Scanner(System.in);
        System.out.println("Lance Client");

        try {

            Socket s = new Socket("127.0.0.1", 9000);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String ligne = br.readLine();
            textArea.append(ligne);

            BackButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textArea.append("\nClient Answer :" + msg.getText());

                    PrintWriter pw;
                    try {
                        pw = new PrintWriter(s.getOutputStream());
                        pw.println(msg.getText() + " " + GuestName.getText());
                        pw.flush();
                        msg.setText("");
                        new Ecriture(pw).start();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                }
            });
            new Lecture(br, textArea).start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();

    }
}
