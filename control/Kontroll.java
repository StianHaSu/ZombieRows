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

    int antZombierPerRunde = 5;
    int antZombier = 5;
    int runde = 1;

    int score = 0;
    int penger = 0;

    int spillerHelse = 5;

    public Kontroll(){
        brett = new SpillBrett(this);
        brett.lagNyttRutenett();
        kanonEn = new Kanon(0, this, brett);
        kanonTo = new Shotgun(1, this, brett);
        kanonTre = new Shotgun(2, this, brett);
        kanonFire = new Shotgun(3, this, brett);
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
        kanonTre = new Shotgun(2, this, brett);
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
        penger += poeng;
        brett.oppdaterScore(score);
    }

    public void oppgraderVaapen(int kanonNummer){
        switch (kanonNummer){
            case 1:
                if (penger > kanonEn.hentSkade()*500)
                kanonEn.oppgraderVaapen();
                penger -= kanonEn.hentSkade()*500;
                break;
            
            case 2:
                if (penger > kanonTo.hentSkade()*500)
                kanonTo.oppgraderVaapen();
                penger -= kanonTo.hentSkade()*500;
                break;

            case 3:
                if (penger > kanonTre.hentSkade()*500)
                kanonTre.oppgraderVaapen();
                penger -= kanonTre.hentSkade()*500;
                break;

            case 4:
                if (penger > kanonFire.hentSkade()*500)
                kanonFire.oppgraderVaapen();
                penger -= kanonFire.hentSkade()*500;
                break;

            case 5:
                if (penger > kanonFem.hentSkade()*500)
                kanonFem.oppgraderVaapen();
                penger -= kanonFem.hentSkade()*500; 
                break;
        }
    }
}
