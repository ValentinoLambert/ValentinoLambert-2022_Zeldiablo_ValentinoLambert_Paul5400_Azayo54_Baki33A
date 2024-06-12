import gameLaby.laby.*;
import graphe.BellmanFord;
import graphe.GrapheLabyrinthe;
import graphe.Valeur;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class TestMonstre {
    @Test
    public void testMonstredansPerso() throws IOException {
        Labyrinthe laby=new Labyrinthe("./fichierTest/laby0Test.txt");
        int x=laby.getMonstre().get(0).getX();
        int y=laby.getMonstre().get(0).getY();

        laby.deplacerPerso(Labyrinthe.GAUCHE,laby.getMonstre().get(0));
        int x2=laby.getMonstre().get(0).getX();
        int y2=laby.getMonstre().get(0).getY();

        assertEquals(x,x2,"le monstre ne devrait pas avoir bouger");
        assertEquals(y,y2,"le monstre ne devrait pas avoir bouger");
    }
    @Test
    public void testMonstredansMur()throws IOException{
        Labyrinthe laby=new Labyrinthe("./fichierTest/laby0Test.txt");
        int x=laby.getMonstre().get(0).getX();
        int y=laby.getMonstre().get(0).getY();

        laby.deplacerPerso(Labyrinthe.DROITE,laby.getMonstre().get(0));
        int x2=laby.getMonstre().get(0).getX();
        int y2=laby.getMonstre().get(0).getY();

        assertEquals(x,x2,"le monstre ne devrait pas avoir bouger");
        assertEquals(y,y2,"le monstre ne devrait pas avoir bouger");
    }
    @Test
    public void deplacementOK()throws IOException{
        Labyrinthe laby=new Labyrinthe();
        int x=laby.getMonstre().get(0).getX();
        int y=laby.getMonstre().get(0).getY()-1;

        laby.deplacerPerso(Labyrinthe.HAUT,laby.getMonstre().get(0));
        int x2=laby.getMonstre().get(0).getX();
        int y2=laby.getMonstre().get(0).getY();

        assertEquals(x,x2,"le monstre ne devrait pas avoir bouger sur l'axe x");
        assertEquals(y,y2,"le monstre devrait avoir bouger d'un cran en haut");
    }
    @Test
    public void generationMonstre()throws IOException{
        Labyrinthe laby=new Labyrinthe("./fichierTest/laby0Test.txt");
        assertTrue(laby.getMonstre().size()>1,"il devrait y avoir plus de un monstre");
    }

    @Test
    public void monstreComportementIntelligent()throws IOException{
        Labyrinthe l=new Labyrinthe();
        l.murs[2][1]=true;
        GrapheLabyrinthe g=new GrapheLabyrinthe(l);
        BellmanFord b=new BellmanFord();
        Valeur v= b.resoudre(g,("(1,1)"));

        ArrayList<String> a=(ArrayList<String>)  v.calculerChemin(("(5,1)"));

        System.out.println(a);
        for(String s:a) {
            assertFalse(s.equals("(2,1)"), "le chemin ne devrait pas passer par (2,1) car il y a un mur");
        }
    }
    @Test
    public void testIntelligenceDifferent()throws IOException{
        Fantome f=new Fantome(0,0,1,Intelligence.NULLE);
        Fantome f2=new Fantome(0,0,1,Intelligence.INTELLIGENT);
        Troll t=new Troll(0,0,1,Intelligence.NULLE);
        Troll t2=new Troll(0,0,1,Intelligence.INTELLIGENT);
        Monstre m=new Monstre(0,0,1,Intelligence.NULLE);
        Monstre m2=new Monstre(0,0,1,Intelligence.INTELLIGENT);
        assertFalse(f.getIntelligence().equals(f2.getIntelligence()),"il ne devrait pas avoir la meme intelligence");
        assertFalse(t.getIntelligence().equals(t2.getIntelligence()),"il ne devrait pas avoir la meme intelligence");
        assertFalse(m.getIntelligence().equals(m2.getIntelligence()),"il ne devrait pas avoir la meme intelligence");
    }
    @Test
    public void testTroll()throws IOException{
        Troll t=new Troll(0,0,3);
        t.ajouterVie(-1);
        t.regenerer();
        assertEquals(3,t.getVie(),"le troll aurait du regenerer");
    }
    @Test
    public void deplacementFantome()throws IOException{
        Labyrinthe l=new Labyrinthe();
        Fantome f=new Fantome(8,1,10);
        l.monstre.add(f);
        l.deplacerPerso(Labyrinthe.DROITE,f);
        assertEquals(9,f.getX(),"le fantome aurait ud se deplacer dans le mur");
    }

}
