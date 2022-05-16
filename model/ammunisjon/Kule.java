package model.ammunisjon;
import java.util.ArrayList;

import view.*;
import control.*;
import model.Klokke;
import model.SpilleRute;
import model.vaapen.*;
import java.awt.*;

public class Kule {
    int rad;
    int kolonne;
    
    SpillBrett spillBrett;
    SpilleRute[][] sr;
    Vaapen vaapen;
    boolean harTruffet = false;
    long hurtighet = 50;

    Color color = new Color(255,217,102);
    Thread kulensKlokke;
    
    public Kule(int pos, SpillBrett sb, Vaapen k){
        vaapen = k;

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
            vaapen.fjernKule(this);
            return false;
        }
        
        int x2 = rad;

        sr[x2][kolonne].settKule(this);
        sr[x1][kolonne].settBlank();

        return true;
    }

    public void harTruffet(){
        vaapen.fjernKule(this);
        sr[rad][kolonne].settBlank();
        harTruffet = true;
    }

    public boolean sjekkTreff(){
        return harTruffet;
    }

    public int hentSkade(){
        return vaapen.hentSkade();
    }

    public Color hentFarge(){
        return color;
    }

    public long hentHurtighet(){
        return hurtighet;
    }

}
