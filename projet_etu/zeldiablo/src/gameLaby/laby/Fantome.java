package gameLaby.laby;

public class Fantome extends Monstre{
    /**
     * constructeur
     *
     * @param dx  position selon x
     * @param dy  position selon y
     * @param vie
     */
    public Fantome(int dx, int dy, int vie) {
        super(dx, dy, vie);
    }
    public Fantome(int dx, int dy, int vie,String i) {
        super(dx,dy,vie,i);
    }
}
