/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi2;

import RMI.ByteService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Admin
 */

/*[Mã câu hỏi (qCode): CPQUBnYK].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu nhị phân.
Giao diện từ xa:
public interface ByteService extends Remote {
public byte[] requestData(String studentCode, String qCode) throws RemoteException;
public void submitData(String studentCode, String qCode, byte[] data) throws RemoteException;
}
Trong đó:
•	Interface ByteService được viết trong package RMI.
Đối tượng cài đặt giao diện từ xa ByteService được đăng ký với RegistryServer với tên là: RMIByteService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhị phân nhận được từ RMI Server:
a. Triệu gọi phương thức requestData để nhận một mảng dữ liệu nhị phân (byte[]) từ server, cùng với một số nguyên K, đại diện cho thứ tự phần tử cần tìm.
b. Tìm phần tử lớn thứ K trong mảng byte[] với K là phần tử cuối cùng của mảng.
Ví dụ: Nếu mảng dữ liệu nhận được là [3, 1, 5, 12, 2, 11, 3], giá trị K = 3, chương trình sẽ tìm phần tử lớn thứ ba -> Kết quả là 5.
c. Triệu gọi phương thức submitData để gửi mảng byte gồm phần tử lớn thứ K và vị trí của K trở lại server.
d. Kết thúc chương trình client.*/

public class RMI_Byte_PhanTuLonThuK {
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        ByteService bs = (ByteService) Naming.lookup("rmi://203.162.10.109/RMIByteService");
        //rec
        String studentCode = "B21DCCN039";
        String qCode = "CPQUBnYK";
        byte[] recData = bs.requestData(studentCode, qCode);
        
        List<Integer> listInt = new ArrayList<>();
        for(int i = 0; i < recData.length; i++)
            listInt.add((int) recData[i]);
        listInt.sort(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
            
        });
        int shift = recData[recData.length - 1];
        int num = listInt.get(shift - 1);
        byte[] data = new byte[2];
        data[0] = (byte) num;
        for(int i = 0; i < recData.length; i++){
            if(num == recData[i]){
                data[1] = (byte) (i + 1);
            }
        }
        //res
        bs.submitData(studentCode, qCode, data);
    }
}
