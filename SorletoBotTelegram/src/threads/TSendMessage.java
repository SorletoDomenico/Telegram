/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import API.Test;
import sorletobottelegram.JDatiCondivisi;

/**
 *
 * @author sorleto_domenico
 */
public class TSendMessage extends Thread{

    private Test t;
    private JDatiCondivisi dati;

    public TSendMessage(Test t, JDatiCondivisi dati) {
        this.t = t;
        this.dati = dati;
    }
}
