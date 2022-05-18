package model.zombier;

import control.Kontroll;
import view.SpillBrett;
import model.zombier.*;
import view.*;
import java.awt.*;

public class SplitterZombie extends Zombie{

    public SplitterZombie(Kontroll k, SpillBrett sb, int y, long hurtighet) {
        super(k, sb, 0, y, hurtighet);
        color = new Color(24,113,180);
        helse = 6;
        hurtighet = 2500;
    }

    @Override
    public void treff(int antSkade){
        helse -= antSkade;

        if (helse < 1){
            sr[rad][kolonne].fjernZombie(this);
            sr[rad+1][kolonne].fjernHitBox(this);
            kontroll.fjernZombie(this);
            kontroll.oekScore(50);

            if (rad > 0){
                Zombie ny = new Zombie(kontroll, spillBrett, rad-1, kolonne, 1500);
                kontroll.leggTilZombie(ny);
            }

            if (rad < 12){
                Zombie ny = new Zombie(kontroll, spillBrett, rad+1, kolonne, 1500);
                kontroll.leggTilZombie(ny);
            }

            if (kolonne > 0){
                Zombie ny = new Zombie(kontroll, spillBrett, rad, kolonne-1, 1500);
                kontroll.leggTilZombie(ny);
            }

            if (kolonne < 4){
                Zombie ny = new Zombie(kontroll, spillBrett, rad, kolonne+1, 1500);
                kontroll.leggTilZombie(ny);
            }

        } else {
            sr[rad][kolonne].oppdaterRute();
        }

        kontroll.oekScore(15);
    }
    
}
