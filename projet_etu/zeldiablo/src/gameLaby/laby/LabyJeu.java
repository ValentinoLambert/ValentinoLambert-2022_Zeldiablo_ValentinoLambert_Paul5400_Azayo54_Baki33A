package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.IOException;

public class LabyJeu implements Jeu {

    public Labyrinthe getLaby() {
        return laby;
    }



    public Labyrinthe laby;


    @Override
    public void update(double secondes, Clavier clavier) {


        laby.pj.setAttaque(false);
        for(Monstre m:laby.getMonstre()) {
            m.setAttaque(false);
            if (laby.pj.estACoter(m)) {
                m.attaquer(laby.pj);
            } else {

                this.laby.deplacerMonstreintelligence(m);
            }
        }

        if(clavier.space){
            laby.persoAttaquerMonstre();
        }
        
        if (clavier.droite) {
            laby.deplacerPerso(laby.DROITE, laby.pj);

        }
        if (clavier.gauche) {
            laby.deplacerPerso(laby.GAUCHE, laby.pj);

        }
        if (clavier.haut) {
            laby.deplacerPerso(laby.HAUT, laby.pj);


        }
        if (clavier.bas) {
            laby.deplacerPerso(laby.BAS, laby.pj);


        }
        laby.retirerMonstreMort();

        //on compare la vie du troll à la fin du tour avec sa vie initiale
        for(Monstre mo:laby.getMonstre()){
            if(mo instanceof Troll) {
                ((Troll) mo).regenerer();
                break;
            }
        }

    }

    @Override
    public void init() {

    }

    @Override
    public boolean etreFini() {


        return laby.etreFini();
    }
}
