package gameLaby.laby;


/**
 * gere un personnage situe en x,y
 */
public class Perso  extends Personnage{


    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Perso(int dx, int dy,int vie) {
        super(dx,dy,vie);
    }

    public boolean etreEnFace(Personnage p){
        int[] tempC=Labyrinthe.getSuivant(this.x,this.y,this.orientation);
        return(p.etrePresent(tempC[0],tempC[1]));
    }



}
