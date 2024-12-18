package onThi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/*[Mã câu hỏi (qCode): 8Ta8OvXz].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:

a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN009;F3E8B2D4".

b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;n, n", với:
--- requestId là chuỗi ngẫu nhiên duy nhất.
--- n là một số nguyên ngẫu nhiên ≤ 100.

c. Tính và gửi về server danh sách n số nguyên tố đầu tiên theo định dạng "requestId;p1,p2,...,pk", trong đó p1,p2,...,pk là các số nguyên tố.

d. Đóng socket và kết thúc chương trình.*/

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
