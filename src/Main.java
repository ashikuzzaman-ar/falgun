
import com.studevs.falgun.io.ReadTextFile;
import java.io.File;
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ashik
 */
public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {

        String[] word = null;
        ReadTextFile rtf = new ReadTextFile(new File("").getAbsolutePath() + "/src/Main.java");
        if (rtf.hasNext()) {

            word = rtf.readNextWordTo(10);
        }
        
        rtf.close();

        for (String word1 : word) {
            System.out.println(word1);
        }
    }
}
//You have to implement DeepCopy Only if you want to make a class cloneable

class Test implements Serializable {

    private int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
