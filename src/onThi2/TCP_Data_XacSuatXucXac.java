/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Admin
 */

/*[Mã câu hỏi (qCode): CwrlYSjG].  Một chương trình servercho phép kết nối qua TCP tại cổng 2207 (hỗ trợ thời gian liên lạc tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng chương trình client tương tác với server bằng các byte stream (DataInputStream/DataOutputStream) để trao đổi thông tin theo trình tự sau:

a. Gửi một chuỗi chứa mã sinh viên và mã câu hỏi ở định dạng "studentCode;qCode". Ví dụ: "B10DCCN000;0D135D6A".

b. Nhận từ server một số nguyên n, là số lần tung xúc xắc. Ví dụ: Nếu bạn nhận được n = 21 từ máy chủ, có nghĩa bạn sẽ nhận giá trị tung xúc xắc 21 lần.

b. Nhận từ server các giá trị sau mỗi lần tung xúc xắc. Ví dụ: Server gửi lần lượt 21 giá trị là 1,6,4,4,4,3,2,6,3,4,5,4,5,2,4,5,4,6,1,5,5

c. Tính xác suất xuất hiện của các giá trị [1,2,3,4,5,6] khi tung xúc sắc và gửi lần lượt xác suất này (dưới dạng float) lên server theo đúng thứ tự. Ví dụ gửi lên server lần lượt 6 giá trị là 0.0952381, 0.0952381, 0.0952381, 0.33333334, 0.232209524, 0.14285715

d. Đóng kết nối và kết thúc chương trình.*/

public class TCP_Data_XacSuatXucXac {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("203.162.10.109", 2207);
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        //req
        String reqStr = "B21DCCN039;CwrlYSjG";
        dos.writeUTF(reqStr);
        //rec
        int n = dis.readInt();
        float cnt1 = 0;
        float cnt2 = 0;
        float cnt3 = 0;
        float cnt4 = 0;
        float cnt5 = 0;
        float cnt6 = 0;
        for(int i = 0; i < n; i++){
            int a = dis.readInt();
            if(a == 1) cnt1 += 1;
            else if(a == 2) cnt2 += 1;
            else if(a == 3) cnt3 += 1;
            else if(a == 4) cnt4 += 1;
            else if(a == 5) cnt5 += 1;
            else cnt6 += 1;
        }
        //res
        dos.writeFloat(cnt1 / (float) n);
        dos.writeFloat(cnt2 / (float) n);
        dos.writeFloat(cnt3 / (float) n);
        dos.writeFloat(cnt4 / (float) n);
        dos.writeFloat(cnt5 / (float) n);
        dos.writeFloat(cnt6 / (float) n);
        
        dis.close();
        dos.close();
        client.close();
    }
}
