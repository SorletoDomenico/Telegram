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
public class TSendMessage extends Thread {

    private Test t;
    private JDatiCondivisi dati;

    public TSendMessage(Test t, JDatiCondivisi dati) {
        this.t = t;
        this.dati = dati;
    }

    public void run() {
        do {
            if (dati.getAlAd().size() > 0) {
                $lat1 = $row["lat"];
            $lon1 = $row["lng"];
            $lat2 = $_GET["lat"];
            $lon2 = $_GET["lng"];
            if ($lat2 != $lat1 || $lon1 != $lon2) {
                $lat11 = deg2rad($lat1);
                $lon11 = deg2rad($lon1);
                $lat2 = deg2rad($lat2);
                $lon2 = deg2rad($lon2);
                $earthRadius = 6371.01; //Kilometers
                $resultKM = $earthRadius * acos(sin($lat11) * sin($lat2) + cos($lat11) * cos($lat2) * cos($lon11 - $lon2));
            } else if ($lat2 == $lat1 && $lon1 == $lon2) {
                $resultKM = 0;
            }
                
                
                
                
                
                dati.getAlAd().remove(0);
            }
        } while (true);
    }
    
    
    private void getCoordinate(){
        
    }
}
