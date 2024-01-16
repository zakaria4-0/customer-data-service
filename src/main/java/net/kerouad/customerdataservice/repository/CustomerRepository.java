package net.kerouad.customerdataservice.repository;

import net.kerouad.customerdataservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findCustomerByNameContains(String key);
}
