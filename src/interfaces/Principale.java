package interfaces;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;


public class Principale extends JFrame {

        static JDesktopPane desktop = new JDesktopPane();
        public static int InternWidth = 730;
        public static int InternHeight = 700;

        public Principale()
        {
                this.setTitle("Gestion Hotel : ");
                this.setSize(745, 740);
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                desktop.add(new WelcomePage());
                this.add(desktop);
                this.setVisible(true);
        }

}
