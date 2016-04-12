
import com.studevs.util.RandomNumberGenerator;

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

    public static void main(String[] args) {

        RandomNumberGenerator rng = new RandomNumberGenerator(500000000);
        for (int i = 0; i < 100; i++) {

            System.out.println(rng.getGeneratedNumber());
        }
    }
}
