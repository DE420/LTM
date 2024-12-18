/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baiThiThu1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ten_mien_dung {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("172.188.19.218", 1606);
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        //gui chuoi msv
        bw.write("B21DCCN731;HYKai7q");
        bw.newLine();
        bw.flush();
        //nhan ds ten mien
        String domainlist = br.readLine();
        //gui ten mien edu
        String[] domains = domainlist.split(", ");
        List<String> edudomains = new ArrayList<>();
        for(String domain : domains){
            if(domain.endsWith(".edu")){
                edudomains.add(domain);
            }
        }
        String answer = String.join(", ", edudomains);
        bw.write(answer);
//        bw.newLine();
        bw.flush();
        //dong ket noi
        br.close();
        bw.close();
        client.close();
    }
}
