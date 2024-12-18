/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

import java.util.Collections;
import java.util.List;
import vn.medianews.CharacterService;
import vn.medianews.CharacterService_Service;

/**
 *
 * @author Admin
 */
public class WS_CharacterService_SapXepKyTu {
    public static void main(String[] args) {
        CharacterService_Service css = new CharacterService_Service();
        CharacterService port = css.getCharacterServicePort();
        //rec
        String studentCode = "B21DCCN731";
        String qCode = "gxFSJ2Fa";
        List<Integer> recList = port.requestCharacter(studentCode, qCode);

        Collections.sort(recList);
        //res
        port.submitCharacterCharArray(studentCode, qCode, recList);
    }
}
