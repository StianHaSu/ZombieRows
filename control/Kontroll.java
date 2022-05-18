package control;

import view.*;
import model.*;
import model.vaapen.Kanon;
import model.vaapen.Shotgun;
import model.zombier.Zombie;
import model.vaapen.*;

import java.util.ArrayList;

public class Kontroll {
    SpillBrett brett;
    Vaapen kanonEn, kanonTo, kanonTre, kanonFire, kanonFem;
    ArrayList<Zombie> zombier = new ArrayList<>();
    Thread rundeKlokke = null;

    boolean ferdigProdusere = false;

    int antZombierPerRunde = 5;
    int antZombier = 5;
    int runde = 0;

    int score = 0;
    int penger = 0;

    int spillerHelse = 5;

    public Kontroll(){
        brett = new SpillBrett(this);
        brett.lagNyttRutenett();
        kanonEn = new Kanon(0, this, brett);
        kanonTo = new Kanon(1, this, brett);
        kanonTre = new Kanon(2, this, brett);
        kanonFire = new Kanon(3, this, brett);
        kanonFem = new Kanon(4, this, brett);
        nyRunde();
        
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
        if (zombier.size() < 1 && ferdigProdusere){
            brett.visRundeSkjerm();
        }
    }

    public void nyRunde(){
        runde++;
        ferdigProdusere = false;
        brett.fjernRundeSkjerm();
        rundeKlokke = new Thread(new RundeKlokke(this, antZombierPerRunde*runde));
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
        kanonTre = new Shotgun(2, this, brett);
        kanonFire = new Kanon(3, this, brett);
        kanonFem = new Kanon(4, this, brett);
        nyRunde();
    }

    public void leggTilZombie(Zombie z){
        zombier.add(z);
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
        penger += poeng;
        brett.oppdaterScore(score);
        brett.oppdaterPenger(penger);
    }

    public int hentPenger(){
        return penger;
    }

    public int hentRunde(){
        return runde;
    }

    public void ferdigProdusering(){
        ferdigProdusere = true;
    }

    public void oppgraderVaapen(int kanonNummer){
        switch (kanonNummer){
            case 1:
                if (penger > kanonEn.hentKostnadForOppgradering())
                penger -= kanonFire.hentKostnadForOppgradering();
                kanonEn.oppgraderVaapen();
                break;
            
            case 2:
                if (penger > kanonTo.hentKostnadForOppgradering())
                penger -= kanonFire.hentKostnadForOppgradering();
                kanonTo.oppgraderVaapen();
                break;

            case 3:
                if (penger > kanonTre.hentKostnadForOppgradering())
                penger -= kanonFire.hentKostnadForOppgradering();
                kanonTre.oppgraderVaapen();
                break;

            case 4:
                if (penger > kanonFire.hentKostnadForOppgradering())
                penger -= kanonFire.hentKostnadForOppgradering();
                kanonFire.oppgraderVaapen();
                break;

            case 5:
                if (penger > kanonFem.hentKostnadForOppgradering())
                penger -= kanonFem.hentKostnadForOppgradering(); 
                kanonFem.oppgraderVaapen();
                break;
        }
    }
}
