
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sorletobottelegram;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Domen
 */
public class CSV {

    private String fileName;
    private File file;

    public CSV(String fileName) throws IOException {
        this.fileName = fileName;
        file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public CSV() {
    }

    public void addPersona(JPersona persona) throws IOException {
        synchronized (file) {
            Scanner s = new Scanner(file);
            String line;
            boolean uguale = false;
            do {
                try {
                    line = s.nextLine();
                    if (line.split(";")[0].equals(persona.chat_id)) {
                        uguale = true;
                    }
                } catch (Exception e) {
                    line = null;
                }

            } while (line != null);
            if (!uguale) {
                FileWriter fW = new FileWriter(file, true);
                fW.append(persona.toString() + "\n");
                fW.flush();
                fW.close();
            }
        }
    }

    public ArrayList<JPersona> getAllCitta() throws IOException {
        ArrayList<JPersona> allCitta = new ArrayList<JPersona>();
        synchronized (file) {
            Scanner s = new Scanner(file);
            String line;
            boolean uguale = false;
            do {
                try {
                    line = s.nextLine();
                    allCitta.add(new JPersona(Integer.parseInt(line.split(";")[0]), line.split(";")[1]));
                } catch (Exception e) {
                    line = null;
                }
            } while (line != null);
        }
        return allCitta;
    }

}
