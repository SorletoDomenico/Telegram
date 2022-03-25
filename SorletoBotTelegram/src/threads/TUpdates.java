/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import API.Test;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sorleto_domenico
 */
public class TUpdates extends Thread {

    private Test t;

    public TUpdates(Test t) {
        this.t = t;
    }

    public void run() {
        do {
            try {
                t.getUpdates();
            } catch (IOException ex) {
                Logger.getLogger(TUpdates.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }
}
