/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

import java.util.ArrayList;
import java.util.List;
import vn.medianews.DataService;
import vn.medianews.DataService_Service;

/**
 *
 * @author Admin
 */
public class WS_DataService_PhanSoToiGian {
    public static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a%b);
    }
    
    public static void main(String[] args) {
        DataService_Service dss = new DataService_Service();
        DataService port = dss.getDataServicePort();
        //rec
        String studentCode = "B21DCCN731";
        String qCode = "gCPHrTyK";
        double recDouble = port.getDataDouble(studentCode, qCode);
        
        int tu = (int) (recDouble * 100);
        int ucln = gcd(tu, 100);
        List<Integer> data = new ArrayList<>();
        data.add(tu / ucln);
        data.add(100 / ucln);
        port.submitDataIntArray(studentCode, qCode, data);
    }
}
