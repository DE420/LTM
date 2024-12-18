/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baiThiThu1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Admin
 */
public class tong_cac_so {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("172.188.19.218", 1604);
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();
        //gui chuoi msv
        String msv = "B21DCCN731;kJ9qn8n";
        os.write(msv.getBytes());
        //nhan chuoi gia tri
        byte[] buf = new byte[1024];
        int byte_read = is.read(buf);
        String rec_str = new String(buf, 0, byte_read);
        //tach chuoi tinh tong
        String[] numbers = rec_str.split("\\|");
        int sum = 0;
        for (String number : numbers){
            sum += Integer.parseInt(number.trim());
        }
        String sum_str = String.valueOf(sum);
        os.write(sum_str.getBytes());
        os.flush();
        //dong ket noi
        is.close();
        os.close();
        client.close();
    }
}
