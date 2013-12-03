package bar.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import bar.model.Order;

@Service("orderService")
//@Scope("application")
public class OrderService {

    private List <Order>orders;

    public List<Order> getOrders() {
        return orders;
    }
   
    public void addOrder (String name, String email, String drink, String comments){
        Order order = new Order(name, email, drink, comments);
        orders.add(order);
    }
   
    @PostConstruct
    public void create (){
        orders = new ArrayList <Order> ();
    }
}