package Server;

import java.io.PrintWriter;
import java.util.Scanner;

public class Ecriture extends Thread
{
    Scanner sc = new Scanner(System.in);
    PrintWriter pw;

    Ecriture(PrintWriter pw)
    {
        this.pw = pw;
    }
    public void run()
    {
        while(true)
        {
            pw.println(sc.nextLine());
            pw.flush();
        }
    }
}