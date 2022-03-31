/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import API.Test;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import sorletobottelegram.CSV;
import sorletobottelegram.JDatiCondivisi;
import sorletobottelegram.JPersona;

/**
 *
 * @author sorleto_domenico
 */
public class TSendMessage extends Thread {

    private Test t;
    private JDatiCondivisi dati;
    private CSV csv;

    public TSendMessage(Test t, JDatiCondivisi dati) throws IOException {
        this.t = t;
        this.dati = dati;
        csv = new CSV("listaUtenti.csv");
    }

    public void run() {
        do {
            if (dati.getAlAd().size() > 0) {
                try {
                    Double latA, lonA;
                    //coordinate della cittá inserita
                    Double[] c = getCoordinate(dati.getAlAd().get(0).citta);
                    latA = c[0];
                    lonA = c[1];
                    //coordinate dei file csv
                    ArrayList<Integer> listaID = getUtentiVicini(latA, lonA, dati.getAlAd().get(0).distanza);
                    for(Integer id: listaID){
                        t.sendMessage(id, dati.getAlAd().get(0).text);
                    }
                    dati.getAlAd().remove(0);
                } catch (IOException ex) {
                    Logger.getLogger(TSendMessage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (true);
    }

    private ArrayList<Integer> getUtentiVicini(Double latA, Double lonA, Integer raggio) throws IOException {
        ArrayList<Integer> id = new ArrayList<Integer>();
        ArrayList<JPersona> persone = csv.getAllCitta();
        Double latB, lonB; //citta inserita da java
        latA = Math.toRadians(latA);
        lonA = Math.toRadians(lonA);
        for (JPersona persona : persone) { //scorro tutte le cittá del file
            Double[] c = getCoordinate(persona.citta);
            
            latB = Math.toRadians(c[0]);
            lonB = Math.toRadians(c[1]);
            
            double dlon = lonB - lonA;
            double dlat = latB - latA;
            double a = Math.pow(Math.sin(dlat / 2), 2)
                    + Math.cos(latA) * Math.cos(latB)
                    * Math.pow(Math.sin(dlon / 2), 2);

            double ca = 2 * Math.asin(Math.sqrt(a));
            double ris = ca * 6371;
            if(ris <= raggio){
                id.add(persona.chat_id);
            }
        }
        return id;
    }

    private Double[] getCoordinate(String citta) throws MalformedURLException, IOException {
        Double[] coordinate = new Double[2];//posizione 0-> latitudine | posizione 1-> longitudine
        URL fileUrl = new URL("https://nominatim.openstreetmap.org/search?q=" + URLEncoder.encode(citta, StandardCharsets.UTF_8) + "&format=xml&addressdetails=1");
        Scanner inRemote = new Scanner(fileUrl.openStream());
        inRemote.useDelimiter("\u001a");
        String content = inRemote.next();

        Document doc = convertStringToXMLDocument(content);
        NodeList listPlace = doc.getElementsByTagName("place");
        if (listPlace.getLength() > 0) {
            Element e = (Element) listPlace.item(0);
            if (e.hasAttribute("lat") && e.hasAttribute("lon")) {
                coordinate[0] = Double.parseDouble(e.getAttribute("lat"));
                coordinate[1] = Double.parseDouble(e.getAttribute("lon"));
            } else {
                coordinate = null;
            }
        } else {
            coordinate = null;
        }
        return coordinate;
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
