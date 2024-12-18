/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */

/*[Mã câu hỏi (qCode): hX2zhWcT].  Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). 
Yêu cầu xây dựng chương trình client thực hiện kết nối tới server trên sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự: 
a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;C64967DD"
b.	Nhận dữ liệu từ server là một chuỗi gồm các giá trị nguyên được phân tách với nhau bằng  "|"
Ex: 2|5|9|11
c.	Thực hiện tìm giá trị tổng của các số nguyên trong chuỗi và gửi lên server
Ex: 27
d.	Đóng kết nối và kết thúc*/

public class TCP_Byte_TongChuoi {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("203.162.10.109", 2206);
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();
        //req
        String reqMessage = "B21DCCN039;hX2zhWcT";
        os.write(reqMessage.getBytes());
        os.flush();
        //rec
        byte[] buf = new byte[1024];
        int byteRead = is.read(buf);
        String recStr = new String(buf, 0, byteRead);
        
        String[] numStr = recStr.split("\\|");
        List<Integer> nums = new ArrayList<>();
        for(String num : numStr){
            nums.add(Integer.parseInt(num));
        }
        int sum = 0;
        for(int i : nums){
            sum += i;
        }
        String resStr = String.valueOf(sum);
        //res
        os.write(resStr.getBytes());
        os.flush();
        
        is.close();
        os.close();
        client.close();
    }
}
