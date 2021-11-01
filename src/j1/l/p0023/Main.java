/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.l.p0023;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author win
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Fruit> lf = new ArrayList<>();
        lf.add(new Fruit("001", "Apple", 10, 1.5, "America"));
        lf.add(new Fruit("002", "Plum", 10, 3, "Vietnam"));
        lf.add(new Fruit("003", "Coconut", 20, 4, "VietNam"));
        lf.add(new Fruit("004", "Banana", 15, 2, "Thailand"));
        lf.add(new Fruit("005", "Orange", 10, 2.4, "Nhatban"));        
        Hashtable<String, ArrayList<Order>> ht = new Hashtable<>();
        while (true) {
            int choice = Manager.menu();
            switch (choice) {
                case 1:
                    Manager.createFruit(lf);
                    break;
                case 2:
                    Manager.viewOrder(ht);
                    break;
                case 3:
                    Manager.shopping(lf, ht);
                    break;
                case 8:
                    return;
            }
        }
    }

}
