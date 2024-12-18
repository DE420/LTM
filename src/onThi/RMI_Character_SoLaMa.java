/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

import RMI.CharacterService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Admin
 */
public class RMI_Character_SoLaMa {
    public static int romanDigit(char c){
        if(c == 'I') return 1;
        if(c == 'V') return 5;
        if(c == 'X') return 10;
        if(c == 'L') return 50;
        if(c == 'C') return 100;
        if(c == 'D') return 500;
        return 1000;
    }
    
    public static int romanToDecimal(String s){
        int total = 0;
        int prevValue = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            int currValue = romanDigit(s.charAt(i));
            if(currValue < prevValue)
                total -= currValue;
            else
                total += currValue;
            prevValue = currValue;
        }
        return total;
    }
    
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        CharacterService cs = (CharacterService) Naming.lookup("rmi://203.162.10.109/RMICharacterService");
        //rec
        String studentCode = "B21DCCN731";
        String qCode = "PtVq9hVb";
        String recMessage = cs.requestCharacter(studentCode, qCode);
        
        String strSubmit = String.valueOf(romanToDecimal(recMessage));
        //res
        cs.submitCharacter(studentCode, qCode, strSubmit);
    }
}
