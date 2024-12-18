/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baiThiThu2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Admin
 */
public class chuan_hoa_chuoi {
    public static String normalizeString(String str){
        String[] words = str.split("\\s+");
        StringBuilder nStr = new StringBuilder();
        for(String word : words){
            if(word.length() > 0){
                nStr.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1).toLowerCase())
                    .append(" ");
            }
        }
        return nStr.toString().trim();
    }
    
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        //gui chuoi msv
        String requestMessage = ";B21DCCN731;TLyUf37T";
        byte[] sendData = requestMessage.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("203.162.10.109"), 2208);
        client.send(sendPacket);
        //nhan thong diep
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        client.receive(receivePacket);
        String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
        //chuan hoa va gui lai chuoi
        String[] idSep = receivedMessage.split(";");
        String requestId = idSep[0];
        String data = idSep[1];
        String normalizedData = normalizeString(data);
        
        String responseMessage = requestId + ";" + normalizedData;
        byte[] responseData = responseMessage.getBytes();
        DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, InetAddress.getByName("203.162.10.109"), 2208);
        client.send(responsePacket);
        //dong socket
        client.close();
    }
}
