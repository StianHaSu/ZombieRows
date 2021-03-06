package model.zombier;

import java.awt.*;

import control.*;
import model.SpilleRute;
import view.*;

public class Zombie {
    protected int rad;
    protected int kolonne;

    protected int helse = 2;
    protected int antSkade = 1;
    protected long hurtighet;

    protected Kontroll kontroll;
    protected SpillBrett spillBrett;
    protected SpilleRute[][] sr;
    protected Thread klokke;

    protected Color color = new Color(106,168,79);

    public Zombie(Kontroll k, SpillBrett sb, int x, int y, long hurtighet){
        kontroll = k;
        spillBrett = sb;
        rad = x;
        kolonne = y;

        this.hurtighet = hurtighet;

        sr = spillBrett.hentSpilleRuter();
        sr[rad][kolonne].settZombie(this);
        sr[rad+1][kolonne].settHitBox(this);
        klokke = new Thread(new ZombieKlokke(this, hurtighet));
        klokke.start();
    }

    public boolean beveg(){
        int x1 = rad;
        
        if (rad < 28 && helse > 0)rad++;
        else {
            if (rad >= 28) kontroll.gjoerSkade();
            
            sr[x1][kolonne].fjernZombie(this);
            kontroll.fjernZombie(this);

            return false;
        }
        
        int x2 = rad;

        sr[x2][kolonne].settZombie(this);
        if (rad < 28) sr[x2+1][kolonne].settHitBox(this);
        sr[x2][kolonne].fjernHitBox(this);
        sr[x1][kolonne].fjernZombie(this);
        
        return true;
    }

    public void treff(int antSkade){
        helse -= antSkade;
        if (helse < 1){
            sr[rad][kolonne].fjernZombie(this);
            sr[rad+1][kolonne].fjernHitBox(this);
            kontroll.fjernZombie(this);
            kontroll.oekScore(50);
        }

        else {
            sr[rad][kolonne].oppdaterRute();
        }

        kontroll.oekScore(15);
    }

    public int hentHelse(){
        return helse;
    }

    public void settDod(){
        helse = 0;
    }

    public Color hentFarge(){
        return color;
    }
    
}
