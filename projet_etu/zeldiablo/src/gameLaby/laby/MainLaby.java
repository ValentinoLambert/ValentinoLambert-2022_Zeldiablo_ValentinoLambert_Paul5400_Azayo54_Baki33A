package gameLaby.laby;

import moteurJeu.MoteurJeu;

import java.io.IOException;
import java.util.Scanner;

public class MainLaby {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Entrer le nom d'un labyrinthe (avec l'extension) : ");
        String nomFichier = sc.nextLine();

        int width = 800;
        int height = 600;
        int pFPS = 10;

        LabyJeu lj = new LabyJeu();
        if (nomFichier == null) {
            lj.laby = new Labyrinthe();
        } else{
            lj.laby = new Labyrinthe("labySimple/" + nomFichier);
    }
        LabyDessin ld=new LabyDessin();

        MoteurJeu.setTaille(width,height);
        MoteurJeu.setFPS(8);

        MoteurJeu.launch(lj,ld);



    }
}
