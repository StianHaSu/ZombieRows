package model.zombier;

import control.*;
import view.*;

import java.awt.*;

public class KraftigZombie extends Zombie{
    
    public KraftigZombie(Kontroll k, SpillBrett sb, int y, long hurtighet){
        super(k, sb, y, hurtighet);
        helse = 10;
        color = new Color(196,52,26);
    }

    @Override
    public void treff(){
        helse--;

        if (helse < 1){
            sr[rad][kolonne].settBlank();
            sr[rad+1][kolonne].fjernHitBox();
            kontroll.fjernZombie(this);
            kontroll.oekScore(5);
        }

        kontroll.oekScore(100);
    }
}
