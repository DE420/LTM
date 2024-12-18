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
import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.List;

/**
 *
 * @author Admin
 */
public class requestIDMaxMin {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        //gui chuoi msv
        String requestMessage = ";B21DCCN731;DFdUaLde";
        byte[] sendData = requestMessage.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("203.162.10.109"), 2207);
        client.send(sendPacket);
        //nhan chuoi
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        client.receive(receivePacket);
        String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
        //tim max min va gui lai chuoi
        String[] idSep = receivedMessage.split(";");
        String requestID = idSep[0];
        String[] numbers = idSep[1].split(",");
        
        List<Integer> intnums = new ArrayList<>();
        for(String number : numbers){
            intnums.add(Integer.parseInt(number));
        }
        sort(intnums);
        String max = intnums.get(numbers.length - 1).toString();
        String min = intnums.get(0).toString();
        
        String responseMessage = requestID + ";" + max + "," + min;
        byte[] responseData = responseMessage.getBytes();
        DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, InetAddress.getByName("203.162.10.109"), 2207);
        client.send(responsePacket);
        //dong socket
        client.close();
    }
}
