package model.vaapen;

import control.Kontroll;
import view.SpillBrett;
import model.ammunisjon.*;

public class Shotgun extends Kanon{

    public Shotgun(int pos, Kontroll k, SpillBrett sb) {
        super(pos, k, sb);
        int oppgraderingsKostnad = 750;
    }

    @Override
    public void skyt(){
        int antallKuler = kulerSkutt.size();
        if (antallKuler < 5 && antKuler < maksKuler){
            antKuler++;
            kulerSkutt.add(0,new ShotgunKule(pos, spillBrett, this));
            if (pos-1 >= 0) kulerSkutt.add(0,new ShotgunKule(pos-1, spillBrett, this));
            if (pos+1 < 5) kulerSkutt.add(0,new ShotgunKule(pos+1, spillBrett, this)); 
        }
    }

    @Override
    public String toString(){
        return "Shotgun";
    }
    
}
