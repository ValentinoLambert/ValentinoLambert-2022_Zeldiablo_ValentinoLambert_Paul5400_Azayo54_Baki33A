package graphe;

import java.util.List;

/**
 * interface graphe
 */
public interface Graphe {

    /**
     * methode qui retourne la liste de tout les noeud d'un graphe
     *
     * @return lsite de tout lesn oeuds du graphe
     */
    List<String> listeNoeuds();

    /**
     * methode pour donner l'arc de toute les laison de n
     *
     * @param n le noeud n
     * @return la liste des liaison de n
     */
    List<Arc> suivants(String n) ;
}
