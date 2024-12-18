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

/*[Mã câu hỏi (qCode): EUN1CzrZ].  Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client tương tác với server kịch bản dưới đây:
a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN001;EE29C059”
b.	Nhận thông điệp từ server theo định dạng “requestId; data” 
-	requestId là một chuỗi ngẫu nhiên duy nhất
-	data là chuỗi dữ liệu đầu vào cần xử lý
Ex: “requestId;Qnc8d5x78aldSGWWmaAAjyg3”
c.	Tìm kiếm ký tự xuất hiện nhiều nhất trong chuỗi và gửi lên server theo định dạng “requestId;ký tự xuất hiện nhiều nhất: các vị trí xuất hiện ký tự đó” 
ví dụ: “requestId;8:4,9,”
d.	Đóng socket và kết thúc chương trình*/

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
