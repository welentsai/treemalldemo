package id.welen.demo.dao;

import java.util.ArrayList;
import java.util.Collection;

public class Orders {
    Collection<Order> orders = new ArrayList<>();

    public Orders(Collection<Order> orders) {
        this.orders = orders;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }
}
