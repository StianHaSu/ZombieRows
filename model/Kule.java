package model;
import java.util.ArrayList;

import view.*;
import control.*;

public class Kule {
    int rad;
    int kolonne;
    
    SpillBrett spillBrett;
    SpilleRute[][] sr;
    Kanon kanon;
    boolean harTruffet = false;
    
    Thread kulensKlokke;
    
    public Kule(int pos, SpillBrett sb, Kanon k){
        kanon = k;

        kolonne = pos;
        rad = 28;
        spillBrett = sb;
        sr = spillBrett.hentSpilleRuter();

        sr[rad][kolonne].settKule(this);
        kulensKlokke = new Thread(new Klokke(this));
        kulensKlokke.start();

    }

    public boolean beveg(){
        int x1 = rad;
        
        if (rad > 0)rad--;
        else {
            sr[x1][kolonne].settBlank();
            kanon.fjernKule(this);
            return false;
        }
        
        int x2 = rad;

        sr[x2][kolonne].settKule(this);
        sr[x1][kolonne].settBlank();

        return true;
    }

    public void harTruffet(){
        kanon.fjernKule(this);
        sr[rad][kolonne].settBlank();
        harTruffet = true;
    }

    public boolean sjekkTreff(){
        return harTruffet;
    }

}
