/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class UDP_String_KyTuXuatHienNhieuNhat {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        //req
        String reqMessage = ";B21DCCN731;EUN1CzrZ";
        byte[] reqData = reqMessage.getBytes();
        DatagramPacket reqPacket = new DatagramPacket(reqData, reqData.length, InetAddress.getByName("203.162.10.109"), 2208);
        client.send(reqPacket);
        //rec
        byte[] recData = new byte[1024];
        DatagramPacket recPacket = new DatagramPacket(recData, recData.length);
        client.receive(recPacket);
        String recMessage = new String(recPacket.getData(), 0, recPacket.getLength());
        
        String[] parts = recMessage.split(";");
        String requestId = parts[0];
        String data = parts[1];
        
        Map<Character, List<Integer>> charPositions = new HashMap<>();
        int maxFrequency = 0;
        char maxChar = 0;
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            charPositions.putIfAbsent(c, new ArrayList<>());
            charPositions.get(c).add(i);

            if (charPositions.get(c).size() > maxFrequency) {
                maxFrequency = charPositions.get(c).size();
                maxChar = c;
            }
        }
        StringBuilder positions = new StringBuilder();
        for (int pos : charPositions.get(maxChar)) {
            positions.append(pos + 1).append(",");
        }

        String result = requestId + ";" + maxChar + ":" + positions;
        //res
        byte[] resData = result.getBytes();
        DatagramPacket resPacket = new DatagramPacket(resData, resData.length, InetAddress.getByName("203.162.10.109"), 2208);
        client.send(resPacket);
        
        client.close();
    }
}
