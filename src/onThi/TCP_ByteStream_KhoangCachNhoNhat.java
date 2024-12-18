/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Admin
 */

/*[Mã câu hỏi (qCode): zUw7flJL].  Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client thực hiện kết nối tới server trên sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự: 
a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;FF49DC02"
b.	Nhận dữ liệu từ server là một chuỗi các giá trị số nguyên được phân tách nhau bởi ký tự ","
Ex: 1,3,9,19,33,20
c.	Thực hiện tìm giá trị khoảng cách nhỏ nhất của các phần tử nằm trong chuỗi và hai giá trị lớn nhất tạo nên khoảng cách đó. Gửi lên server chuỗi gồm "khoảng cách nhỏ nhất, số thứ nhất, số thứ hai". Ex: 1,19,20
d.	Đóng kết nối và kết thúc*/

public class TCP_ByteStream_KhoangCachNhoNhat {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("203.162.10.109", 2206);
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();
        //req
        String reqMessage = "B21DCCN731;zUw7flJL";
        os.write(reqMessage.getBytes());
        os.flush();
        //rec
        byte[] buf = new byte[1024];
        int byteRead = is.read(buf);
        String recStr = new String(buf, 0, byteRead);
        
        String[] numStr = recStr.split(",");
        List<Integer> nums = new ArrayList<>();
        for(String num : numStr){
            nums.add(Integer.parseInt(num));
        }
        
        Collections.sort(nums);
        int a = 0;
        int b = 0;
        int minGap = 99999;
        for(int i = nums.size() - 1; i > 0; i--){
            int gap = nums.get(i) - nums.get(i - 1);
            if(gap < minGap){
                a = nums.get(i - 1);
                b = nums.get(i);
                minGap = gap;
            }
        }
        //res
        String resMessage = minGap + "," + a + "," + b;
        os.write(resMessage.getBytes());
        os.flush();
        
        is.close();
        os.close();
        client.close();
    }
}
