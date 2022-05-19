package model.vaapen;

import java.util.ArrayList;

import control.*;
import model.ammunisjon.*;
import view.*;

public class Kanon implements Vaapen{
    int pos;
    int helse = 10;
    int skade = 1;

    int oppgraderingsKostnad = 500;

    static int maksKuler = 8;
    static int antKuler = 0;

    Kontroll kontroll;
    SpillBrett spillBrett;
    ArrayList<Kule> kulerSkutt = new ArrayList<>();

    public Kanon(int pos, Kontroll k, SpillBrett sb){
        this.pos = pos;
        kontroll = k;
        spillBrett = sb;
    }

    public void skyt(){
        int antallKuler = kulerSkutt.size();
        if (antallKuler < 5 && antKuler < maksKuler){
            antKuler++;
            kulerSkutt.add(0,new Kule(pos, spillBrett, this)); 
        }
    }

    public void fjernKule(Kule k){
        antKuler--;
        kulerSkutt.remove(k);
    }

    public void oppgraderVaapen(){
        skade++;
        oppgraderingsKostnad += 500;
        spillBrett.oppdaterPenger(kontroll.hentPenger());
    }

    public int hentSkade(){
        return skade;
    }

    public int hentKostnadForOppgradering(){
        return oppgraderingsKostnad;
    }

    @Override
    public String toString(){
        return "Kanon";
    }

}
