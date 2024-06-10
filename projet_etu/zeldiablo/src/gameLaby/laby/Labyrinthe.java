package gameLaby.laby;

import graphe.BellmanFord;
import graphe.GrapheLabyrinthe;
import graphe.Valeur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * classe labyrinthe. represente un labyrinthe avec
 * <ul> des murs </ul>
 * <ul> un personnage (x,y) </ul>
 */
public class Labyrinthe {

    /**
     * Constantes char
     */
    public static final char MUR = 'X';
    public static final char PJ = 'P';
    public static final char VIDE = '.';


    /**
     * constantes actions possibles
     */
    public static final String HAUT = "Haut";
    public static final String BAS = "Bas";
    public static final String GAUCHE = "Gauche";
    public static final String DROITE = "Droite";
    public static final char MONSTRE = 'M';
    /**
     * attribut du personnage
     */
    public Perso pj;
    public ArrayList<Monstre> monstre;

    /**
     * les murs du labyrinthe
     */
    public boolean[][] murs;
    public ArrayList<CaseDeclencheur> caseD;

    Porte p;

    /**
     * retourne la case suivante selon une actions
     *
     * @param x      case depart
     * @param y      case depart
     * @param action action effectuee
     * @return case suivante
     */


    public static int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case HAUT:
                // on monte une ligne
                y--;
                break;
            case BAS:
                // on descend une ligne
                y++;
                break;
            case DROITE:
                // on augmente colonne
                x++;
                break;
            case GAUCHE:
                // on augmente colonne
                x--;
                break;
            default:
                throw new Error("action inconnue");
        }
        int[] res = {x, y};
        return res;
    }

    /**
     * charge le labyrinthe
     *
     * @param nom nom du fichier de labyrinthe
     * @return labyrinthe cree
     * @throws IOException probleme a la lecture / ouverture
     */
    public Labyrinthe(String nom) throws IOException {
        this.monstre = new ArrayList<>();
        // ouvrir fichier
        FileReader fichier = new FileReader(nom);
        BufferedReader bfRead = new BufferedReader(fichier);

        int nbLignes, nbColonnes;
        // lecture nblignes
        nbLignes = Integer.parseInt(bfRead.readLine());
        // lecture nbcolonnes
        nbColonnes = Integer.parseInt(bfRead.readLine());

        // creation labyrinthe vide
        this.murs = new boolean[nbColonnes][nbLignes];
        this.caseD = new ArrayList<>();
        this.pj = null;

        // lecture des cases
        String ligne = bfRead.readLine();

        // stocke les indices courants
        int numeroLigne = 0;

        // parcours le fichier
        while (ligne != null) {

            // parcours de la ligne
            for (int colonne = 0; colonne < ligne.length(); colonne++) {
                char c = ligne.charAt(colonne);
                switch (c) {
                    case MUR:
                        this.murs[colonne][numeroLigne] = true;
                        break;
                    case VIDE:
                        this.murs[colonne][numeroLigne] = false;
                        break;

                    case MONSTRE:
                        this.murs[colonne][numeroLigne] = false;
                        this.monstre.add(new Monstre(colonne, numeroLigne, 10));
                        break;
                    case PJ:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.pj = new Perso(colonne, numeroLigne, 100);
                        break;

                    default:
                        throw new Error("caractere inconnu " + c);
                }

            }

            // lecture
            ligne = bfRead.readLine();
            numeroLigne++;
        }

        // ferme fichier
        bfRead.close();
        this.caseD = new ArrayList<>();

        //ajout de casse pieger aléatoire
        int[] coord = this.genererCoorValid();

        this.ajouterCaseDeclencheur(new CasePiege(coord[0], coord[1]));

        coord = this.genererCoorValid();

        this.p = new Porte(coord[0], coord[1]);

        coord = this.genererCoorValid();

        this.ajouterCaseDeclencheur(new CaseOuverture(coord[0], coord[1], this.p));

        coord = this.genererCoorValid();
        this.ajouterCaseDeclencheur(new CaseFermeture(coord[0], coord[1], this.p));

        coord=this.genererCoorValid();
        while (this.cooorValideMonstre(coord[0],coord[1])){
            coord=this.genererCoorValid();
        }
        this.monstre.add(new Monstre(coord[0],coord[1],10));
        coord=this.genererCoorValid();
        while (this.cooorValideMonstre(coord[0],coord[1])){
            coord=this.genererCoorValid();
        }
        this.monstre.add(new Troll(coord[0],coord[1],2));
        coord=this.genererCoorValid();
        while (this.cooorValideMonstre(coord[0],coord[1])){
            coord=this.genererCoorValid();
        }
        this.monstre.add(new Fantome(coord[0],coord[1],10));
    }

    public void ajouterCaseDeclencheur(CaseDeclencheur c) {
        this.caseD.add(c);
    }

    /*
    * true si un monstre est present en x y
     */

    public boolean monstrePresent(int x, int y) {
        boolean retour = false;
        for (Monstre m : monstre) {
            retour = m.etrePresent(x, y);
            if (retour) {
                break;
            }
        }
        return (retour);
    }

    public boolean cooorValideMonstre(int x,int y){
        return !this.murs[x][y]&&!monstrePresent(x,y)&&this.pj.etrePresent(x,y);
    }


    /*
    * renvoie le monstre au coordonnées x y, null si il n'y en a pas
     */

    public Monstre monstreEnXY(int x,int y){
        Monstre retour=null;

        for (Monstre m : monstre) {
            if (m.etrePresent(x, y)) {
                retour = m;
                break;
            }
        }
        return (retour);
    }

    public int[] genererCoorValid() {
        int x = (int) Math.floor(Math.random() * this.getLength());
        int y = (int) Math.floor(Math.random() * this.getLengthY());
        while (getMur(x, y) || this.pj.etrePresent(x, y) || this.monstrePresent(x, y)) {
            x = (int) Math.floor(Math.random() * this.getLength());
            y = (int) Math.floor(Math.random() * this.getLengthY());
        }
        return new int[]{x, y};
    }


    public Labyrinthe() {
        this.monstre = new ArrayList<>();

        this.murs = new boolean[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.murs[i][j] = false;
            }
        }

        for (int i = 0; i < 10; i++) {
            this.murs[i][0] = true;
            this.murs[i][9] = true;
            this.murs[0][i] = true;
            this.murs[9][i] = true;
        }

        this.pj = new Perso(5, 5, 100);
        this.monstre.add(new Monstre(6, 5, 100));
        this.p = new Porte(3, 3);
        this.caseD = new ArrayList<>();
    }


    /**
     * deplace le personnage en fonction de l'action.
     * gere la collision avec les murs
     *
     * @param action une des actions possibles
     */

    public void deplacerPerso(String action, Personnage perso) {
        perso.orientation = action;
        perso.setAttaque(false);
        // case courante
        int[] courante = {perso.x, perso.y};

        // calcule case suivante
        int[] suivante = getSuivant(courante[0], courante[1], action);

        // si c'est pas un mur, on effectue le deplacement
        if (perso instanceof Fantome) {
            perso.x = suivante[0];
            perso.y = suivante[1];
        } else {
            if (!this.murs[suivante[0]][suivante[1]] && !this.monstrePresent(suivante[0], suivante[1]) && !this.pj.etrePresent(suivante[0], suivante[1])) {
                // on met a jour personnage
                if (!this.p.etrePresent(suivante[0], suivante[1]) || ((this.p.etrePresent(suivante[0], suivante[1]) && !this.p.etreFerme()))) {
                    perso.x = suivante[0];
                    perso.y = suivante[1];

                }

            }
        }
        for (CaseDeclencheur c : caseD) {
            c.event(this.pj);
            if (!(c instanceof CasePiege)) {
                for (Monstre m : monstre) {
                    c.event(m);
                }
            }
        }

    }

    /*
    retire tout les monstres mort de la liste de monstre
     */




    public void retirerMonstreMort(){
        for(Monstre m:monstre){
            if(m.etreMort()){

                monstre.remove(m);
            }
        }

    }
    /**
     * jamais fini
     *
     * @return fin du jeu
     */


    public boolean etreFini() {
        if (pj.etreMort() || monstre.size() == 0) {
            System.out.println("Fin du jeu");
            return true;
        }
        return false;
    }

    // ##################################
    // GETTER
    // ##################################

    /**
     * return taille selon Y
     *
     * @return
     */
    public int getLengthY() {
        return murs[0].length;
    }

    /**
     * return taille selon X
     *
     * @return
     */
    public int getLength() {
        return murs.length;
    }

    /**
     * return mur en (i,j)
     *
     * @param x
     * @param y
     * @return
     */
    public boolean getMur(int x, int y) {
        // utilise le tableau de boolean
        return this.murs[x][y];
    }

    public ArrayList<Monstre> getMonstre() {
        return this.monstre;
    }

    public Porte getPorte() {
        return p;
    }

    public void persoAttaquerMonstre() {
        for (Monstre m : monstre) {
            if (this.pj.etreEnFace(m)) {
                this.pj.attaquer(m);
                break;
            }
        }

    }


    public void deplacerIntelligenceMoyenne(Monstre m){
        if(m.x>pj.x){
            this.deplacerPerso(Labyrinthe.GAUCHE,m);
        } else if (m.x<pj.x){
            this.deplacerPerso(Labyrinthe.DROITE,m);
        }else {
            if(m.y>pj.y){
                this.deplacerPerso(Labyrinthe.HAUT,m);

            }else if(m.y<pj.y){
                this.deplacerPerso(Labyrinthe.BAS,m);

            }
        }
    }
    public void deplacerIntellifenceUltime(Monstre m){
        GrapheLabyrinthe g=new GrapheLabyrinthe(this);
        BellmanFord b=new BellmanFord();
        String c="("+m.getX()+","+m.getY()+")";
        Valeur v=b.resoudre(g,c);
        ArrayList<String> l=(ArrayList)v.calculerChemin("("+this.pj.getX()+","+this.pj.getY()+")");
        if(l.size()>1) {
            int coor[] = GrapheLabyrinthe.extraireCoordonnees(l.get(1));
            if (coor[0] > m.getX()) {
                this.deplacerPerso(Labyrinthe.DROITE, m);
            } else if (coor[0] < m.getX()) {
                this.deplacerPerso(Labyrinthe.GAUCHE, m);
            } else if (coor[1] > m.getY()) {
                this.deplacerPerso(Labyrinthe.BAS, m);
            } else if (coor[1] < m.getY()) {
                this.deplacerPerso(Labyrinthe.HAUT, m);
            }
        }
    }
    public void deplacerMonstreintelligence(Monstre m){
        switch (m.getIntelligence()){
            case Intelligence.FAIBLE :
                String[] action = {Labyrinthe.GAUCHE, Labyrinthe.DROITE, Labyrinthe.HAUT, Labyrinthe.BAS};
                this.deplacerPerso(action[(int) Math.floor(Math.random() * action.length)],m);
                break;
            case Intelligence.MOYENNE:
                deplacerIntelligenceMoyenne(m);
                break;
            case Intelligence.INTELLIGENT:
                deplacerIntellifenceUltime(m);
                break;
        }
    }

}
