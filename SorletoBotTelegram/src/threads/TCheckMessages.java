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
public class TCheckMessages extends Thread {

    private Test t;
    private JDatiCondivisi dati;

    public TCheckMessages(Test t, JDatiCondivisi dati) {
        this.t = t;
        this.dati = dati;
    }

    public void run() {

        try {
            do {
                if (dati.getAlM().size() > 0) {
                    Messaggio m = dati.getAlM().get(0);
                    if (m.text.equalsIgnoreCase("ciao")) {
                        t.sendMessage(m.chat.id, "ACA");

                    }
                    dati.getAlM().remove(0);
                }
            } while (true);
        } catch (IOException ex) {
            Logger.getLogger(TCheckMessages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
