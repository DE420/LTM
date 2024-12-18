/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Admin
 */

/*[Mã câu hỏi (qCode): QZgkUQSX].  Mật mã caesar, còn gọi là mật mã dịch chuyển, để giải mã thì mỗi ký tự nhận được sẽ được thay thế bằng một ký tự cách nó một đoạn s. Ví dụ: với s = 3 thì ký tự “A” sẽ được thay thế bằng ký tự “D”.
Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2207 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng chương trình client tương tác với server trên, sử dụng các luồng byte (DataInputStream/DataOutputStream) để trao đổi thông tin theo thứ tự:
a.	Gửi một chuỗi gồm mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;D68C93F7"
b.	Nhận lần lượt chuỗi đã bị mã hóa caesar và giá trị dịch chuyển s nguyên
c.	Thực hiện giải mã ra thông điệp ban đầu và gửi lên Server
d.	Đóng kết nối và kết thúc chương trình.*/

public class TCP_DataStream_MaHoaCeasar {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("203.162.10.109", 2207);
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        //req
        String reqMessage = "B21DCCN731;QZgkUQSX";
        dos.writeUTF(reqMessage);
        //rec
        String recMessage = dis.readUTF();
        int s = dis.readInt();
        
        String decrypt = "";
        for(char c : recMessage.toCharArray()){
            if(Character.isLowerCase(c)){
                c = (char) ((c - 'a' + s + 26) % 26 + 'a');
            }
            else
                c = (char) ((c - 'A' + s + 26) % 26 + 'A');
            decrypt += c;
        }
        //res
        dos.writeUTF(decrypt);
        
        dis.close();
        dos.close();
        client.close();
    }
}
