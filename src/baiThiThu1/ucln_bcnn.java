/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baiThiThu1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Admin
 */
public class ucln_bcnn {
    //ucln
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    //bcnn
    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
    
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("172.188.19.218", 1605);
        //gui chuoi msv
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF("B21DCCN731;xK9qfIW");
        //nhan 2 so nguyen
        DataInputStream dis = new DataInputStream(client.getInputStream());
        int a = dis.readInt();
        int b = dis.readInt();
        //ucln, bcnn
        int ucln = gcd(a, b);
        int bcnn = lcm(a, b);
        dos.writeInt(ucln);
        dos.writeInt(bcnn);
        //dong ket noi
        dis.close();
        dos.close();
        client.close();
    }
}
