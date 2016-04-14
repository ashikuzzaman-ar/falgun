
import com.studevs.util.DeepCopy;
import com.studevs.util.ObjectCopy;

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

        Test t = new Test();
        t.setA(10);
        Test t2 = (Test) ObjectCopy.getCopyOf(t);
        t.setA(15);
        
        System.out.println("Value of t: "+t.getA());
        System.out.println("Value of t2: "+t2.getA());
    }
}
//You have to implement DeepCopy Only if you want to make a class cloneable
class Test implements DeepCopy{

    private int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
