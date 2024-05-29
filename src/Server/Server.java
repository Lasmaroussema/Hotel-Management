package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
    public static void main(String[] args) {
        int nbclient = 0;

        try {
            ServerSocket server = new ServerSocket(9000);
            while (nbclient < 3) {
                Socket s = server.accept();

                nbclient++;

                new Discussion(s).start();

                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String answer = br.readLine();
                String ligne = answer.split(" ")[0];
                String Guest = answer.split(" ")[1];
                PrintWriter pw = new PrintWriter(s.getOutputStream());
                if (Integer.parseInt(ligne) == 1) {
                    pw.println("The service Room Service Will be there for " + Guest);
                }
                if (Integer.parseInt(ligne) == 2) {
                    pw.println("The service Room Cleaning Will be there for " + Guest);
                }
                if (Integer.parseInt(ligne) == 3) {
                    pw.println("The service Laundry Service Will be there for " + Guest);
                }

                pw.flush();
                new Ecriture(pw).start();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Arrêt Serveur");
    }
}
