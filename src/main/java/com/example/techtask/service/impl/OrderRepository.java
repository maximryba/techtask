package com.example.techtask.service.impl;

import com.example.techtask.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = """
            select o.id, o.product_name, o.price, o.quantity, o.user_id, o.created_at,
                   o.order_status from orders o
            where o.created_at
                      = (select max(o.created_at) from orders o where o.quantity > 1)
                      """, nativeQuery = true)
    Order findNewestOrderWithMoreThanOneItem();

    @Query(value = """
            select o.id, o.product_name, o.price, o.quantity, o.user_id, o.created_at,
                   o.order_status from orders o
                                           join users u on u.id = o.user_id
            where u.user_status = 'ACTIVE'
            order by o.created_at
            """, nativeQuery = true)
    List<Order> findOrdersByActiveUsersSortedByDate();
}
