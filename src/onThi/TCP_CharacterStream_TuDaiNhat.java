/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author Admin
 */
public class TCP_CharacterStream_TuDaiNhat {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("203.162.10.109", 2208);
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        //req
        bw.write("B21DCCN731;OSosTfy5");
        bw.newLine();
        bw.flush();
        //rec
        String recMessage = br.readLine();
        
        String[] words = recMessage.split(" ");
        String longest = "";
        for(String word : words){
            if(word.length() > longest.length()){
                longest = word;
            }
        }
        int index = recMessage.indexOf(longest);
        
        //res
        bw.write(longest);
        bw.newLine();
        bw.flush();
        bw.write(Integer.toString(index));
        bw.newLine();
        bw.flush();
        
        br.close();
        bw.close();
        client.close();
    }
}
