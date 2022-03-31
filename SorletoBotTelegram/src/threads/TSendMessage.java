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

/**
 *
 * @author sorleto_domenico
 */
public class TSendMessage extends Thread {

    private Test t;
    private JDatiCondivisi dati;
    private CSV csv;

    public TSendMessage(Test t, JDatiCondivisi dati) {
        this.t = t;
        this.dati = dati;
    }

    public void run() {
        do {
            if (dati.getAlAd().size() > 0) {
                try {
                    Float latA, lonA;
                    //coordinate della cittá inserita
                    Float[] c = getCoordinate(dati.getAlAd().get(0).citta);
                    latA = c[0];
                    lonA = c[1];
                    //coordinate dei file csv
                    ArrayList<Integer> listaID = getUtentiVicini(latA, lonA);

//                            $lat1 = $row["lat"];
//                    $lon1 = $row["lng"];
//                    $lat2 = $_GET["lat"];
//                    $lon2 = $_GET["lng"];
//                    if ($lat2 != $lat1 || $lon1 != $lon2) {
//                        $lat11 = deg2rad($lat1);
//                        $lon11 = deg2rad($lon1);
//                        $lat2 = deg2rad($lat2);
//                        $lon2 = deg2rad($lon2);
//                        $earthRadius = 6371.01; //Kilometers
//                        $resultKM = $earthRadius * acos(sin($lat11) * sin($lat2) + cos($lat11) * cos($lat2) * cos($lon11 - $lon2));
//                    } else if ($lat2 == $lat1 && $lon1 == $lon2) {
//                        $resultKM = 0;
//                    }
                    dati.getAlAd().remove(0);
                } catch (IOException ex) {
                    Logger.getLogger(TSendMessage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (true);
    }

    private ArrayList<Integer> getUtentiVicini(Float latA, Float lonA) throws IOException {
        ArrayList<Integer> id = new ArrayList<Integer>();
        ArrayList<String> cittas = csv.getAllCitta();
        Float latB, lonB;
        for (String citta : cittas) {
            Float[] c = getCoordinate(citta);
            latB = c[0];
            lonB = c[1];
        }
        return id;
    }

    private Float[] getCoordinate(String citta) throws MalformedURLException, IOException {
        Float[] coordinate = new Float[2];//posizione 0-> latitudine | posizione 1-> longitudine
        URL fileUrl = new URL("https://nominatim.openstreetmap.org/search?q=" + URLEncoder.encode(citta, StandardCharsets.UTF_8) + "&format=xml&addressdetails=1");
        Scanner inRemote = new Scanner(fileUrl.openStream());
        inRemote.useDelimiter("\u001a");
        String content = inRemote.next();

        Document doc = convertStringToXMLDocument(content);
        NodeList listPlace = doc.getElementsByTagName("place");
        if (listPlace.getLength() > 0) {
            Element e = (Element) listPlace.item(0);
            if (e.hasAttribute("lat") && e.hasAttribute("lon")) {
                coordinate[0] = Float.parseFloat(e.getAttribute("lat"));
                coordinate[1] = Float.parseFloat(e.getAttribute("lon"));
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
