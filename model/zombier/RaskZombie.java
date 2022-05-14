package model.zombier;

import view.*;
import control.*;
import java.awt.*;

public class RaskZombie extends Zombie{

    public RaskZombie(Kontroll k, SpillBrett sb, int y, long hurtighet){
        super(k, sb, y, hurtighet);
        helse = 1;
        antSkade = 1;
        color = new Color(117,18,92);
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

        kontroll.oekScore(25);
    }
}
