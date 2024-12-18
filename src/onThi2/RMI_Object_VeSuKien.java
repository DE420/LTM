/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi2;

import RMI.ObjectService;
import RMI.Ticket;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Admin
 */

/*[Mã câu hỏi (qCode): d09159Lp].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để quản lý thông tin vé trong hệ thống bán vé sự kiện. Chương trình sẽ ngẫu nhiên tạo ra đối tượng Ticket với các giá trị ban đầu và cung cấp cho RMI client như sau:
    Giao diện từ xa:
public interface ObjectService extends Remote {
    public Serializable requestObject(String studentCode, String qCode) throws RemoteException;
    public void submitObject(String studentCode, String qCode, Serializable object) throws RemoteException;
}
Lớp Ticket gồm các thuộc tính: id String, eventName String, saleDate String, ticketCode String.
•	Trường dữ liệu: private static final long serialVersionUID = 20241133L;
•	02 hàm khởi dựng:
    	public Ticket()
    	public Ticket(String id, String eventName, String saleDate)
Trong đó:
•	Interface ObjectService và lớp Ticket được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa ObjectService được đăng ký với RegistryServer: RMIObjectService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với đối tượng vé được nhận từ RMI Server:
a. Triệu gọi phương thức requestObject để nhận đối tượng Ticket ngẫu nhiên từ server.
b. Tạo mã ticketCode cho vé dựa trên các quy tắc sau:
•	Bắt đầu với hai chữ cái là: Chữ cái đầu tiên và cuối cùng của eventName, viết hoa.
•	Thêm ngày và tháng từ saleDate (theo định dạng "MMdd")
•	Kết thúc bằng hai chữ số là: Chữ số lớn nhất và nhỏ nhất không xuất hiện trong saleDate.
    	Ví dụ: tên sự kiện là "Charity Concert", và ngày bán là "15/06/2024", mã ticketCode sẽ là "CT061593".
c. Cập nhật giá trị ticketCode trong đối tượng Ticket.
d. Triệu gọi phương thức submitObject để gửi đối tượng Ticket đã được xử lý trở lại server.
e. Kết thúc chương trình client.*/

public class RMI_Object_VeSuKien {
    public static String genTkCode(String eventName, String saleDate){
        String ans = "";
        
        char firstChar = Character.toUpperCase(eventName.charAt(0));
        char lastChar = Character.toUpperCase(eventName.charAt(eventName.length() - 1));
        
        String[] sd1 = saleDate.split("/");
        String md = sd1[1] + sd1[0];
        
        String sd2 = saleDate.replace("/", "");
        String digits = "0123456789";
        char max = '0';
        char min = '9';
        for(char i : digits.toCharArray()){
            if(!sd2.contains(String.valueOf(i))){
                if(i > max) max = i;
                if(i < min) min = i;
            }
        }
        
        ans += String.valueOf(firstChar) + String.valueOf(lastChar) + md + max + min;        
        return ans;
    }
    
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        ObjectService os = (ObjectService) Naming.lookup("rmi://203.162.10.109/RMIObjectService");
        //rec
        String studentCode = "B21DCCN039";
        String qCode = "d09159Lp";
        Serializable recObject = os.requestObject(studentCode, qCode);
        
        Ticket tk = (Ticket) recObject;
        tk.setTicketCode(genTkCode(tk.getEventName(), tk.getSaleDate()));
        Serializable object = (Serializable) tk;
        //res
        os.submitObject(studentCode, qCode, object);
    }
}
