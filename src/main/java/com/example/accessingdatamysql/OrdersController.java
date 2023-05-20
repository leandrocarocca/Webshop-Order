package com.example.accessingdatamysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdersController {
    private final OrdersRepo repo;
    private static final Logger log = LoggerFactory.getLogger(OrdersController.class);
    OrdersController(OrdersRepo repo){
        this.repo = repo;
    }

    @RequestMapping("/orders")
    public List<Orders> getAllOrders(){
        List<Orders> listOfOrders = repo.findAll();
        if(listOfOrders.isEmpty()){
            log.warn("The list of orders was empty");
        }
        return repo.findAll();
    }

    /*@RequestMapping("/orders/{customerId}")
    public List<Orders> getOrdersByCustomerId(@PathVariable Long customerId){
        List<Orders> listOfOrders = repo.findAll();
        return listOfOrders.stream()
                .filter(orders -> Objects.equals(orders.getCustomer().getId(), customerId))
                .toList();
    }*/
}
