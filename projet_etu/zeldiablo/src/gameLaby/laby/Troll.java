package gameLaby.laby;

public class Troll extends Monstre {


    public Troll(int dx, int dy, int vie) {
        super(dx,dy,vie);
    }
    public Troll(int dx, int dy, int vie,String i) {
        super(dx,dy,vie,i);
    }

    public void regenerer(){
        if(!this.etreMort() && this.getVie()<this.getMaxVie()){

            this.ajouterVie(1);

        }
    }
}
