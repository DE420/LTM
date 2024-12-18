/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

import java.util.ArrayList;
import java.util.List;
import vn.medianews.Customer;
import vn.medianews.ObjectService;
import vn.medianews.ObjectService_Service;

/**
 *
 * @author Admin
 */
public class WS_Object_TotalSpent {
    public static void main(String[] args) {
        ObjectService_Service oss = new ObjectService_Service();
        ObjectService port = oss.getObjectServicePort();
        //rec
        String studentCode = "B21DCCN731";
        String qCode = "WZiXWUkH";
        List<Customer> recList = port.requestListCustomer(studentCode, qCode);
        
        List<Customer> customers = new ArrayList<>();
        for(Customer i : recList){
            if(i.getTotalSpent() > 5000 && i.getPurchaseCount() >= 5){
                customers.add(i);
            }
        }
        //res
        port.submitListCustomer(studentCode, qCode, customers);
    }
}
