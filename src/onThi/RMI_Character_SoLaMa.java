/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

import RMI.CharacterService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Admin
 */

/*[Mã câu hỏi (qCode): PtVq9hVb].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý chuỗi.
Giao diện từ xa:
public interface CharacterService extends Remote {
public String requestCharacter(String studentCode, String qCode) throws RemoteException;
public void submitCharacter(String studentCode, String qCode, String strSubmit) throws RemoteException;
}
Trong đó:
• Interface CharacterService được viết trong package RMI.
• Đối tượng cài đặt giao diện từ xa CharacterService được đăng ký với RegistryServer với tên là: RMICharacterService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với chuỗi được nhận từ RMI Server:
a. Triệu gọi phương thức requestCharacter để nhận chuỗi ngẫu nhiên từ server với định dạng: "Chuỗi số La Mã đầu vào".
b. Thực hiện chuyển đổi chuỗi số La Mã nhận được thành số thập phân (Decimal).
Quy tắc chuyển đổi: Các ký tự La Mã chính bao gồm: I=1, V=5, X=10, L=50, C=100, D=500, M=1000.
    Ví dụ: "MCMXCIV" -> 1994.
c. Triệu gọi phương thức submitCharacter để gửi số thập phân đã chuyển đổi trở lại server.
d. Kết thúc chương trình client.*/

public class RMI_Character_SoLaMa {
    public static int romanDigit(char c){
        if(c == 'I') return 1;
        if(c == 'V') return 5;
        if(c == 'X') return 10;
        if(c == 'L') return 50;
        if(c == 'C') return 100;
        if(c == 'D') return 500;
        return 1000;
    }
    
    public static int romanToDecimal(String s){
        int total = 0;
        int prevValue = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            int currValue = romanDigit(s.charAt(i));
            if(currValue < prevValue)
                total -= currValue;
            else
                total += currValue;
            prevValue = currValue;
        }
        return total;
    }
    
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        CharacterService cs = (CharacterService) Naming.lookup("rmi://203.162.10.109/RMICharacterService");
        //rec
        String studentCode = "B21DCCN731";
        String qCode = "PtVq9hVb";
        String recMessage = cs.requestCharacter(studentCode, qCode);
        
        String strSubmit = String.valueOf(romanToDecimal(recMessage));
        //res
        cs.submitCharacter(studentCode, qCode, strSubmit);
    }
}
