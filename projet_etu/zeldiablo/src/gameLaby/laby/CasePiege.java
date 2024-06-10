package gameLaby.laby;

public class CasePiege extends CaseDeclencheur {
    boolean ammorce;
    boolean passerDessus;

    public CasePiege(int dx, int dy) {
        super(dx, dy);
        ammorce = true;
        passerDessus = false;
    }

    @Override
    public void event(Personnage p) {
        if (p.etrePresent(this.getX(), this.getY())) {
            if (ammorce) {
                System.out.println("boom");
                ammorce = false;
                p.ajouterVie(-10);
                passerDessus = true;
            }
        } else {
            ammorce = true;
        }
    }

     public boolean getPasserDessus() {
        return passerDessus;
     }


}
