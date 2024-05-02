import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

// En klasse kaldet GUI, der udvider JPanel og implementerer MouseListener-interfacet
public class GUI extends JPanel implements MouseListener{
    // Statisk variabel x
    public static int x=0;
    // Statisk variabel y
    public static int y=0;
    // Integer variabel til opbevaring af ny x-værdi
    int NewX;
    // Integer variabel til opbevaring af ny y-værdi
    int NewY;
    // Integer variabel til opbevaring af gammel x-værdi
    int OldX;
    // Integer variabel til opbevaring af gammel y-værdi
    int OldY;
    // Statisk variabel til opbevaring af ny talværdi
    public static int NewTal;
    // Statisk variabel til opbevaring af gammel talværdi
    public static int OldTal=0;
    // Variabel til opbevaring af antal stik
    int Antalstik;
    // Booleans til kontrol af fjernelse af brik og start af spillet
    boolean Fjernebrik=false;
    boolean Startspil=false;
    // bestemmer Størrelse på font
    int fontSize=20;

    // Opretter et objekt af klassen Brains
    Brains Brain1 = new Brains();

    // Konstruktør til opsætning af GUI
    GUI(){
        // sætter vores panel til hvid og gør panelt synligt
        setBackground(Color.WHITE);
        setVisible(true);
        // tilføjer en mouselistener til vores panel
        addMouseListener(this);
    }
    // Override af paintComponent-metoden til at tegne komponenterne på panelet
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // vi bestemmer vores font og sætter vores font smat farve på den tekst vi skriver.
        Font f = new Font("Comic Sans MS", Font.BOLD, fontSize);
        g2.setFont(f);
        g.setColor(Color.BLACK);
        // Tegner antal stik og træk
        if (Antalstik<10){
            g.drawString(String.valueOf(Antalstik),600,150);
            g.drawString(String.valueOf(Brains.Antaltræk),600,200);
            g.drawString("Antal Stik",630,150);
            g.drawString("Antal Træk",630,200);
        }
        // Tegner spillebrættet
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if(Brains.Flippedcards[j][i]){
                }
                else {
                    g.drawRect(100 * (i + 1), 100 * (j + 1), 90, 90);
                    // Vi tegner vores spillebræt
                }
            }
        }
        // Tegner talværdien for den nye position
        if (Brains.Spilleplade[NewY][NewX] != 0 && NewTal!=0){
            g.drawString(String.valueOf(NewTal), NewX * 100 + 145, NewY * 100 + 150);
        }
        // Tegner talværdien for den gamle position
        if (Brains.Spilleplade[OldY][OldX] != 0 && Brains.Antaltræk>1 && OldTal!=0){
            g.drawString(String.valueOf(OldTal), OldX * 100 + 145, OldY * 100 + 150);
        }
        // Tillykkes besked, når antallet af stik når 10
        if (Antalstik == 10) {
            g2.drawString("Tillykke du har vundet", 350, 380);
            g2.drawString("Du har brugt " + Brains.Antaltræk + " Træk",350,400);
            g.clearRect(550,150,100,100);
        }
        // Besked om at prøve igen, hvis antallet af træk overstiger 100
        if (Brains.Antaltræk>=100){
            g.clearRect(0,0,1000,1000);
            g2.drawString("Du har brugt 100 træk, prøv igen", 300,380);
        }
        // Tegner knappen til at prøve igen
        if (Antalstik==10 || Brains.Antaltræk>=100){
            // Opretter en JButton og placere den på panelet
            JButton prøvIgen = new JButton("Prøv Igen");
            add(prøvIgen);
            prøvIgen.setBounds(380,420,150,50);
            prøvIgen.setVisible(true);
            // Tilføjer en actionlistener til knappen
            prøvIgen.addActionListener(e -> {
                // Nulstiller variablerne og opretter et nyt spil
                Antalstik=0;
                Brains.Antaltræk=0;
                prøvIgen.setVisible(false);
                Brain1 = new Brains();
                repaint();
            });
        }
    }

    // Metode til at opdatere GUI'en
    public void Vendefelt(){
        repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Henter x- og y-koordinaterne for museklikket
        int x = e.getX();
        int y = e.getY();
        // Vi får endnu et træk tilføjet til Antaltræk
        Brains.Antaltræk++;

        // Tjekker om museklikket er inden for grænserne af spillepladen eller antallet af tilladte træk
        if (x >= 100 && x <= 500 && y >= 100 && y <= 600 &&(Antalstik<10||Brains.Antaltræk<100)) {
            // Gemmer den gamle x- og y-værdi
            OldX = NewX;
            OldY = NewY;
            // Beregner den nye x- og y-værdi baseret på museklikket
            NewX = x / 100 - 1;
            NewY = y / 100 - 1;

            // Udskriver talværdien for den nye position samt museklikkets koordinater
            System.out.println(NewTal);
            System.out.println(x + " " + y);
            // Gemmer den gamle talværdi og henter den nye talværdi fra spillepladen
            OldTal=NewTal;
            NewTal = Brains.Spilleplade[NewY][NewX];


            // Tjekker om den nye og gamle talværdi er ens og om positionen er forskellig
            if (NewTal!=0&&NewTal==OldTal && (OldX!=NewX || OldY!=NewY)){
                // Markerer de to kort som vendt
                Brains.Flippedcards[NewY][NewX] = true;
                Brains.Flippedcards[OldY][OldX] = true;
                // Fjerner talværdierne fra spillepladen
                Brains.Spilleplade[NewY][NewX] = 0;
                Brains.Spilleplade[OldY][OldX] = 0;
                // Udskriver det nye status for Flippedcards-arrayet
                System.out.println(Arrays.deepToString(Brains.Flippedcards));
                // Øger antallet af stik
                Antalstik++;
                Fjernebrik=true;
            }
            // Opdaterer GUI'en
            repaint();
        }
        // Hvis museklikket er uden for spillepladen, udskrives en fejlmeddelelse
        else {
            System.out.println("Du klikkede udenfor spillepladen");
        }
        // Kalder metoden til at opdatere GUI'en
        Vendefelt();
        // Opdaterer GUI'en
        repaint();
        // Angiver at spillet er startet
        Startspil=true;
    }


    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}