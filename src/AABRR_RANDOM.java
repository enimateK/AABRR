package src;

import java.util.ArrayList;
import java.util.Collections;

public class AABRR_RANDOM {

    public void GenererAABRRAleatoire(int nbNoeuds, int max) {
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 1; i <= nbNoeuds ;i++) {
            values.add(this.getRandomInt(1, max));
        }
        Collections.sort(values);
        int arbreMin = 0;
        ArrayList<AABRR> sousArbres = new ArrayList<AABRR>();
        for (Integer arbreMax: values) {
            arbreMin = arbreMin + 1;
            AABRR sousArbre = new AABRR(arbreMin, arbreMax, this.getRandomInt(arbreMin, arbreMax) );
            sousArbres.add(sousArbre);
            arbreMin = arbreMax + 1;
        }
        AABRR randomAABRR = this.CreerAABRR(sousArbres);
        String val = randomAABRR.Parcours("");
        System.out.println(val);
    }

    private AABRR CreerAABRR(ArrayList<AABRR> sousArbres) {
        AABRR arbre = new AABRR();
        AABRR arbreComplet = null;
        for (AABRR sousArbre: sousArbres){
            arbreComplet = arbre.InsererAABRR(arbreComplet, sousArbre);
        }

        arbreComplet.Parcours("");
        return arbreComplet;
    }



    private int getRandomInt(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
    }
}

