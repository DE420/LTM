/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

import UDP.Product;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Admin
 */

/*[Mã câu hỏi (qCode): s6VUr7Fq].  Thông tin sản phẩm vì một lý do nào đó đã bị sửa đổi thành không đúng, cụ thể:
a.	Tên sản phẩm bị đổi ngược từ đầu tiên và từ cuối cùng, ví dụ: “lenovo thinkpad T520” bị chuyển thành “T520 thinkpad lenovo”
b.	Số lượng sản phẩm cũng bị đảo ngược giá trị, ví dụ từ 9981 thành 1899

Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2209. Yêu cầu là xây dựng một chương trình client giao tiếp với server để gửi/nhận các sản phẩm theo mô tả dưới đây:
a.	Đối tượng trao đổi là thể hiện của lớp Product được mô tả như sau
•	Tên đầy đủ của lớp: UDP.Product
•	Các thuộc tính: id String, code String, name String, quantity int
•	Một hàm khởi tạo có đầy đủ các thuộc tính được liệt kê ở trên
•	Trường dữ liệu: private static final long serialVersionUID = 20161107; 
b.	Giao tiếp với server theo kịch bản
•       Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN001;EE29C059”

•	Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại chứa một đối tượng là thể hiện của lớp Product từ server. Trong đối tượng này, các thuộc tính id, name và quantity đã được thiết lập giá trị.
•	Sửa các thông tin sai của đối tượng về tên và số lượng như mô tả ở trên và gửi đối tượng vừa được sửa đổi lên server theo cấu trúc:
08 byte đầu chứa chuỗi requestId và các byte còn lại chứa đối tượng Product đã được sửa đổi.
•	Đóng socket và kết thúc chương trình.*/

public class UDP_Object_ThongTinSanPham {
    public static String fixProductName(String name){
        String[] words = name.split(" ");
        if(words.length < 2) return name;
        String temp = words[0];
        words[0] = words[words.length - 1];
        words[words.length - 1] = temp;
        return String.join(" ", words);
    }
    
    public static int fixProductQuantity(int quantity){
        String quantityStr = Integer.toString(quantity);
        String reversedStr = new StringBuilder(quantityStr).reverse().toString();
        return Integer.parseInt(reversedStr);
    }
    
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException, ClassNotFoundException {
        DatagramSocket client = new DatagramSocket();
        //req
        String reqMessage = ";B21DCCN731;s6VUr7Fq";
        byte[] reqData = reqMessage.getBytes();
        DatagramPacket reqPacket = new DatagramPacket(reqData, reqData.length, InetAddress.getByName("203.162.10.109"), 2209);
        client.send(reqPacket);
        //rec
        byte[] recData = new byte[1024];
        DatagramPacket recPacket = new DatagramPacket(recData, recData.length);
        client.receive(recPacket);
        
        byte[] requestIdBytes = new byte[8];
        System.arraycopy(recData, 0, requestIdBytes, 0, 8);
        
        ByteArrayInputStream bais = new ByteArrayInputStream(recData, 8, recData.length - 8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Product receivedProduct = (Product)ois.readObject();
        
        receivedProduct.setName(fixProductName(receivedProduct.getName()));
        receivedProduct.setQuantity(fixProductQuantity(receivedProduct.getQuantity()));
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(receivedProduct);
        byte[] productData = baos.toByteArray();
                
        //res
        byte[] resData = new byte[8 + productData.length];
        System.arraycopy(requestIdBytes, 0, resData, 0, 8);
        System.arraycopy(productData, 0, resData, 8, productData.length);
        DatagramPacket resPacket = new DatagramPacket(resData, resData.length, InetAddress.getByName("203.162.10.109"), 2209);
        client.send(resPacket);
        
        client.close();
    }
}
