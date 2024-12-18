/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onThi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import TCP.Student;

/**
 *
 * @author Admin
 */
public class TCP_ObjectStream_Student {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket client = new Socket("203.162.10.109", 2209);
        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        //req
        oos.writeObject("B21DCCN731;N23EXoxY");
        oos.flush();
        //rec
        Student stu = (Student) ois.readObject();
        
        float gpa = stu.getGpa();
        String gpaLetter = "";
        if(gpa >= 3.7) gpaLetter = "A";
        else if(gpa >= 3.0) gpaLetter = "B";
        else if(gpa >= 2.0) gpaLetter = "C";
        else if(gpa >= 1.0) gpaLetter = "D";
        else gpaLetter = "F";
        stu.setGpaLetter(gpaLetter);
        //res
        oos.writeObject(stu);
        oos.flush();

        ois.close();
        oos.close();
        client.close();
    }
}
