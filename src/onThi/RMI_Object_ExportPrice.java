/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

import RMI.ObjectService;
import RMI.Product;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Admin
 */

/*[Mã câu hỏi (qCode): nuXrS00j].  Một chương trình (tạm gọi là RMI server) cung cấp giao diện cho phép triệu gọi từ xa với thông tin như sau:
-	Giao diện từ xa
    public interface ObjectService extends Remote {
        public Serializable requestObject(String studentCode, String qAlias) throws RemoteException;

        public void submitObject(String studentCode, String qAlias, Serializable object) throws RemoteException;
    }
-	Lớp Product gồm các thông tin: id String, code String, importPrice double, exportPrice double.
    Trường dữ liệu: private static final long serialVersionUID = 20151107L;
    02 hàm khởi dựng 
        public Product()
        public Product(id String, String code,double ImportPrice, double ExportPrice)
Trong đó:
-	interface ObjectService và lớp Product được viết trong package RMI
-	Đối tượng cài đặt giao diện từ xa ObjectService được đăng ký với RegistryServer: RMIObjectService

Yêu cầu yêu cầu viết chương trình tại máy trạm (RMI client) thực hiện chuẩn hóa sản phẩm theo thứ tự:
a.	Triệu gọi phương thức requestObject để lấy về đối tượng sản phẩm cần chuẩn hóa.
b.	Thực hiện chuẩn hóa đối tượng nhận được theo nguyên tắc:
        - Chuyển mã sản phẩm thành in hoa.
        - Cập nhật giá xuất (exportPrice) bằng giá nhập (importPrice) + 20%

c.  Triệu gọi phương thức submitObject để gửi dữ liệu đã chuẩn hóa
d.  Kết thúc chương trình client*/

public class RMI_Object_ExportPrice {
    public static void normalizeProduct(Product o){
        o.setCode(o.getCode().toUpperCase());
        double exportPrice = o.getImportPrice() * 1.2;
        o.setExportPrice(exportPrice);
    }
    
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        ObjectService os = (ObjectService) Naming.lookup("rmi://203.162.10.109/RMIObjectService");
        //rec
        String studentCode = "B21DCCN731";
        String qAlias = "nuXrS00j";
        Serializable recMessage = os.requestObject(studentCode, qAlias);
        
        Product pd = (Product) recMessage;
        normalizeProduct(pd);
        Serializable product = (Serializable) pd;
        //res
        os.submitObject(studentCode, qAlias, product);
    }
}
