/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.HashMap;
import model.dao.ProductsDAO;

/**
 *
 * @author wuann_
 */
public class CartShop {

    private HashMap<String, OrderDetails> items;

    public CartShop() {
        this.items = new HashMap<>();
    }

    public CartShop(HashMap<String, OrderDetails> items) {
        this.items = items != null ? items : new HashMap<>();
    }

    public HashMap<String, OrderDetails> getItems() {
        return items;
    }

    public void setItems(HashMap<String, OrderDetails> items) {
        this.items = items;
    }

    public void addItems(String productId) {
        if(this.items.containsKey(productId)){
            increaseQuantity(productId);
        }else{
            Products x = new ProductsDAO().findProducts(productId);
            OrderDetails z = new OrderDetails();
            z.setProducts(x);
            z.setQuantity(1);
            z.setDiscount(x.getDiscount());
            z.setPrice(x.getPrice());
            this.items.putIfAbsent(productId, z);
        }
    }

    public void removeItems(String productId) {
        this.items.remove(productId);
    }

    public void increaseQuantity(String productId) {
        OrderDetails x = this.items.get(productId);
        if (x != null) {
            x.setQuantity(x.getQuantity() + 1);
        }else{
            addItems(productId);
        }
    }
    public void decreaseQuantity(String productId){
        OrderDetails x = this.items.get(productId);
        if(x != null && (x.getQuantity() > 1)){
            x.setQuantity(x.getQuantity() -1);
        }else{
            this.items.remove(productId);
        }
    }
    
    public int caculateTotalPrice() {
        int result = 0;
        for (OrderDetails i : this.items.values()) {
            result += i.getQuantity() * i.getPrice();
        }
        return result;

    }
}
