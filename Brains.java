import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Brains {
    //her opretter vi vores liste med de tal der skal sætte ind i vores spil
    static Integer[] Tal = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10};
    //Arrayliste med tal fra 1-10 x2


    // variable som bestemer tallet fra vores TAl liste
    int Counter = 0;

// vi opretter to 2d array og en arrayliste.
    public static Boolean[][] Flippedcards = new Boolean[5][4];

    public static ArrayList<Integer> liste = new ArrayList<>(Arrays.asList(Tal));
    public static Integer[][] Spilleplade = new Integer[5][4];
    // laver et variable til at tælle træk
    public static int Antaltræk;

    Brains() {
        // vi laver et for loop som fylder vores Flippedecards array med false
        for (int k=0;k<5;k++){
            for (int l=0;l<4;l++){
                Flippedcards[k][l]=false;

            }
        }
        System.out.println(Arrays.deepToString(Flippedcards));

        // vi blander vores liste med alle tallene
        Collections.shuffle(liste);
        System.out.println(liste);
        // vi tager vores blanded liste og sætter den in i vores spilleplade array
        for (int i = 0; i < 5; i++) { // Itererer gennem 5 rækker
            for (int j = 0; j < 4; j++) { // Itererer gennem 4 kolonner
                Spilleplade[i][j] = liste.get(Counter);
                Counter++;
            }
        }
    }
}