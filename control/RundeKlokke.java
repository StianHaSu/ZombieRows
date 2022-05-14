package control;

import model.*;
import model.zombier.*;

public class RundeKlokke implements Runnable {
    Kontroll kontroll;
    int antallZombier;
    
    public RundeKlokke(Kontroll k, int antZom){
        kontroll = k;
        antallZombier = antZom;
    }

    @Override
    public void run(){
        for (int i = 0; i < antallZombier; i++){
            int kolonne = (int)(Math.random()*(5-1-0+1))+0;
            Zombie ny = new Zombie(kontroll, kontroll.hentBrett(), kolonne, 1500);
            kontroll.leggTilZombie(ny);
            long tid = (long)(Math.random()*(6000-4000-0+1))+0;
            try{Thread.sleep(tid);} catch (Exception e){System.out.println(e);}
        }
        for (int e = 0; e < (int) antallZombier/3; e++){
            int kolonne = (int)(Math.random()*(5-1-0+1))+0;
            Zombie nyRask1 = new RaskZombie(kontroll, kontroll.hentBrett(), kolonne, 200);
            kontroll.leggTilZombie(nyRask1);

        }
    }
}
