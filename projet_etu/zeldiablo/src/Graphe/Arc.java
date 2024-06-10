package Graphe;

/**
 * La classe Arc
 */
public class Arc {

    /**
     * getDEst
     *
     * @return la destination de l'arc
     */
    public String getDest() {
        return dest;
    }

    private String dest;


    /**
     * Getscout.
     *
     * @return le cout de l'arc
     */
    public double getCout() {
        return cout;
    }

    private double cout;

    /**
     * construit un nouvelle arc
     *
     * @param d destination de l'arc
     * @param c cout de l'arc
     * @throws Exception
     */
    public Arc(String d, double c) throws Exception {
        this.dest = d;
        if (c <= 0) {
            throw new Exception("Cout inferieur ou égal a 0");
        }
        this.cout = c;
    }

    public String toString() {
        return (dest + "(" + cout + ")");
    }

    /**
     * To graphviz string.
     *
     * @return chaine pour afficher le grahe dans graphviz
     */
    public String toGraphviz() {
        return (dest + " [label = " + cout + "]");
    }
}
