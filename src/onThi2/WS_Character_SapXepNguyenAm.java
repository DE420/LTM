/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi2;

import java.util.Comparator;
import java.util.List;
import vn.medianews.CharacterService;
import vn.medianews.CharacterService_Service;

/**
 *
 * @author Admin
 */

/*[Mã câu hỏi (qCode): 2kbuW7re].  Một dịch vụ web được định nghĩa và mô tả trong tệp CharacterService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực hiện các công việc sau:
a. Triệu gọi phương thức requestStringArray với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một danh sách chuỗi (List<String>) từ server.
b. Sắp xếp các chuỗi trong danh sách theo số lượng nguyên âm tăng dần. Nếu hai chuỗi có cùng số lượng nguyên âm, giữ nguyên thứ tự xuất hiện ban đầu của chúng trong mảng.
c. Triệu gọi phương thức submitCharacterStringArray(String studentCode, String qCode, List<String> data) để gửi danh sách chuỗi đã sắp xếp trở lại server.
Ví dụ: Nếu mảng chuỗi nhận được từ phương thức requestCharacter là ["apple", "kiwi", "banana", "pear"], số lượng nguyên âm trong các từ là:
•	"apple" có 2 nguyên âm
•	"kiwi" có 2 nguyên âm
•	"pear" có 2 nguyên âm
•	"banana" có 3 nguyên âm
Sau khi sắp xếp theo số lượng nguyên âm tăng dần, kết quả sẽ là ["apple", "kiwi", "pear", , "banana"]. Danh sách này sẽ được gửi lại server qua phương thức submitCharacter.
d. Kết thúc chương trình client.*/

//dùng recList.sort((String o1, String o2) -> vowelCnt(o1) - vowelCnt(o2)); hoặc Comparator

public class WS_Character_SapXepNguyenAm {
    public static int vowelCnt(String s){
        int cnt = 0;
        for(char c : s.toCharArray()){
            if(c == 'u' || c == 'e' || c == 'o' || c == 'a' || c == 'i' || c == 'U' || c == 'E' || c == 'O' || c == 'A' || c == 'I')
                cnt++;
        }
        return cnt;
    }
    
    public static void main(String[] args) {
        CharacterService_Service css = new CharacterService_Service();
        CharacterService port = css.getCharacterServicePort();
        //rec
        String studentCode = "B21DCCN039";
        String qCode = "2kbuW7re";
        List<String> recList = port.requestStringArray(studentCode, qCode);
        
        //recList.sort((String o1, String o2) -> vowelCnt(o1) - vowelCnt(o2));
        recList.sort(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return vowelCnt(o1) - vowelCnt(o2);
            }
            
        });
        //res
        port.submitCharacterStringArray(studentCode, qCode, recList);
    }
}
