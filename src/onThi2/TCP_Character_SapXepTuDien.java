/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Admin
 */

/*[Mã câu hỏi (qCode): Dc7xMHEI].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng một chương trình client thực hiện kết nối tới server và sử dụng luồng ký tự (BufferedWriter/BufferedReader) để trao đổi thông tin theo kịch bản sau:
a. Gửi một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;X1107ABC".
b. Nhận từ server một chuỗi ngẫu nhiên chứa nhiều từ, các từ phân tách bởi khoảng trắng.
c. Thực hiện các bước xử lý:
    Bước 1: Tách chuỗi thành các từ dựa trên khoảng trắng.
    Bước 2: Sắp xếp các từ theo thứ tự từ điển (có phân biệt chữ cái hoa thường).
d. Gửi lại chuỗi đã sắp xếp theo thứ tự từ điển lên server.

e. Đóng kết nối và kết thúc chương trình.*/

public class TCP_Character_SapXepTuDien {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("203.162.10.109", 2208);
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        //req
        String reqStr = "B21DCCN039;Dc7xMHEI";
        bw.write(reqStr);
        bw.newLine();
        bw.flush();
        //rec
        String recStr = br.readLine();
        
        List<String> strList = new ArrayList<>();
        strList.addAll(Arrays.asList(recStr.split(" ")));
        Collections.sort(strList);
        //res
        String resStr = String.join(" ", strList);
        bw.write(resStr);
        bw.newLine();
        bw.flush();
        
        br.close();
        bw.close();
        client.close();
    }
}
