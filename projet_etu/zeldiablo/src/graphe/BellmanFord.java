package graphe;

import java.util.ArrayList;

public class BellmanFord  {
    public Valeur resoudre(Graphe g,String depart){
        ArrayList<String> listeNom=new ArrayList<>(g.listeNoeuds());
        Valeur v=new Valeur();
        for(String s:listeNom) {
            if (s.equals(depart)) {
                v.setValeur(s, 0);
            } else {
                v.setValeur(s, Double.MAX_VALUE);
            }
            v.setParent(s, null);
        }
        boolean changer=false;
        while(!changer){
            changer=true;
            for(String s:listeNom){
                ArrayList<Arc> arcs=new ArrayList<>(g.suivants(s));
                for(Arc a:arcs){
                    String n=a.getDest();
                    double cout=a.getCout();
                    double distance=cout+v.getValeur(s);
                    if(distance<v.getValeur(n)){
                        v.setValeur(n,distance);
                        v.setParent(n,s);
                        changer=false;
                    }
                }
            }
            //System.out.println(v);
        }
        return v;
    }
}
