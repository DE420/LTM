/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

import java.util.ArrayList;
import java.util.List;
import vn.medianews.DataService;
import vn.medianews.DataService_Service;

/**
 *
 * @author Admin
 */

/*[Mã câu hỏi (qCode): gCPHrTyK].  Một dịch vụ web được định nghĩa và mô tả trong tệp DataService?wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/DataService?wsdl để xử lý các bài toán với dữ liệu nguyên thủy.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với DataService thực hiện các công việc sau:
a. Triệu gọi phương thức getDataDouble với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một số thập phân (double) từ server.
b. Làm tròn số thập phân nhận được tới 02 chữ số thập phân và thực hiện chuyển đổi số thập phân nhận được thành một phân số tối giản. Lưu tử số và mẫu số nguyên của phân số đó vào danh sách (List<Integer>) với phần tử đầu tiên là tử số và phần tử thứ hai là mẫu số.
c. Triệu gọi phương thức submitDataIntArray(String studentCode, String qCode, List<Integer> data) để gửi phân số đơn giản nhất đã chuyển đổi trở lại server.
Ví dụ: Nếu số thập phân nhận được từ phương thức getData là 0.75, chương trình client sẽ chuyển đổi thành phân số tối giản là [3, 4], và gửi mảng [3, 4] trở lại server qua phương thức submitData.
d. Kết thúc chương trình client.*/

public class WS_DataService_PhanSoToiGian {
    public static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a%b);
    }
    
    public static void main(String[] args) {
        DataService_Service dss = new DataService_Service();
        DataService port = dss.getDataServicePort();
        //rec
        String studentCode = "B21DCCN731";
        String qCode = "gCPHrTyK";
        double recDouble = port.getDataDouble(studentCode, qCode);
        
        int tu = (int) (recDouble * 100);
        int ucln = gcd(tu, 100);
        List<Integer> data = new ArrayList<>();
        data.add(tu / ucln);
        data.add(100 / ucln);
        port.submitDataIntArray(studentCode, qCode, data);
    }
}
