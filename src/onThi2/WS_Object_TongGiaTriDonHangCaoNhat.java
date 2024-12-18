/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi2;

import java.util.ArrayList;
import java.util.List;
import vn.medianews.ObjectService;
import vn.medianews.ObjectService_Service;
import vn.medianews.Order;

/**
 *
 * @author Admin
 */

/*[Mã câu hỏi (qCode): yDvKOPOR].  Một dịch vụ web được định nghĩa và mô tả trong tệp ObjectService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/ObjectService?wsdl để xử lý các bài toán với đối tượng.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với ObjectService thực hiện các công việc sau:
a. Triệu gọi phương thức requestListOrder với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về danh sách đối tượng Order từ server. Mỗi đối tượng Order có các thuộc tính:
•	customerId: kiểu String, đại diện cho mã khách hàng.
•	amount: kiểu float, đại diện cho giá trị của đơn hàng.
•	status: kiểu String, đại diện cho trạng thái của đơn hàng, với các trạng thái có thể là "completed", "pending", hoặc "canceled".
b. Thực hiện lọc và chỉ giữ lại các hóa đơn của khách hàng có tổng giá trị đơn hàng cao nhất
c. Triệu gọi phương thức submitListOrder(String studentCode, String qCode, List<Order> data) để gửi danh sách hóa đơn của khách hàng có tổng giá trị đơn hàng cao nhất.
d. Kết thúc chương trình client.*/

public class WS_Object_TongGiaTriDonHangCaoNhat {
    public static void main(String[] args) {
        ObjectService_Service oss = new ObjectService_Service();
        ObjectService port = oss.getObjectServicePort();
        //rec
        String studentCode = "B21DCCN039";
        String qCode = "yDvKOPOR";
        List<Order> recList = port.requestListOrder(studentCode, qCode);
        
        float max = 0;
        for(Order i : recList){
            if(i.getAmount() > max)
                max = i.getAmount();
        }
        List<Order> data = new ArrayList<>();
        for(Order i : recList){
            if(i.getAmount() == max)
                data.add(i);
        }
        //res
        port.submitListOrder(studentCode, qCode, data);
    }
}
