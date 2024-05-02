import javax.swing.*;

public class Main extends JFrame{
    public static void main(String[] args)
    {
        // vi opretter vores vindue her med navnet vendespil og størelsen 900 * 1000
        JFrame vindue = new JFrame("Vendespil");
        vindue.setSize(900,1000);
        vindue.setDefaultCloseOperation(EXIT_ON_CLOSE);
        vindue.add(new GUI());
        vindue.setVisible(true);
    }
}