/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author Admin
 */

/*[Mã câu hỏi (qCode): OSosTfy5].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng một chương trình client thực hiện kết nối tới server và sử dụng luồng ký tự (BufferedWriter/BufferedReader) để trao đổi thông tin theo kịch bản sau:
a. Gửi một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;A1B2C3D4".
b. Nhận từ server một chuỗi ngẫu nhiên.
c. Xử lý chuỗi đã nhận theo các bước sau:
    Bước 1: Tìm từ dài nhất trong trong chuỗi ngẫu nhiên (từ là chuỗi con phân tách bởi khoảng trắng).
    Bước 2: Xác định vị trí bắt đầu của từ dài nhất đó trong chuỗi ban đầu.
d. Gửi lần lượt hai giá trị lên server:
   - Từ dài nhất xuất hiện trong chuỗi.
   - Vị trí bắt đầu của từ trong chuỗi ban đầu.

e. Đóng kết nối và kết thúc chương trình.*/

public class TCP_CharacterStream_TuDaiNhat {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("203.162.10.109", 2208);
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        //req
        bw.write("B21DCCN731;OSosTfy5");
        bw.newLine();
        bw.flush();
        //rec
        String recMessage = br.readLine();
        
        String[] words = recMessage.split(" ");
        String longest = "";
        for(String word : words){
            if(word.length() > longest.length()){
                longest = word;
            }
        }
        int index = recMessage.indexOf(longest);
        
        //res
        bw.write(longest);
        bw.newLine();
        bw.flush();
        bw.write(Integer.toString(index));
        bw.newLine();
        bw.flush();
        
        br.close();
        bw.close();
        client.close();
    }
}
