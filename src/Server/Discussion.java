package Server;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Discussion extends Thread {
    Socket s;

    Discussion(Socket s) {
        this.s = s;
    }

    public void run() {
        PrintWriter pw;
        try {
            pw = new PrintWriter(s.getOutputStream());
            pw.println("Service :  Welcome To Guest Service :  1-Room Service 2-Room Cleaning 3-Laundry Service");
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}
