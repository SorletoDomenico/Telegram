/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorletobottelegram;

import API.Messaggio;
import java.util.ArrayList;

/**
 *
 * @author sorleto_domenico
 */
public class JDatiCondivisi {

    private ArrayList<Messaggio> alM;
    private ArrayList<JAd> alAd;

    public JDatiCondivisi() {
        alM = new ArrayList<Messaggio>();
    }

    public ArrayList<Messaggio> getAlM() {
        synchronized (alM) {
            return alM;

        }
    }

    public ArrayList<JAd> getAlAd() {
        synchronized (alAd) {
            return alAd;
        }
    }
}
