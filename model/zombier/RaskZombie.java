package model.zombier;

import view.*;
import control.*;
import java.awt.*;

public class RaskZombie extends Zombie{

    public RaskZombie(Kontroll k, SpillBrett sb, int y, long hurtighet){
        super(k, sb, 0, y, hurtighet);
        helse = 1;
        antSkade = 1;
        color = new Color(117,18,92);
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

        kontroll.oekScore(25);
    }
}
