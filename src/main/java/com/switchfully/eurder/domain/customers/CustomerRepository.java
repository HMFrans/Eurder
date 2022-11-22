package com.switchfully.eurder.domain.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findCustomerByEmailAddress(String emailAddress);

    Boolean existsByEmailAddress(String emailAddress);
}
