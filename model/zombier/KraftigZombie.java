package model.zombier;

import control.*;
import view.*;

import java.awt.*;

public class KraftigZombie extends Zombie{
    
    public KraftigZombie(Kontroll k, SpillBrett sb, int y, long hurtighet){
        super(k, sb,0, y, hurtighet);
        helse = 10;
        color = new Color(196,52,26);
        hurtighet = 3000;
    }

    @Override
    public void treff(int antSkade){
        helse -= antSkade;

        if (helse < 1){
            sr[rad][kolonne].fjernZombie(this);
            sr[rad+1][kolonne].fjernHitBox(this);
            kontroll.fjernZombie(this);
            kontroll.oekScore(5);
        } else {
            sr[rad][kolonne].oppdaterRute();
        }

        kontroll.oekScore(100);
    }
}
