package gameLaby.laby;

public class Monstre extends Personnage {

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    private String intelligence;

    public Monstre(int dx, int dy, int vie) {
        super(dx, dy, vie);
        intelligence = Intelligence.intelligence[(int) (Math.round(Math.random() * (Intelligence.intelligence.length - 1)))];
    }
}
