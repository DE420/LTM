/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Admin
 */

/*[Mã câu hỏi (qCode): Ih0jvyb2].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN001;73457A17”
b.	Nhận thông điệp là một chuỗi từ server theo định dạng “requestId;n;A1,A2,...An” , với
-	requestId là chuỗi ngẫu nhiên duy nhất
-	n là một số ngẫu nhiên nhỏ hơn 100.
-            A1, A2 ... Am (m <= n) là các giá trị ngẫu nhiên nhỏ hơn hoặc bằng n và có thể trùng nhau.
Ex: requestId;10;2,3,5,6,5
c.	Tìm kiếm các giá trị còn thiếu và gửi lên server theo định dạng “requestId;B1,B2,...,Bm”
Ex: requestId;1,4,7,8,9,10
d.	Đóng socket và kết thúc chương trình.*/

public class UDP_Data_SoConThieu {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        //req
        String reqStr = ";B21DCCN039;Ih0jvyb2";
        byte[] reqData = reqStr.getBytes();
        DatagramPacket reqPacket = new DatagramPacket(reqData, reqData.length, InetAddress.getByName("203.162.10.109"), 2207);
        client.send(reqPacket);
        //rec
        byte[] recData = new byte[1024];
        DatagramPacket recPacket = new DatagramPacket(recData, recData.length);
        client.receive(recPacket);
        String recStr = new String(recPacket.getData(), 0, recPacket.getLength());
        
        String[] parts = recStr.split(";");
        Integer n = Integer.valueOf(parts[1]);
        String[] numStr = parts[2].split(",");
        Set<Integer> nums = new HashSet<>();
        for(String i : numStr){
            nums.add(Integer.valueOf(i));
        }
        
        StringBuilder resStr = new StringBuilder(parts[0] + ";");
        for(int i = 1; i <= n; i++){
            if(!nums.contains(i)){
                resStr.append(i + ",");
            }
        }
        resStr.deleteCharAt(resStr.length() - 1);
        //res
        byte[] resData = resStr.toString().getBytes();
        DatagramPacket resPacket = new DatagramPacket(resData, resData.length, InetAddress.getByName("203.162.10.109"), 2207);
        client.send(resPacket);
        
        client.close();
    }
}
