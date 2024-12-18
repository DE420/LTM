/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

import RMI.ByteService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Admin
 */
public class RMI_Byte_MaHoaCeasar {
    private static byte[] caesarCipher(byte[] data, int shift) {
        byte[] result = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = (byte) (data[i] + shift);
        }
        return result;
    }
    
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        ByteService bs = (ByteService) Naming.lookup("rmi://203.162.10.109/RMIByteService");
        //rec
        String studentCode = "B21DCCN731";
        String qCode = "AH3pW8LY";
        byte[] recData = bs.requestData(studentCode, qCode);
        
        int shift = recData.length; // Number of ASCII characters in the array
        byte[] encodedData = caesarCipher(recData, shift);
        //res
        bs.submitData(studentCode, qCode, encodedData);
    }
}
