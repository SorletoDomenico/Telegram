/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import API.Messaggio;
import API.Test;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import sorletobottelegram.CSV;
import sorletobottelegram.JDatiCondivisi;
import sorletobottelegram.JPersona;

/**
 *
 * @author sorleto_domenico
 */
public class TCheckMessages extends Thread {

    private Test t;
    private JDatiCondivisi dati;
    private CSV csv;

    public TCheckMessages(Test t, JDatiCondivisi dati) throws IOException {
        this.t = t;
        this.dati = dati;
        csv = new CSV("listaUtenti.csv");
    }

    public void run() {

        do {
            if (dati.getAlM().size() > 0) {
                Messaggio m = dati.getAlM().get(0);
                String[] messaggio = m.text.split(" ", 2);
                String comando = messaggio[0];
                if (comando.equalsIgnoreCase("/citta")) {
                    try {
                        if (esiste(messaggio[1])) {
                            csv.addPersona(new JPersona(m.chat.id, messaggio[1]));
                            t.sendMessage(m.chat.id, "Aggiunto");
                        } else {
                            t.sendMessage(m.chat.id, "Errore");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(TCheckMessages.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                dati.getAlM().remove(0);
            }
        } while (true);
    }

    private Boolean esiste(String citta) throws IOException {
        URL fileUrl = new URL("https://nominatim.openstreetmap.org/search?q=" + URLEncoder.encode(citta, StandardCharsets.UTF_8) + "&format=xml&addressdetails=1");
        Scanner inRemote = new Scanner(fileUrl.openStream());
        inRemote.useDelimiter("\u001a");
        String content = inRemote.next();

        Document doc = convertStringToXMLDocument(content);
        NodeList listPlace = doc.getElementsByTagName("place");
        if (listPlace.getLength() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private Document convertStringToXMLDocument(String xmlString) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
