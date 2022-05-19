package view;

import control.*;
import model.SpilleRute;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class SpillBrett{
    Kontroll kontroll;
    public JButton restart, oppEn, oppTo, oppTre, oppFire, oppFem, nesteRunde, kanonKnapp, shotgunKnapp, oppgradering;
    JFrame ramme, rundeSkjerm, vaapenSkjerm;
    JPanel spillPanel, topp, panel, underPanel, underKnapper, rundePanel, vaapenPanel, zombiePanel, underPaddingEn, underPaddingTo;
    JLabel spillerStatus, score, penger, rundeInfo;
    SpilleRute[][] rutenett;

    boolean rundeVises = false;

    public SpillBrett(Kontroll k){
        ramme = new JFrame();
        panel = new JPanel();
        topp = new JPanel();
        spillPanel = new JPanel();
        rundeSkjerm = new JFrame();
        rundePanel = new JPanel();
        vaapenSkjerm = new JFrame();
        vaapenPanel = new JPanel();
        zombiePanel = new JPanel();
        kontroll = k;

        ramme.setFocusable(true);

        underPanel = new JPanel();
        underKnapper = new JPanel();

        underPanel.setLayout(new BorderLayout());
        underKnapper.setLayout(new GridLayout(1, 7));

        oppEn = new JButton("Kanon");
        oppTo = new JButton("Kanon");
        oppTre = new JButton("Kanon");
        oppFire = new JButton("Kano");
        oppFem = new JButton("Kanon");

        oppgradering = new JButton("Oppgrader");

        kanonKnapp = new JButton("Kanon");
        kanonKnapp.setHorizontalAlignment(JButton.CENTER);
        kanonKnapp.setPreferredSize(new Dimension(120, 40));
        kanonKnapp.addActionListener(new VelgVapen(0, kontroll, ramme));

        shotgunKnapp = new JButton("Shotgun");
        shotgunKnapp.setHorizontalAlignment(JButton.CENTER);
        shotgunKnapp.setPreferredSize(new Dimension(120, 40));
        shotgunKnapp.addActionListener(new VelgVapen(1, kontroll, ramme));

        spillerStatus = new JLabel(""+kontroll.hentHelse());
        spillerStatus.setHorizontalAlignment(JLabel.CENTER);
        spillerStatus.setPreferredSize(new Dimension(500, 40));

        penger = new JLabel("Penger: "+ kontroll.hentPenger());
        penger.setHorizontalAlignment(JLabel.CENTER);

        restart = new JButton("Restart");
        restart.setPreferredSize(new Dimension(150, 40));

        score = new JLabel("Score: 0");
        score.setPreferredSize(new Dimension(150, 40));

        panel.setLayout(new BorderLayout());
        topp.setLayout(new BorderLayout());

        topp.add(score, BorderLayout.EAST);
        topp.add(penger, BorderLayout.CENTER);
        topp.add(restart, BorderLayout.WEST);


        spillPanel.setLayout(new GridLayout(30, 5));
        spillPanel.setPreferredSize(new Dimension(700, 1000));
        
        ramme.setFocusable(true);
        ramme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramme.setLocationRelativeTo(null);

        //vaapenSkjerm.setDefaultCloseOperation();

        ramme.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent event){
                if (event.getKeyCode() == KeyEvent.VK_1) {
                    kontroll.skyt(1);
                    kontroll.velgVaapen(1);
                }
            
                else if (event.getKeyCode() == KeyEvent.VK_2) {
                    kontroll.skyt(2);
                    kontroll.velgVaapen(2);
                }
            
                else if (event.getKeyCode() == KeyEvent.VK_3) {
                    kontroll.skyt(3);
                    kontroll.velgVaapen(3);
                }
            
                else if (event.getKeyCode() == KeyEvent.VK_4 || event.getKeyCode() == KeyEvent.VK_KP_DOWN) {
                    kontroll.skyt(4);
                    kontroll.velgVaapen(4);
                }

                else if (event.getKeyCode() == KeyEvent.VK_5) {
                    kontroll.skyt(5);
                    kontroll.velgVaapen(5);
                }

                else if (event.getKeyCode() == KeyEvent.VK_UP){
                    kontroll.settVaapen(1);
                }

                else if (event.getKeyCode() == KeyEvent.VK_DOWN){
                    kontroll.settVaapen(0);
                }
            }
        
            @Override
            public void keyTyped(KeyEvent e) {      
                if (e.getKeyCode() == KeyEvent.VK_S){
                    kontroll.velgVaapen(2);
                }

            }
        
            @Override
            public void keyReleased(KeyEvent e) {   
        }});

        class RestartKnapp implements ActionListener{

            @Override 
            public void actionPerformed(ActionEvent ae){
                kontroll.startNyttSpill();
                ramme.requestFocusInWindow();
            }
        } 
        
        class oppgraderKnapp implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent ae){
                kontroll.oppgraderVaapen();
                ramme.requestFocusInWindow();
                if (rundeVises) visRundeSkjerm();
            }
        }

        class nyRundeKnapp implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent ae){
                kontroll.nyRunde();

            }
        }


        nesteRunde = new JButton("Start neste Runde");
        nesteRunde.addActionListener(new nyRundeKnapp());

        rundeInfo = new JLabel("Du har fullfoer runde: "+kontroll.hentRunde());
        rundeInfo.setHorizontalAlignment(JLabel.CENTER);
        rundeInfo.setVerticalAlignment(JLabel.CENTER);

        rundePanel.setPreferredSize(rundeSkjerm.getSize());
        rundePanel.setLayout(new BorderLayout());

        nesteRunde.setPreferredSize(new Dimension(500, 60));
        nesteRunde.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        rundePanel.add(rundeInfo, BorderLayout.NORTH);
        rundePanel.add(nesteRunde, BorderLayout.SOUTH);

        restart.addActionListener(new RestartKnapp());

        oppEn.addActionListener(new VaapenMeny(1, vaapenPanel, kontroll, ramme));
        oppTo.addActionListener(new VaapenMeny(2, vaapenPanel, kontroll, ramme));
        oppTre.addActionListener(new VaapenMeny(3, vaapenPanel, kontroll, ramme));
        oppFire.addActionListener(new VaapenMeny(4, vaapenPanel, kontroll, ramme));
        oppFem.addActionListener(new VaapenMeny(5, vaapenPanel, kontroll, ramme));

        underPaddingEn = new JPanel();
        underPaddingEn.setPreferredSize(new Dimension(100, 40));

        underPaddingTo = new JPanel();
        underPaddingTo.setPreferredSize(new Dimension(100, 40));

        oppEn.setPreferredSize(new Dimension(100, 40));
        oppTo.setPreferredSize(new Dimension(100, 40));
        oppTre.setPreferredSize(new Dimension(100, 40));
        oppFire.setPreferredSize(new Dimension(100, 40));
        oppFem.setPreferredSize(new Dimension(100, 40));
        oppgradering.addActionListener(new oppgraderKnapp());
        
        vaapenPanel.setLayout(new GridLayout(3,1));
        vaapenPanel.add(kanonKnapp);
        vaapenPanel.add(shotgunKnapp);
        vaapenPanel.add(oppgradering);
        vaapenPanel.setPreferredSize(new Dimension(100, 1000));

        zombiePanel.setPreferredSize(new Dimension(100, 1000));

        underPanel.add(spillerStatus, BorderLayout.SOUTH);
        underPanel.add(underPaddingEn, BorderLayout.EAST);
        underPanel.add(underPaddingTo, BorderLayout.WEST);

        panel.add(zombiePanel, BorderLayout.EAST);
        panel.add(spillPanel, BorderLayout.CENTER);
        panel.add(vaapenPanel, BorderLayout.WEST);
        panel.add(topp, BorderLayout.PAGE_START);
        panel.add(underPanel, BorderLayout.PAGE_END);

        ramme.requestFocusInWindow();

        ramme.add(panel);
        ramme.pack();
        ramme.setVisible(true);
    }

    public SpilleRute[][] hentSpilleRuter(){
        return rutenett;
    }

    public void endreHelseStatus(int i){
        spillerStatus.setText(""+i);
    }

    public void fjern(){
        ramme.setVisible(false);
        ramme.dispose();
    }

    public void lagNyttRutenett(){
        rutenett = new SpilleRute[29][5];

        for (int i = 0; i < 29; i++){
            for (int e = 0; e < 5; e++){
                SpilleRute ny = new SpilleRute(i, e, this);
                ny.setHorizontalAlignment(JLabel.CENTER);
                ny.setVerticalAlignment(JLabel.CENTER);
                ny.setOpaque(true);
                ny.setPreferredSize(new Dimension(40, 80));
                ny.setBackground(Color.WHITE);

                spillPanel.add(ny);
                rutenett[i][e] = ny;

            }
        }

        spillPanel.add(oppEn);
        spillPanel.add(oppTo);
        spillPanel.add(oppTre);
        spillPanel.add(oppFire);
        spillPanel.add(oppFem);
        
    }

    public void oppdaterScore(int nyScore){
        score.setText("Score: "+nyScore);
    }

    public void oppdaterPenger(int antPenger){
        penger.setText("Penger: "+antPenger);
    }

    public void visRundeSkjerm(){
        rundeVises = true;
        rundeSkjerm.setPreferredSize(new Dimension((int) (ramme.getWidth()*0.7), (int) (ramme.getHeight()*0.3)));
        rundeInfo.setText("Du har fullfoert runde: "+kontroll.hentRunde());
        rundeSkjerm.add(rundePanel);
        rundeSkjerm.pack();
        rundeSkjerm.setLocationRelativeTo(ramme);
        rundeSkjerm.setVisible(true);
        
    }

    public void fjernRundeSkjerm(){
        rundeSkjerm.setVisible(false);
        rundeSkjerm.dispose();
        rundeVises = false;

    }

    public boolean rundeSkjermVises(){
        return rundeVises;
    }

}