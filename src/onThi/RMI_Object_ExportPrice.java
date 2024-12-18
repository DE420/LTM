/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

import RMI.ObjectService;
import RMI.Product;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Admin
 */
public class RMI_Object_ExportPrice {
    public static void normalizeProduct(Product o){
        o.setCode(o.getCode().toUpperCase());
        double exportPrice = o.getImportPrice() * 1.2;
        o.setExportPrice(exportPrice);
    }
    
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        ObjectService os = (ObjectService) Naming.lookup("rmi://203.162.10.109/RMIObjectService");
        //rec
        String studentCode = "B21DCCN731";
        String qAlias = "nuXrS00j";
        Serializable recMessage = os.requestObject(studentCode, qAlias);
        
        Product pd = (Product) recMessage;
        normalizeProduct(pd);
        Serializable product = (Serializable) pd;
        //res
        os.submitObject(studentCode, qAlias, product);
    }
}
