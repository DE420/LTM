/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

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
public class UDP_Object_ThongTinSanPham {
    public static String fixProductName(String name){
        String[] words = name.split(" ");
        if(words.length < 2) return name;
        String temp = words[0];
        words[0] = words[words.length - 1];
        words[words.length - 1] = temp;
        return String.join(" ", words);
    }
    
    public static int fixProductQuantity(int quantity){
        String quantityStr = Integer.toString(quantity);
        String reversedStr = new StringBuilder(quantityStr).reverse().toString();
        return Integer.parseInt(reversedStr);
    }
    
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException, ClassNotFoundException {
        DatagramSocket client = new DatagramSocket();
        //req
        String reqMessage = ";B21DCCN731;s6VUr7Fq";
        byte[] reqData = reqMessage.getBytes();
        DatagramPacket reqPacket = new DatagramPacket(reqData, reqData.length, InetAddress.getByName("203.162.10.109"), 2209);
        client.send(reqPacket);
        //rec
        byte[] recData = new byte[1024];
        DatagramPacket recPacket = new DatagramPacket(recData, recData.length);
        client.receive(recPacket);
        
        byte[] requestIdBytes = new byte[8];
        System.arraycopy(recData, 0, requestIdBytes, 0, 8);
        
        ByteArrayInputStream bais = new ByteArrayInputStream(recData, 8, recData.length - 8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Product receivedProduct = (Product)ois.readObject();
        
        receivedProduct.setName(fixProductName(receivedProduct.getName()));
        receivedProduct.setQuantity(fixProductQuantity(receivedProduct.getQuantity()));
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(receivedProduct);
        byte[] productData = baos.toByteArray();
                
        //res
        byte[] resData = new byte[8 + productData.length];
        System.arraycopy(requestIdBytes, 0, resData, 0, 8);
        System.arraycopy(productData, 0, resData, 8, productData.length);
        DatagramPacket resPacket = new DatagramPacket(resData, resData.length, InetAddress.getByName("203.162.10.109"), 2209);
        client.send(resPacket);
        
        client.close();
    }
}
