package model;

import java.awt.*;
import javax.swing.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

import model.zombier.Zombie;

import java.io.File;

import view.SpillBrett;

public class SpilleRute extends JLabel{
    int x;
    int y;
    SpillBrett gui;
    SpilleRute[][] brett;
    Zombie zombie = null;
    Zombie hitBox = null;
    Kule kule = null;

    //BufferedImage img;
    //ImageIcon kuleBilde;
//
    //BufferedImage img2;
    //ImageIcon zombieBilde;

    public SpilleRute(int x, int y, SpillBrett g){
        this.x = x;
        this.y = y;
        gui = g;
        brett = gui.hentSpilleRuter();

        //try {img = ImageIO.read(new File("bilder/kule.png"));} catch (Exception e){System.out.println(e);}
        //kuleBilde = new ImageIcon(img);
//
        //try {img2 = ImageIO.read(new File("bilder/zombie.jpg"));} catch (Exception e){System.out.println(e);}
        //zombieBilde = new ImageIcon(img2);
    }

    public void settKule(Kule k){
        if (zombie != null){
            zombie.treff();
            k.harTruffet();
            System.out.println("Zombie");
        }
        else if (hitBox != null){
            System.out.println("Hitbox");
            hitBox.treff();
            k.harTruffet();
        } else {
            kule = k;
            this.setBackground(Color.YELLOW);
        }
    }

    public void settBlank(){
        this.setText("");
        this.setBackground(Color.WHITE);
        kule = null;
        zombie = null;
    }

    public void settZombie(Zombie z){
        zombie = z;
        if (kule != null){
            zombie.treff();
        }
        if (zombie != null && zombie.hentHelse() > 0){
            this.setBackground(zombie.hentFarge());
            this.setText(""+zombie.hentHelse());
        }
    }

    public Zombie hentZombie(){
        return zombie;
    }

    public void settHitBox(Zombie z){
        hitBox = z;
    }

    public void fjernHitBox(){
        hitBox = null;
    }
    
}
