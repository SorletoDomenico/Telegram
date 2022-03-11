/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

/**
 *
 * @author Domen
 */
public class Messaggio {

    public Messaggio() {
    }

    public Messaggio(int update_id, int message_id, From from, Chat chat, int date, String text) {
        this.update_id = update_id;
        this.message_id = message_id;
        this.from = from;
        this.chat = chat;
        this.date = date;
        this.text = text;
    }
    
    public int update_id;

    public int message_id;

    public From from;
    public Chat chat;

    public int date;
    public String text;
}
