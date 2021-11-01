/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.l.p0023;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

/**
 *
 * @author win
 */
public class Manager {
    public static int menu() {
        System.out.println("============= FRUIT SHOP =============");
        System.out.println("1. Create Fruit");
        System.out.println("2. View orders");        
        System.out.println("3. Shopping (for buyer)");        
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        int choice = Validation.checkInputIntLimit(1, 8);
        return choice;
    }
    
    public static void fruitWithIDFound(ArrayList<Fruit> lf, String ID){
        for (Fruit fruit : lf) {
            if (ID.equalsIgnoreCase(fruit.getID())) {
                fruit.print();
                return;
            }
        }
        System.out.println("This fruit doesn't exist"
                + "");
    }
    
    public static void createFruit(ArrayList<Fruit> lf) {
        displayListFruit(lf);
        while (true) {
            System.out.print("Enter fruit ID: ");
            String fruitID = Validation.checkInputFruitID(lf);
            if (fruitID.equals("")) {
                return;
            }
            System.out.print("Enter fruit name: ");
            String name = Validation.checkInputString();
            System.out.print("Enter price: ");
            double price = Validation.checkInputDouble(); 
            System.out.print("Enter quantity: ");
            int quantity = Validation.checkInputInt();
            System.out.print("Enter origin: ");
            String origin = Validation.checkInputString();
            
                lf.add(new Fruit(fruitID, name, quantity, price, origin));
                System.out.println("Add " + name + " successfully");                        
        }
    }
    
    public static void updateQuantityFruit(ArrayList<Fruit> lf) {
        if (lf.isEmpty()) {
            System.err.println("No item!");
            return;
        }
        displayListFruitwithID(lf);
        int quantity;
        while (true) {
            System.out.print("Enter fruit ID to update quantity: ");
            String ID = Validation.checkInputString();
            if(!Validation.checkIdExist(lf, ID)) {
                System.out.print("Enter quantity: ");
                quantity = Validation.checkInputInt();
                Fruit toUpdate = null;
                for (Fruit f : lf) {
                    if (f.getID().equals(ID)) {
                        f.setQuantity(f.getQuantity() + quantity);
                        toUpdate = f;
                    }
                }
                System.out.println("Update quantity of fruit with ID " + ID + " successfully:");
                System.out.printf("%-20s%-20s%-15.0f%-15s\n",
                        toUpdate.getName(), toUpdate.getOrigin(), toUpdate.getPrice(), toUpdate.getQuantity());
            } else {
                System.err.println("ID doesn't exist! You may want to create fruit first!");
            }
//            if (!Validation.checkInputYN()) return;
        }
    }
    
    public static void displayListOrder(ArrayList<Order> lo) {
        double total = 0;
        System.out.printf("%15s | %15s | %15s | %15s\n", "Product", "Quantity", "Price", "Amount");
        for (Order order : lo) {
            System.out.printf("%15s%15d%15.0f$%15.0f$\n", order.getFruitName(),
                    order.getQuantity(), order.getPrice(),
                    order.getPrice() * order.getQuantity());
            total += order.getPrice() * order.getQuantity();
            order.setTotal(total);
        }
        System.out.println("Total: " + total);
    }
    
    public static void viewOrder(Hashtable<String, ArrayList<Order>> ht) {
        if (ht.keySet().isEmpty()) {
            System.err.println("No order have made yet");
            System.out.println("");
        }
        for (String name: ht.keySet()) {
            System.out.println("Customer: " + name);
            ArrayList<Order> or = ht.get(name);
            displayListOrder(or);
        }
    }
    
    public static void displayListFruit(ArrayList<Fruit> lf) {
        int countItem = 1; 
        System.out.printf("%-10s%-20s%-20s%-15s%-15s\n", "Item", "Fruit name", "Origin", "Price($)", "Quantity");    
        for (Fruit fruit : lf) {
            if (fruit.getQuantity() != 0) {
                System.out.printf("%-10d%-20s%-20s%-15.0f%-15s\n", countItem++,
                        fruit.getName(), fruit.getOrigin(), fruit.getPrice(), fruit.getQuantity());
            }
        }
    }
    
    public static void displayListFruitwithID(ArrayList<Fruit> lf) {
        System.out.printf("%-10s%-20s%-20s%-15s%-15s\n", "ID", "Fruit name", "Origin", "Price($)", "Quantity");    
        for (Fruit fruit : lf) {
            if (fruit.getQuantity() != 0) {
                System.out.printf("%-10s%-20s%-20s%-15.0f%-15s\n", fruit.getID(),
                        fruit.getName(), fruit.getOrigin(), fruit.getPrice(), fruit.getQuantity());
            }
        }
    }
    
    public static Fruit getFruitByItem(ArrayList<Fruit> lf, int item) {
        int countItem = 1;
        for (Fruit fruit : lf) {
            if (fruit.getQuantity() != 0) {
                countItem++;
            }
            if (countItem - 1 == item) {
                return fruit;
            }
        }
        return null;
    }
    
    
    public static void shopping(ArrayList<Fruit> lf, Hashtable<String, ArrayList<Order>> ht) {
        if (lf.isEmpty()) {
            System.err.println("No item!");
            return;
        }
        ArrayList<Order> lo = new ArrayList<>();
        displayListFruit(lf);
        while (true) {
            System.out.print("Enter item: ");
            int item = Validation.checkInputIntLimit(1, lf.size());
            Fruit fruit = getFruitByItem(lf, item);
            System.out.println("Enter quantity: ");
            int quantity = Validation.checkInputIntLimit(1, fruit.getQuantity());
            fruit.setQuantity(fruit.getQuantity() - quantity);
            if(!Validation.checkItemExist(lo, fruit.getID())) {
                updateOrder(lo, fruit.getID(), quantity);
            } else {
                System.out.println("You finished your order!");
                System.out.println("Order details: ");
                System.out.printf("%-20s%-20s%-15s\n", "Fruit name", "Origin", "Price($)");
                System.out.printf("%-20s%-20s%-15.0f\n", fruit.getName(), fruit.getOrigin(), fruit.getPrice());
                lo.add(new Order(fruit.getID(), fruit.getName(), quantity, fruit.getPrice()));
            }
            if (!Validation.checkInputYN()) break;
            System.out.println("");
        }
        displayListOrder(lo);
        System.out.println("Enter customer's name: ");
        String name = Validation.checkInputString(); 
        try {
            ArrayList<Order> o = ht.get(name);
            for (Order n : lo) {
            o.add(n);
        }
        } catch (NullPointerException e) {
            ht.put(name, lo);
        }

    }
    
    //If order exists, update order
    public static void updateOrder(ArrayList<Order> lo, String id, int quantity) {
        for (Order order : lo) {
            if (order.getFruitID().equalsIgnoreCase(id)) {
                order.setQuantity(order.getQuantity() + quantity);
                return;
            }
        }
    }
    
    
    
    
    
    
}
