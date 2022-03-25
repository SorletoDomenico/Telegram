/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import API.Messaggio;
import API.Test;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sorletobottelegram.JDatiCondivisi;

/**
 *
 * @author sorleto_domenico
 */
public class TUpdates extends Thread {

    private Test t;
    private JDatiCondivisi dati;

    public TUpdates(Test t, JDatiCondivisi dati) {
        this.t = t;
        this.dati = dati;
    }

    public void run() {
        do {
            try {
                Messaggio m = t.getUpdates();
                if (m != null) {
                    dati.getAlM().add(m);
                }
            } catch (IOException ex) {
                Logger.getLogger(TUpdates.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }
}
