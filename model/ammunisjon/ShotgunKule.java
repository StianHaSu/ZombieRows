package model.ammunisjon;

import model.vaapen.Kanon;
import view.SpillBrett;
import java.awt.*;

public class ShotgunKule extends Kule{
    public ShotgunKule(int pos, SpillBrett sb, Kanon k) {
        super(pos, sb, k);
        color = new Color(127,96,0);
        hurtighet = 5;
    }

    @Override
    public boolean beveg(){
        int x1 = rad;
        
        if (rad > 10)rad--;
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

    @Override
    public long hentHurtighet(){
        hurtighet += 4;
        return hurtighet;
    }
    
}
