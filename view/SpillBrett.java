package view;

import control.*;
import model.SpilleRute;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

import java.io.File;

public class SpillBrett{
    Kontroll kontroll;
    JButton restart;
    JFrame ramme;
    JPanel spillPanel, topp, panel;
    JLabel spillerStatus, score;
    SpilleRute[][] rutenett;

    //ImageIcon kanonBilde;
    //BufferedImage img;

    public SpillBrett(Kontroll k){
        ramme = new JFrame();
        panel = new JPanel();
        topp = new JPanel();
        spillPanel = new JPanel();
        kontroll = k;

        spillerStatus = new JLabel(""+kontroll.hentHelse());
        spillerStatus.setHorizontalAlignment(JLabel.CENTER);
        spillerStatus.setPreferredSize(new Dimension(500, 40));

        //try {img = ImageIO.read(new File("bilder/kanon.png"));} catch (Exception e){System.out.println(e);}
        //kanonBilde = new ImageIcon(img);

        restart = new JButton("Restart");
        restart.setPreferredSize(new Dimension(150, 40));

        score = new JLabel("Score: 0");
        score.setPreferredSize(new Dimension(150, 40));

        panel.setLayout(new BorderLayout());
        topp.setLayout(new BorderLayout());

        topp.add(score, BorderLayout.EAST);
        topp.add(restart, BorderLayout.WEST);


        spillPanel.setLayout(new GridLayout(30, 5));
        spillPanel.setPreferredSize(new Dimension(500, 1000));
        
        ramme.setFocusable(true);
        ramme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramme.setLocationRelativeTo(null);

        ramme.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent event){
                if (event.getKeyCode() == KeyEvent.VK_1) {
                    kontroll.skyt(1);
                }
            
                else if (event.getKeyCode() == KeyEvent.VK_2) {
                    kontroll.skyt(2);
                }
            
                else if (event.getKeyCode() == KeyEvent.VK_3) {
                    kontroll.skyt(3);
                }
            
                else if (event.getKeyCode() == KeyEvent.VK_4 || event.getKeyCode() == KeyEvent.VK_KP_DOWN) {
                    kontroll.skyt(4);
                }

                else if (event.getKeyCode() == KeyEvent.VK_5) {
                    kontroll.skyt(5);
                }
            }
        
            @Override
            public void keyTyped(KeyEvent e) {       
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

        restart.addActionListener(new RestartKnapp());

        panel.add(spillPanel, BorderLayout.CENTER);
        panel.add(topp, BorderLayout.PAGE_START);
        panel.add(spillerStatus, BorderLayout.PAGE_END);

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
        rutenett = new SpilleRute[30][5];

        for (int i = 0; i < 30; i++){
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

        rutenett[29][0].setBackground(Color.BLACK);
        rutenett[29][1].setBackground(Color.BLACK);
        rutenett[29][2].setBackground(Color.BLACK);
        rutenett[29][3].setBackground(Color.BLACK);
        rutenett[29][4].setBackground(Color.BLACK);

        rutenett[29][0].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        rutenett[29][1].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        rutenett[29][2].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        rutenett[29][3].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        rutenett[29][4].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

    }

    public void oppdaterScore(int nyScore){
        score.setText("Score: "+nyScore);
    }
}