package com.example.accessingdatamysql;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepo extends JpaRepository<Orders, Long> {
    List<Orders> findOrdersByCustomer_Id(Long customerId);

}
