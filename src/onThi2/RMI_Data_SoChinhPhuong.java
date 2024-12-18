/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi2;

import RMI.DataService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */

/*[Mã câu hỏi (qCode): Uk8Io5wr].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu.
Giao diện từ xa:
public interface DataService extends Remote {
public Object requestData(String studentCode, String qCode) throws RemoteException;
public void submitData(String studentCode, String qCode, Object data) throws RemoteException;
}
Trong đó:
•	Interface DataService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa DataService được đăng ký với RegistryServer với tên là: RMIDataService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhận được từ RMI Server:
a. Triệu gọi phương thức requestData để nhận một số nguyên dương N từ server.
b. Xác định tất cả các số chính phương nhỏ hơn N. Kết quả trả về là danh sách các số chính phương thỏa mãn yêu cầu.
Ví dụ: Với N = 20, kết quả là [1, 4, 9, 16].
c. Triệu gọi phương thức submitData để gửi đối tượng List<Integer> danh sách các số chính phương đã tìm được trở lại server.
d. Kết thúc chương trình client.*/

public class RMI_Data_SoChinhPhuong {
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        DataService ds = (DataService) Naming.lookup("rmi://203.162.10.109/RMIDataService");
        //rec
        String studentCode = "B21DCCN039";
        String qCode = "Uk8Io5wr";
        Object recData = ds.requestData(studentCode, qCode);
        
        int n = (int) recData;
        double k = Math.sqrt(n);
        List<Integer> data = new ArrayList<>();
        for(int i = 1; i <= k; i++){
            data.add(i * i);
        }
        //res
        ds.submitData(studentCode, qCode, data);
    }
}
