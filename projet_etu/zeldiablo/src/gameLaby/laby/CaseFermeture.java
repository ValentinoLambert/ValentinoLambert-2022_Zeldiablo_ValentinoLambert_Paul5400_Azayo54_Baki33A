package gameLaby.laby;

public class CaseFermeture extends CaseDeclencheur{
    Porte p;

    public CaseFermeture(int x,int y,Porte p){
        super(x,y);
        this.p=p;
    }
    @Override
    void event(Personnage p) {
        if(p.etrePresent(this.getX(),this.getY())){
            System.out.println("fermeture");
            this.p.setFerme(true);
        }
    }
}
