package model;

import java.awt.*;
import javax.swing.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

import model.ammunisjon.Kule;
import model.zombier.Zombie;

import java.awt.*;

import java.io.File;
import java.util.LinkedList;

import view.SpillBrett;

public class SpilleRute extends JLabel{
    int x;
    int y;
    SpillBrett gui;
    SpilleRute[][] brett;
    LinkedList<Zombie> zombie = new LinkedList<>();
    LinkedList<Zombie> hitBox = new LinkedList<>();
    Kule kule = null;

    public SpilleRute(int x, int y, SpillBrett g){
        this.x = x;
        this.y = y;
        gui = g;
        brett = gui.hentSpilleRuter();
    }

    public void settKule(Kule k){
        Zombie hb = null;
        Zombie zmb = null;
        if (hitBox.size() > 0 ){
            try{hb = hitBox.getFirst();} catch (Exception e){
                return;
            }
        }
        if (zombie.size() > 0 ){
            try{zmb = zombie.getFirst();} catch (Exception e2){
                return;
            }
        }

        if (hb != null) {
            hb.treff(k.hentSkade());
            k.harTruffet();
        } else if (zmb != null){
            zmb.treff(k.hentSkade());
            k.harTruffet();
        }
        
        else if (hitBox.size() > 0 && hitBox.getFirst() != null){
            hitBox.getFirst().treff(k.hentSkade());
            k.harTruffet();
        } else {
            kule = k;
            this.setBackground(k.hentFarge());
        }
    }

    public void fjernZombie(Zombie z){
        zombie.remove(z);
        if (zombie.size()<1){
            this.setText("");
            this.setBackground(Color.WHITE);
        }
    }

    public void settZombie(Zombie z){
        Zombie nyZombie = z;
        if (kule != null){
            kule.harTruffet();
            nyZombie.treff(kule.hentSkade());
            kule = null;
        }
        if (nyZombie.hentHelse() > 0){
            zombie.addLast(nyZombie);
            try{
                this.setBackground(zombie.getFirst().hentFarge());
                this.setText(""+zombie.getFirst().hentHelse());
            } catch (Exception e){
                System.out.println("storrelse: "+zombie.size());
                return;
            }
        }
    }

    public void settBlank(){
        this.setText("");
        this.setBackground(Color.WHITE);
        kule = null;
    }

    public Zombie hentZombie(){
        return zombie.getFirst();
    }

    public void settHitBox(Zombie z){
        hitBox.addLast(z);
    }

    public void fjernHitBox(Zombie z){
        hitBox.remove(z);
    }

    public void oppdaterRute(){
        Zombie zmb = null;
        if (zombie.size() > 0){zmb = zombie.getFirst();}
        if (zmb != null){
            this.setBackground(zmb.hentFarge());
            this.setText(""+zmb.hentHelse());
        }

    }
    
}
