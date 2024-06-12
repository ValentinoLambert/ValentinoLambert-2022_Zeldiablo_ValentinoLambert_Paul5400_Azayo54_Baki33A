package gameLaby.laby;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

public class LabyDessin implements DessinJeu {


    private String tiry="projet_etu/zeldiablo/spiriteTexture/thierry_henry.jpg";
    private String sol=   "projet_etu/zeldiablo/spiriteTexture/sol.png";
    private String hero="projet_etu/zeldiablo/spiriteTexture/heroNoDamage.png";
    private String monstre="projet_etu/zeldiablo/spiriteTexture/monstre.png";

    private String mur ="projet_etu/zeldiablo/spiriteTexture/mur.png";

    private String trollFrape="projet_etu/zeldiablo/spiriteTexture/monstreFrappe.png";
    private String trollFrappePas="projet_etu/zeldiablo/spiriteTexture/monstrefrappepas.png";
    private String fantome="projet_etu/zeldiablo/spiriteTexture/phantome.png";
    private String piege="projet_etu/zeldiablo/spiriteTexture/pik.png";
    private String porte="projet_etu/zeldiablo/spiriteTexture/porte.png";
    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) {
        Labyrinthe laby = ((LabyJeu) jeu).getLaby();

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int taille = 52;
        for (int i = 0; i < laby.getLength(); i++) {
            for (int j = 0; j < laby.getLengthY(); j++) {
                for (CaseDeclencheur c : laby.caseD) {
                    if (c instanceof CasePiege) {
                        if (((CasePiege) (c)).getPasserDessus()) {

                            gc.drawImage(new Image(piege), c.getX() * taille, c.getY() * taille);
                        }
                    }
                }

                if (laby.getMur(i, j)) {
                    gc.drawImage(new Image(mur), i * taille, j * taille);
                } else if (laby.pj.etrePresent(i, j)) {
                    gc.drawImage(new Image(sol), i * taille, j * taille);

                    gc.drawImage(new Image(hero), i * taille, j * taille);
                } else if (laby.monstrePresent(i, j)) {
                    Monstre tempM = laby.monstreEnXY(i, j);
                    if (tempM instanceof Fantome) {
                        if(laby.getMur(i,j)){
                            gc.drawImage(new Image(mur), i * taille, j * taille);

                        }else{
                            gc.drawImage(new Image(sol), i * taille, j * taille);

                        }


                        gc.drawImage(new Image(fantome), i * taille, j * taille);
                    } else if (tempM instanceof Troll) {
                        gc.drawImage(new Image(sol), i * taille, j * taille);

                        if (tempM.getAttaque()) {

                            gc.drawImage(new Image(trollFrape), i * taille, j * taille);
                        } else {

                            gc.drawImage(new Image(trollFrappePas), i * taille, j * taille);
                        }
                    } else {
                        gc.drawImage(new Image(sol), i * taille, j * taille);

                        gc.drawImage(new Image(monstre), i * taille, j * taille);
                    }
                } else if (laby.getPorte().etreFerme() && laby.getPorte().etrePresent(i, j)) {
                    gc.drawImage(new Image(mur), i * taille, j * taille);
                } else if(!laby.getPorte().etreFerme() && laby.getPorte().etrePresent(i, j)){
                    gc.drawImage(new Image(porte), i * taille, j * taille);
                } else {
                    gc.drawImage(new Image(sol), i * taille, j * taille);
                }
            }

            if (laby.etreFini()) {
                gc.setFill(Color.YELLOW);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setFill(Color.BLACK);
                gc.fillText("Fin du jeu", canvas.getWidth() / 2, canvas.getHeight() / 2);
            }
        }
    }
}