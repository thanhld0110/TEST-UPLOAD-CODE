/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.l.p0023;

/**
 *
 * @author win
 */
public class Fruit {
    private String ID;
   private String name;
   private int quantity;
   private double price;
   private String origin;

    public Fruit(String ID, String name, int quantity, double price, String origin) {
        this.ID = ID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.origin = origin;
    }

    public Fruit() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void print(){
        System.out.printf("%-15s %-15s%-15s\n", name, quantity, price);
    }
   
}



