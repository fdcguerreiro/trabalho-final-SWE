package com.finalproject.order.order.dao;

import com.finalproject.order.order.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query("FROM Customer c WHERE c.phone_number = :search_term")
    Customer findCustomer(@Param("search_term") String searchTerm);
}
