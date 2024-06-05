package gameLaby.laby;

public class CaseOuverture extends CaseDeclencheur{

    Porte p;

    public CaseOuverture(int x,int y,Porte p){
        super(x,y);
        this.p=p;
    }

    @Override
    void event(Personnage p) {
        if(p.etrePresent(this.getX(),this.getY())){
            this.p.setFerme(false);
            System.out.println("porte ouverte");
        }
    }
}
