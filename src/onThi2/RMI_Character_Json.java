/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi2;

import RMI.CharacterService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Admin
 */

/*[Mã câu hỏi (qCode): U6dIYKTu].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý chuỗi.
Giao diện từ xa:
public interface CharacterService extends Remote {
public String requestCharacter(String studentCode, String qCode) throws RemoteException;
public void submitCharacter(String studentCode, String qCode, String strSubmit) throws RemoteException;
}
Trong đó:
•	Interface CharacterService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa CharacterService được đăng ký với RegistryServer với tên là: RMICharacterService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với chuỗi được nhận từ RMI Server:
a. Triệu gọi phương thức requestCharacter để nhận chuỗi JSON ngẫu nhiên từ server với định dạng: "Chuỗi JSON đầu vào".
b. Phân tích cú pháp chuỗi JSON nhận được và trích xuất các cặp key: value dựa trên vị trí của chúng:
•	Các cặp key: value ở vị trí chẵn sẽ được đưa vào chuỗi đầu tiên.
•	Các cặp key: value ở vị trí lẻ sẽ được đưa vào chuỗi thứ hai.
•	Hai chuỗi kết quả sẽ được nối với nhau và phân tách bởi dấu ;
Ví dụ: Chuỗi JSON ban đầu {"name": "Alice", "age": 25, "city": "Wonderland", "country": "Fictionland"} -> Kết quả trích xuất: "name: Alice, city: Wonderland; age: 25, country: Fictionland".
c. Triệu gọi phương thức submitCharacter để gửi chuỗi kết quả trích xuất đã được định dạng trở lại server.
d. Kết thúc chương trình client.*/

public class RMI_Character_Json {
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        CharacterService cs = (CharacterService) Naming.lookup("rmi://203.162.10.109/RMICharacterService");
        //rec
        String studentCode = "B21DCCN039";
        String qCode = "U6dIYKTu";
        String recStr = cs.requestCharacter(studentCode, qCode);
        
        recStr = recStr.replaceAll("[\\{\\\"\\}]", "");
        String[] keyVals = recStr.split(", ");
        StringBuilder chanStr = new StringBuilder();
        StringBuilder leStr = new StringBuilder();
        for(int i = 0; i < keyVals.length; i++){
            if(i % 2 == 0)
                chanStr.append(keyVals[i] + ", ");
            else
                leStr.append(keyVals[i] + ", ");
        }
        chanStr.delete(chanStr.length() - 2, chanStr.length());
        leStr.delete(leStr.length() - 2, leStr.length());
        String strSubmit = chanStr.toString() + "; " + leStr.toString();
        //res
        cs.submitCharacter(studentCode, qCode, strSubmit);
    }
}
