package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JTextArea;

public class Lecture extends Thread {
    Scanner sc = new Scanner(System.in);
    BufferedReader pw;
    
    JTextArea j;
    Lecture(BufferedReader pw,JTextArea j)
    {
        this.pw = pw;
        this.j=j;
    }

    public void run() {
        while (true) {
            try {
                j.append("\nService: "+pw.readLine()+" Thank You , Have A good day");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
