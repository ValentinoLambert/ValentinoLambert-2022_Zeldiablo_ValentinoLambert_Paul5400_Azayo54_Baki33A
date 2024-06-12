import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class TestPersonnage {
    @Test
    public void testEstACoterOk()throws IOException{
        Labyrinthe l=new Labyrinthe("fichierTest/laby0Test.txt");
        assertTrue(l.pj.estACoter(l.getMonstre().get(0)),"le mosntre est à coté du personnage");
    }
    @Test
    public void testEstACoterKO()throws IOException{
        Labyrinthe l=new Labyrinthe("fichierTest/laby2Test.txt");
        assertFalse(l.pj.estACoter(l.getMonstre().get(0)),"le monstre n'est pas à coté du personnage");
    }
    @Test
    public void testMonstreAttaqueOK()throws IOException{
        Labyrinthe l =new Labyrinthe("fichierTest/laby0Test.txt");
        l.getMonstre().get(0).attaquer(l.pj);
        assertEquals(99,l.pj.getVie(),"Le personnage aurait du prendre 1 point de degat");
    }
    @Test
    public void testJoueurAttaqueOK()throws IOException{
        Labyrinthe l =new Labyrinthe("fichierTest/laby0Test.txt");
        l.pj.attaquer(l.getMonstre().get(0));

        assertEquals(99,l.getMonstre().get(0).getVie(),"le monstre aurait du perdre un point de vie");
    }
    @Test
    public void testAffichageAttaqueMonstreJouerOK()throws IOException{
        Labyrinthe l =new Labyrinthe("fichierTest/laby0Test.txt");
        l.pj.attaquer(l.getMonstre().get(0));
        l.getMonstre().get(0).attaquer(l.pj);
        assertTrue(l.pj.getAttaque(),"le personnage aurait du attaquer et donc changer de couleur");
        assertTrue(l.getMonstre().get(0).getAttaque(),"le monstre aurait du attaquer et donc changer de couleur");
    }
    @Test
    public void testAffichageAttaqueMonstreJoueurFIN()throws IOException{
        Labyrinthe l =new Labyrinthe("fichierTest/laby0Test.txt");
        l.pj.attaquer(l.getMonstre().get(0));
        l.getMonstre().get(0).attaquer(l.pj);
        l.deplacerPerso(Labyrinthe.DROITE,l.pj);
        l.deplacerPerso(Labyrinthe.DROITE,l.getMonstre().get(0));
        assertFalse(l.pj.getAttaque(),"le personnage aurait du mettre fin à son attaque et donc ne pas changer de couleur");
        assertFalse(l.getMonstre().get(0).getAttaque(),"le monstre aurait du mettre fin à son attaque et donc ne pas changer de couleur");
    }
    @Test
    public void testAttaqueDirectionelleKO()throws IOException{
        Labyrinthe l =new Labyrinthe();
        l.persoAttaquerMonstre();
        assertFalse(l.pj.getAttaque(),"Le personnage vise la direction du bas et le mosntre est à sa droite il n'aurait donc pas du l'attaquer");
    }
    @Test
    public void testAttaqueDirectionelleOK()throws IOException{
        Labyrinthe l =new Labyrinthe("fichierTest/laby0Test.txt");
        l.deplacerPerso(Labyrinthe.DROITE,l.pj);
        l.persoAttaquerMonstre();
        assertTrue(l.pj.getAttaque(),"Le personnage vise la direction de Droite et le mosntre est à sa droite il aurait donc  du l'attaquer");
    }
    @Test
    public void testMortMonstreOK()throws IOException{
        Labyrinthe l =new Labyrinthe("fichierTest/laby0Test.txt");
        l.getMonstre().get(0).ajouterVie(-100);
        assertTrue(l.getMonstre().get(0).etreMort(),"le monstre devrait etre mort");
    }
}
