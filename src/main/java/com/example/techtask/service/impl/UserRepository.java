package com.example.techtask.service.impl;

import com.example.techtask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {
    @Query(value = """
        select u.id, u.email, u.user_status from users u
        join techtask.orders o on u.id = o.user_id
        where (o.quantity * o.price) =
      (select max(o.quantity * o.price) from orders o
       where EXTRACT(YEAR FROM o.created_at) = 2003 and o.order_status = 'DELIVERED')
            """, nativeQuery = true)
    User findUserWithMaxTotalDeliveredIn2003();

    @Query(value = """
            select u.id, u.email, u.user_status from users u
            join techtask.orders o on u.id = o.user_id
            where o.order_status = 'PAID' and EXTRACT(YEAR FROM o.created_at) = 2010
            """, nativeQuery = true)
    List<User> findUsersWithPaidOrdersIn2010();
}
