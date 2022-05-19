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
    Vaapen vaapenEn, vaapenTo, vaapenTre, vaapenFire, vaapenFem;
    ArrayList<Zombie> zombier = new ArrayList<>();
    Thread rundeKlokke = null;
    Vaapen[][] vaapenListe = new Vaapen[5][2];

    int vaapenValgt = 1;

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

        vaapenListe [0][0] = new Kanon(0, this, brett);
        vaapenListe [1][0] = new Kanon(1, this, brett);
        vaapenListe [2][0] = new Kanon(2, this, brett);
        vaapenListe [3][0] = new Kanon(3, this, brett);
        vaapenListe [4][0] = new Kanon(4, this, brett);

        vaapenListe [0][1] = new Shotgun(0, this, brett);
        vaapenListe [1][1] = new Shotgun(1, this, brett);
        vaapenListe [2][1] = new Shotgun(2, this, brett);
        vaapenListe [3][1] = new Shotgun(3, this, brett);
        vaapenListe [4][1] = new Shotgun(4, this, brett);

        vaapenEn = vaapenListe[0][0];
        vaapenTo = vaapenListe[1][0];
        vaapenTre = vaapenListe[2][0];
        vaapenFire = vaapenListe[3][0];
        vaapenFem = vaapenListe[4][0];
        nyRunde();
        
    }

    public void skyt(int kanonNummer){
        switch (kanonNummer){
            case 1:
                vaapenEn.skyt();
                break;
            
            case 2:
                vaapenTo.skyt();
                break;

            case 3:
                vaapenTre.skyt();
                break;

            case 4:
                vaapenFire.skyt();
                break;

            case 5:
                vaapenFem.skyt();
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
        vaapenEn = vaapenListe[0][0];
        vaapenTo = vaapenListe[1][0];
        vaapenTre = vaapenListe[2][0];
        vaapenFire = vaapenListe[3][0];
        vaapenFem = vaapenListe[4][0];
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

    public void velgVaapen(int kanonNummer){
        vaapenValgt = kanonNummer;
    }

    public void oppgraderVaapen(){
        switch (vaapenValgt){
            case 1:
                if (penger > vaapenEn.hentKostnadForOppgradering())
                penger -= vaapenEn.hentKostnadForOppgradering();
                vaapenEn.oppgraderVaapen();
                break;
            
            case 2:
                if (penger > vaapenTo.hentKostnadForOppgradering())
                penger -= vaapenTo.hentKostnadForOppgradering();
                vaapenTo.oppgraderVaapen();
                break;

            case 3:
                if (penger > vaapenTre.hentKostnadForOppgradering())
                penger -= vaapenTre.hentKostnadForOppgradering();
                vaapenTre.oppgraderVaapen();
                break;

            case 4:
                if (penger > vaapenFire.hentKostnadForOppgradering())
                penger -= vaapenFire.hentKostnadForOppgradering();
                vaapenFire.oppgraderVaapen();
                break;

            case 5:
                if (penger > vaapenFem.hentKostnadForOppgradering())
                penger -= vaapenFem.hentKostnadForOppgradering(); 
                vaapenFem.oppgraderVaapen();
                break;
        }
    }
    
    public void settVaapen(int vaapenType){
        switch (vaapenValgt){
            case 1:
                vaapenEn = vaapenListe[0][vaapenType];
                brett.oppEn.setText(vaapenEn.toString());
                break;
            
            case 2:
                vaapenTo = vaapenListe[1][vaapenType];
                brett.oppTo.setText(vaapenTo.toString());
                break;

            case 3:
                vaapenTre = vaapenListe[2][vaapenType];
                brett.oppTre.setText(vaapenTre.toString());
                break;

            case 4:
                vaapenFire = vaapenListe[3][vaapenType];
                brett.oppFire.setText(vaapenFire.toString());
                break;

            case 5:
                vaapenFem = vaapenListe[4][vaapenType];
                brett.oppFem.setText(vaapenFem.toString());
                break;
        }
    }

    public void visRundeSkjerm(){
        brett.visRundeSkjerm();
    }

    public boolean sjekkRundeskjermVises(){
        return brett.rundeSkjermVises();
    }
}
