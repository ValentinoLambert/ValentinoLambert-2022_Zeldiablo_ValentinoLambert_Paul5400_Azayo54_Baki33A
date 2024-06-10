import gameLaby.laby.CaseFermeture;
import gameLaby.laby.CaseOuverture;
import gameLaby.laby.CasePiege;
import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestGestionLabyrinthe {
    @Test
    public void testDeplacementPersoMur() throws IOException {
        Labyrinthe l=new Labyrinthe("fichierTest/laby1Text.txt");
        int x=l.pj.getX();
        int y=l.pj.getY();
        l.deplacerPerso(Labyrinthe.DROITE,l.pj);
        assertEquals(x,l.pj.getX(),"le perso ne devrait pas avoir bouger à cause du mur");
        assertEquals(y,l.pj.getY(),"le perso ne devrait pas avoir bouger à cause du mur");
    }
    //On utiliseras le labyrinthe par defaut pour ce test et nous rajouterons nous meme les casse peiger
    @Test
    public void testCasePieger(){
        Labyrinthe l=new Labyrinthe();
        l.ajouterCaseDeclencheur(new CasePiege(5,6));
        l.deplacerPerso(Labyrinthe.BAS,l.pj);
        System.out.println(l.pj.getX()+" "+l.pj.getY());
        assertEquals(90,l.pj.getVie(),"le personnage aurait du perdre de la vie en passant sur une case pieger");

    }
    @Test
    public void testPassageSecret(){
        Labyrinthe l=new Labyrinthe();
        l.ajouterCaseDeclencheur(new CaseOuverture(5,6,l.getPorte()));
        assertTrue(l.getPorte().etreFerme(),"la porte devrait etre fermé");
        l.deplacerPerso(Labyrinthe.BAS,l.pj);
        System.out.println(l.pj.getX()+" "+l.pj.getY());
        assertFalse(l.getPorte().etreFerme(),"la porte aurait du etre ouverte");
    }
    @Test
    public void testCaseFermeture(){
        Labyrinthe l=new Labyrinthe();
        l.ajouterCaseDeclencheur(new CaseOuverture(5,6,l.getPorte()));
        l.ajouterCaseDeclencheur(new CaseFermeture(6,6,l.getPorte()));
        l.deplacerPerso(Labyrinthe.BAS,l.pj);
        assertFalse(l.getPorte().etreFerme(),"la porte aurait du etre ouverte");
        l.deplacerPerso(Labyrinthe.DROITE,l.pj);
        assertTrue(l.getPorte().etreFerme(),"la porte devrait etre fermé");
    }
}
