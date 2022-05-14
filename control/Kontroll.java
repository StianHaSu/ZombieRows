package control;

import view.*;
import model.*;
import model.zombier.Zombie;

import java.util.ArrayList;

public class Kontroll {
    SpillBrett brett;
    Kanon kanonEn, kanonTo, kanonTre, kanonFire, kanonFem;
    ArrayList<Zombie> zombier = new ArrayList<>();
    Thread rundeKlokke = null;

    int antZombierPerRunde = 2;
    int antZombier = 2;
    int runde = 1;

    int score = 0;

    int spillerHelse = 5;

    public Kontroll(){
        brett = new SpillBrett(this);
        brett.lagNyttRutenett();
        kanonEn = new Kanon(0, this, brett);
        kanonTo = new Kanon(1, this, brett);
        kanonTre = new Kanon(2, this, brett);
        kanonFire = new Kanon(3, this, brett);
        kanonFem = new Kanon(4, this, brett);
        nyRunde(antZombierPerRunde*runde);
        
    }

    public void skyt(int kanonNummer){
        switch (kanonNummer){
            case 1:
                kanonEn.skyt();
                break;
            
            case 2:
                kanonTo.skyt();
                break;

            case 3:
                kanonTre.skyt();
                break;

            case 4:
                kanonFire.skyt();
                break;

            case 5:
                kanonFem.skyt();
                break;
        }

        System.out.println(antZombier);
    }

    public void fjernZombie(Zombie z){
        zombier.remove(z);
        antZombier--;
        if (antZombier < 1){
            runde++;
            antZombier = antZombierPerRunde*runde;
            nyRunde(antZombier);
        }
    }

    public void nyRunde(int antZombier){
        rundeKlokke = new Thread(new RundeKlokke(this, antZombier));
        rundeKlokke.start();
    }

    public void startNyttSpill(){
        for (Zombie z : zombier){
            z.settDod();
        }
        brett.fjern();
        brett = new SpillBrett(this);
        brett.lagNyttRutenett();
        kanonEn = new Kanon(0, this, brett);
        kanonTo = new Kanon(1, this, brett);
        kanonTre = new Kanon(2, this, brett);
        kanonFire = new Kanon(3, this, brett);
        kanonFem = new Kanon(4, this, brett);
        nyRunde(antZombierPerRunde*runde);
    }

    public void leggTilZombie(Zombie z){
        zombier.add(z);
        antZombier++;
    }

    public SpillBrett hentBrett(){
        return brett;
    }

    public void gjoerSkade(){
        spillerHelse--;
        brett.endreHelseStatus(spillerHelse);
    }

    public int hentHelse(){
        return spillerHelse;
    }

    public void oekScore(int poeng){
        score += poeng;
        brett.oppdaterScore(score);
    }
}
