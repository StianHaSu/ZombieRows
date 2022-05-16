package model;

import model.ammunisjon.Kule;

public class Klokke implements Runnable{
    Kule kulen;
    
    public Klokke(Kule k){
        kulen = k;
    }

    @Override
    public void run() {
        boolean ikkeEnden = true;
        while (ikkeEnden && !kulen.sjekkTreff()){
            try {Thread.sleep(kulen.hentHurtighet());} catch (Exception e){System.out.println(e);}
            ikkeEnden = kulen.beveg();
        }
    }
    
}
