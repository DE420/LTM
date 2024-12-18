/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

import RMI.DataService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class RMI_Data_ThuaSoNguyenTo {
    private static List<Integer> primeFactor(int n){
        List<Integer> factors = new ArrayList<>();
        while(n % 2 == 0){
            factors.add(2);
            n /= 2;
        }
        for(int i = 3; i <= Math.sqrt(n); i += 2){
            while(n % i == 0){
                factors.add(i);
                n /= i;
            }
        }
        if(n > 2)
            factors.add(n);
        return factors;
    }
    
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        DataService ds = (DataService) Naming.lookup("rmi://203.162.10.109/RMIDataService");
        //rec
        String studentCode = "B21DCCN731";
        String qCode = "AfVo0C5W";
        Object recData = ds.requestData(studentCode, qCode);
        
        int n = (Integer) recData;
        List<Integer> primeFactors = primeFactor(n);
        //res
        ds.submitData(studentCode, qCode, primeFactors);
    }
}
