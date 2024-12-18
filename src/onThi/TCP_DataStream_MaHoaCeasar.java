/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Admin
 */
public class TCP_DataStream_MaHoaCeasar {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("203.162.10.109", 2207);
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        //req
        String reqMessage = "B21DCCN731;QZgkUQSX";
        dos.writeUTF(reqMessage);
        //rec
        String recMessage = dis.readUTF();
        int s = dis.readInt();
        
        String decrypt = "";
        for(char c : recMessage.toCharArray()){
            if(Character.isLowerCase(c)){
                c = (char) ((c - 'a' + s + 26) % 26 + 'a');
            }
            else
                c = (char) ((c - 'A' + s + 26) % 26 + 'A');
            decrypt += c;
        }
        //res
        dos.writeUTF(decrypt);
        
        dis.close();
        dos.close();
        client.close();
    }
}
