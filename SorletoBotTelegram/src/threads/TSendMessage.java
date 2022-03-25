/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import API.Test;

/**
 *
 * @author sorleto_domenico
 */
public class TSendMessage extends Thread{

    private Test t;

    public TSendMessage(Test t) {
        this.t = t;
    }
}
