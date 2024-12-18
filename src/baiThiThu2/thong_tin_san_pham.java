/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baiThiThu2;

import UDP.Product;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Admin
 */
public class thong_tin_san_pham {
    public static String fixProductName(String name) {
        String[] words = name.split(" ");
        if (words.length < 2) return name;
        
        String temp = words[0];
        words[0] = words[words.length - 1];
        words[words.length - 1] = temp;
        return String.join(" ", words);
    }
    
    public static int fixProductQuantity(int quantity) {
        String quantityStr = Integer.toString(quantity);
        String reversedStr = new StringBuilder(quantityStr).reverse().toString();
        return Integer.parseInt(reversedStr);
    }
    
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException, ClassNotFoundException {
        DatagramSocket client = new DatagramSocket();
        //gui chuoi msv
        String requestMessage = ";B21DCCN731;s6VUr7Fq";
        byte[] sendData = requestMessage.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("203.162.10.109"), 2209);
        client.send(sendPacket);
        //nhan doi tuong
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        client.receive(receivePacket);
        //sua doi tuong va gui
        byte[] requestIdBytes = new byte[8];
        System.arraycopy(receiveData, 0, requestIdBytes, 0, 8);
        String requestId = new String(requestIdBytes);
        
        ByteArrayInputStream byteStream = new ByteArrayInputStream(receiveData, 8, receiveData.length - 8);
        ObjectInputStream is = new ObjectInputStream(byteStream);
        Product receivedProduct = (Product) is.readObject();
        
        receivedProduct.setName(fixProductName(receivedProduct.getName()));
        receivedProduct.setQuantity(fixProductQuantity(receivedProduct.getQuantity()));
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(receivedProduct);
        byte[] productData = bos.toByteArray();

        byte[] responseData = new byte[8 + productData.length];
        System.arraycopy(requestIdBytes, 0, responseData, 0, 8);
        System.arraycopy(productData, 0, responseData, 8, productData.length);

        DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, InetAddress.getByName("203.162.10.109"), 2209);
        client.send(responsePacket);
        //dong ket noi
        client.close();
    }
}
