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
