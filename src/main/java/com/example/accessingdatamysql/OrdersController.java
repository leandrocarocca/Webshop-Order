package com.example.accessingdatamysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Objects;

@RestController
public class OrdersController {
    private final OrdersRepo repo;
    private final RestTemplate restTemplate;
    private final String CUSTOMER_API_BASE_URL = "http://customer-service:8081";
    private final String PRODUCT_API_BASE_URL = "http://product-service:8082";


    private static final Logger log = LoggerFactory.getLogger(OrdersController.class);

    @Autowired
    public OrdersController(OrdersRepo repo, RestTemplate restTemplate) {
        this.repo = repo;
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/orders")
    public List<Orders> getAllOrders() {
        List<Orders> listOfOrders = repo.findAll();
        if (listOfOrders.isEmpty()) {
            log.warn("The list of orders was empty");
        }

        // API call to Customer API to fetch customers
        String customerApiUrl = CUSTOMER_API_BASE_URL + "/customers";
        System.out.println(customerApiUrl);
        CustomerDTO[] customers = restTemplate.getForObject(customerApiUrl, CustomerDTO[].class);

        // API call to Product API to fetch products
        String productApiUrl = PRODUCT_API_BASE_URL + "/items";
        System.out.println(productApiUrl);
        ProductDTO[] products = restTemplate.getForObject(productApiUrl, ProductDTO[].class);

        return listOfOrders;
    }

    @RequestMapping("/orders/{customerId}")
    public List<Orders> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<Orders> listOfOrders = repo.findAll();

        // API call to Customer API
        String customerApiUrl = CUSTOMER_API_BASE_URL + "/customers/" + customerId;
        CustomerDTO customer = restTemplate.getForObject(customerApiUrl, CustomerDTO.class);

        // API call to Product API
        String productApiUrl = PRODUCT_API_BASE_URL + "/items";
        ProductDTO[] products = restTemplate.getForObject(productApiUrl, ProductDTO[].class);

        return listOfOrders.stream()
                .filter(orders -> Objects.equals(orders.getCustomerId(), customerId))
                .toList();
    }
}

