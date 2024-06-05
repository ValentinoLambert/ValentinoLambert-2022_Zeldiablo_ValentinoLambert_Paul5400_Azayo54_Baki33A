package gameLaby.laby;

public class Porte {
    int x,y;
    private boolean ferme;



    boolean etrePresent(int x,int y){
        if(this.x==x&&this.y==y){
            return true;
        }
        return false;
    }
    public Porte(int x, int y){
        this.x=x;
        this.y=y;
        ferme = true;
    }

    public boolean etreFerme() {
        return ferme;
    }

    public void setFerme(boolean ferme) {
        this.ferme = ferme;
    }

    public boolean estTraversable(int x,int y){
        if(this.etrePresent(x,y)){
            return !this.etreFerme();
        }
        return true;
    }

}
