/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sorletobottelegram;

/**
 *
 * @author Domen
 */
public class JPersona {

    public Integer chat_id;
    public String citta;

    public JPersona(Integer chat_id, String citta) {
        this.chat_id = chat_id;
        this.citta = citta;
    }
    
    public String toString(){
        return chat_id + ";" + citta;
    }
}
