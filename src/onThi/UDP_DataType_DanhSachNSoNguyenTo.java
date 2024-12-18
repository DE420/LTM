package onThi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class UDP_DataType_DanhSachNSoNguyenTo {
    public static boolean check(int n){
        if(n < 2) return false;
        for(int i = 2; i <= Math.sqrt(n); i++){
            if(n % i == 0) return false;
        }
        return true;
    }
    
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        //req
        String reqMessage = ";B21DCCN731;8Ta8OvXz";
        byte[] reqData = reqMessage.getBytes();
        DatagramPacket reqPacket = new DatagramPacket(reqData, reqData.length, InetAddress.getByName("203.162.10.109"), 2207);
        client.send(reqPacket);
        //rec
        byte[] recData = new byte[1024];
        DatagramPacket recPacket = new DatagramPacket(recData, recData.length);
        client.receive(recPacket);
        String recMessage = new String(recPacket.getData(), 0, recPacket.getLength());
        
        String[] parts = recMessage.split(";");
        String requestId = parts[0];
        int n = Integer.parseInt(parts[1]);
        
        List<Integer> primes = new ArrayList<>();
        primes.add(2);
        for(int i = 3; primes.size() < n; i += 2){
            if(check(i)){
                primes.add(i);
            }
        }
        
        StringBuilder primeList = new StringBuilder();
        for(int i = 0; i < primes.size(); i++){
            if(i > 0) primeList.append(",");
            primeList.append(primes.get(i));
        }
        String result = requestId + ";" + primeList;
        //res
        byte[] resData = result.getBytes();
        DatagramPacket resPacket = new DatagramPacket(resData, resData.length, InetAddress.getByName("203.162.10.109"), 2207);
        client.send(resPacket);
        
        client.close();
    }
}
