package view;

import control.*;

import java.awt.event.*;
import javax.swing.*;

public class VelgVapen implements ActionListener{
    int vaapenType;
    Kontroll kontroll;
    JFrame hovedSkjerm;

    public VelgVapen(int vaapen, Kontroll k, JFrame hovedSkjerm){
        vaapenType = vaapen;
        kontroll = k;
        this.hovedSkjerm = hovedSkjerm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        kontroll.settVaapen(vaapenType);
        hovedSkjerm.requestFocusInWindow();
        if (kontroll.sjekkRundeskjermVises()) kontroll.visRundeSkjerm();
        
    }

    
}
