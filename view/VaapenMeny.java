package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import control.Kontroll;

import java.awt.event.*;

public class VaapenMeny implements ActionListener{
    int kanonNummer;
    JFrame hovedSkjerm;
    JPanel vaapenPanel;
    Kontroll kontroll;

    public VaapenMeny(int kanonNummer, JPanel vaapenPanel, Kontroll k, JFrame hovedSkjerm){
        this.kanonNummer = kanonNummer;
        this.vaapenPanel = vaapenPanel; 
        this.hovedSkjerm = hovedSkjerm;
        kontroll = k;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        kontroll.velgVaapen(kanonNummer);
        hovedSkjerm.requestFocusInWindow();
        if (kontroll.sjekkRundeskjermVises()) kontroll.visRundeSkjerm();
        
    }
   
    

}
