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

/*[Mã câu hỏi (qCode): AH3pW8LY].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu nhị phân.
Giao diện từ xa:
public interface ByteService extends Remote {
public byte[] requestData(String studentCode, String qCode) throws RemoteException;
public void submitData(String studentCode, String qCode, byte[] data) throws RemoteException;
}
Trong đó:
•	Interface ByteService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa ByteService được đăng ký với RegistryServer với tên là: RMIByteService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhị phân nhận được từ RMI Server:
a. Triệu gọi phương thức requestData để nhận một mảng dữ liệu nhị phân (byte[]) từ server, đại diện cho một chuỗi văn bản ASCII.
b. Thực hiện mã hóa Caesar cho mảng dữ liệu nhị phân bằng cách dịch chuyển mỗi byte trong mảng đi một số bước cố định trong bảng mã ASCII. Số bước dịch chuyển là số ký tự ASCII trong mảng dữ liệu.
    Ví dụ: Nếu dữ liệu nhị phân nhận được là [72, 101, 108, 108, 111] (tương ứng với chuỗi "Hello"), chương trình sẽ thực hiện mã hóa Caesar với độ dịch là 5. Kết quả mã hóa là mảng [77, 108, 113, 113, 116], tương ứng với chuỗi "Mlqqt".
c. Triệu gọi phương thức submitData để gửi mảng dữ liệu đã được mã hóa bằng Caesar trở lại server.
d. Kết thúc chương trình client.*/

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
