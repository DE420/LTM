/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

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

/*[Mã câu hỏi (qCode): AfVo0C5W].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu.
Giao diện từ xa:
public interface DataService extends Remote {
public Object requestData(String studentCode, String qCode) throws RemoteException;
public void submitData(String studentCode, String qCode, Object data) throws RemoteException;
}
Trong đó:
•	Interface DataService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa DataService được đăng ký với RegistryServer với tên là: RMIDataService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhận được từ RMI Server:
a. Triệu gọi phương thức requestData để nhận một số nguyên dương lớn từ server, gọi là N.
b. Thực hiện phân rã số N thành các thừa số nguyên tố. Kết quả trả về là danh sách các thừa số nguyên tố của N.
Ví dụ: Với N = 84, kết quả là danh sách “2, 2, 3, 7”.
c. Triệu gọi phương thức submitData để gửi danh sách các thừa số nguyên tố đã tìm được trở lại server.
d. Kết thúc chương trình client.*/

public class RMI_Data_ThuaSoNguyenTo {
    private static List<Integer> primeFactor(int n){
        List<Integer> factors = new ArrayList<>();
        while(n % 2 == 0){
            factors.add(2);
            n /= 2;
        }
        for(int i = 3; i <= Math.sqrt(n); i += 2){
            while(n % i == 0){
                factors.add(i);
                n /= i;
            }
        }
        if(n > 2)
            factors.add(n);
        return factors;
    }
    
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        DataService ds = (DataService) Naming.lookup("rmi://203.162.10.109/RMIDataService");
        //rec
        String studentCode = "B21DCCN731";
        String qCode = "AfVo0C5W";
        Object recData = ds.requestData(studentCode, qCode);
        
        int n = (Integer) recData;
        List<Integer> primeFactors = primeFactor(n);
        //res
        ds.submitData(studentCode, qCode, primeFactors);
    }
}
