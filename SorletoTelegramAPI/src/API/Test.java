/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.*;

/**
 *
 * @author Domen
 */
public class Test {

    private String jsonS;
    private Integer update_id; //id dell'ultimo messaggio arrivato

    public Test(String jsonS) {
        this.jsonS = jsonS;
        update_id = -1;
    }

    public Messaggio getUpdates() throws IOException {

        //ArrayList<Messaggio> LMsg = new ArrayList<Messaggio>();
        Messaggio msg = null;
        From from;
        Chat chat;
        URL fileUrl = new URL(jsonS + "/getUpdates");
        Scanner inRemote = new Scanner(fileUrl.openStream());
        inRemote.useDelimiter("\u001a");
        String content = inRemote.next();
        String jsonString = content;
        JSONObject obj = new JSONObject(jsonString);
        JSONArray v = obj.getJSONArray("result"); // notice that `"posts": [...]`
        int update_last_message;
        //citta[] c = new Gson().fromJson(content, citta[].class);
        try {
            update_last_message = v.getJSONObject(v.length() - 1).getInt("update_id");
        } catch (Exception e) {
            update_last_message = -1;
        }
        if (update_id == -1) {
            update_id = update_last_message; //salvo il numero nuovo
        } else if (update_id < update_last_message) {
            //abbiamo un nuovo messaggio
            JSONObject messaggio = v.getJSONObject(v.length() - 1);
            JSONObject vMsg = messaggio.getJSONObject("message");
            JSONObject vForm = vMsg.getJSONObject("from");

            int message_id = vMsg.getInt("message_id");
            int id_update = messaggio.getInt("update_id");
            int id = vForm.getInt("id");
            boolean is_bot = vForm.getBoolean("is_bot");

            String first_name = vForm.getString("first_name");
            String username = vForm.getString("username");
            String language_code = vForm.getString("language_code");
            from = new From(id, is_bot, first_name, username, language_code);

            JSONObject vChat = vMsg.getJSONObject("chat");
            int idC = vChat.getInt("id");
            String first_nameC = vChat.getString("first_name");
            String usernameC = vChat.getString("username");
            String type = vChat.getString("type");
            chat = new Chat(idC, first_nameC, usernameC, type);

            int date = vMsg.getInt("date");
            String text = vMsg.getString("text");
            msg = new Messaggio(id_update, message_id, from, chat, date, text);
            update_id = id_update;
        }
        return msg;
//        for (int i = 0; i < v.length(); i++) {
//
//            JSONObject messaggio = v.getJSONObject(i);
//            JSONObject vMsg = messaggio.getJSONObject("message");
//            JSONObject vForm = vMsg.getJSONObject("from");
//
//            int message_id = vMsg.getInt("message_id");
//            int update_id = messaggio.getInt("update_id");
//            int id = vForm.getInt("id");
//            boolean is_bot = vForm.getBoolean("is_bot");
//
//            String first_name = vForm.getString("first_name");
//            String username = vForm.getString("username");
//            String language_code = vForm.getString("language_code");
//            from = new From(id, is_bot, first_name, username, language_code);
//
//            JSONObject vChat = vMsg.getJSONObject("chat");
//            int idC = vChat.getInt("id");
//            String first_nameC = vChat.getString("first_name");
//            String usernameC = vChat.getString("username");
//            String type = vChat.getString("type");
//            chat = new Chat(idC, first_nameC, usernameC, type);
//
//            int date = vMsg.getInt("date");
//            String text = vMsg.getString("text");
//            msg = new Messaggio(update_id, message_id, from, chat, date, text);
//            LMsg.add(msg);
//        }
    }

    public void sendMessage(int idDestinatario, String testo) throws MalformedURLException, IOException {
        String url = jsonS + "/sendMessage?";
        String p = "chat_id=" + idDestinatario + "&text=" + URLEncoder.encode(testo, "UTF-8");
        url += p;
        URL fileUrl = new URL(url);
        Scanner inRemote = new Scanner(fileUrl.openStream());
        inRemote.useDelimiter("\u001a");

        String content = inRemote.next();
        inRemote.close();
    }

    private boolean ControllaMessaggio() {
        //check Luogo  -> /Luogo

        return true;
    }
}
