/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sorletobottelegram;

import API.Test;
import java.io.IOException;

/**
 *
 * @author Domen
 */
public class SorletoBotTelegram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        final String baseUrl = "https://api.telegram.org/bot";
        final String token = "5198086511:AAEbM5tKRttQfpjyDZPmnEIXb0oogTf9Cxk/";
        Test test = new Test();

        test.sendMessage(452619758, "Ciao");
        test.getUpdates("https://api.telegram.org/bot5198086511:AAEbM5tKRttQfpjyDZPmnEIXb0oogTf9Cxk/getUpdates");

        //https://nominatim.openstreetmap.org/search?q=mariano+comense,+monnet&format=xml&addressdetails=1

        //https://nominatim.openstreetmap.org/search?q=
    }
}
