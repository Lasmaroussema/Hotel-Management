package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JTextArea;

public class Lecture extends Thread {
    Scanner sc = new Scanner(System.in);
    BufferedReader br;
    PrintWriter pw;
    JTextArea j;

    Lecture(BufferedReader br) {
        this.br = br;
        
    }

    public void run() {
        while (true) {
            try {
                System.out.println(br.readLine());
                // pw.println(br.readLine());
                // pw.flush();
               
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
