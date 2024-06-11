package graphe;

import java.util.ArrayList;
import java.util.List;


/**
 * La classe Noeud
 */
public class Noeud {

    private String nom;

    /**
     * Getnom.
     *
     * @return le nom de l'arc
     */
    public String getNom(){
        return nom;
    }


    /**
     * Getsadj.
     *
     * @return les arc adjacent au noeuds
     */
    public List<Arc> getAdj() {

        return adj;
    }


    private List<Arc> adj;

    /**
     * créer un novueau noeuds
     *
     * @param n le nom du noeuds
     */
    public Noeud(String n){
        this.nom=n;
        adj=new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        Noeud n=(Noeud)obj;
        return this.nom.equals(n.nom);
    }

    /**
     * Ajouter arc.
     *
     * @param destination  la destination de l'arc
     * @param cout        le cout de l'arc
     * @throws Exception
     */
    public void ajouterArc(String destination, double cout)throws Exception{
        adj.add(new Arc(destination,cout));
    }

    /**
     * adjString
     *
     * @return chaine de caracter des arc adjacent au noeuds
     */
    public String adjString(){
        StringBuffer retour=new StringBuffer();
        for(int i=0;i<adj.toArray().length;i++){
            retour.append(adj.get(i)+" ");
        }
        return(retour.toString());
    }
}
