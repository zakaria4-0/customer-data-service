package net.kerouad.customerdataservice.web;

import net.kerouad.customerdataservice.entities.Customer;
import net.kerouad.customerdataservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerRestController {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/save")
    public void save(@RequestBody Customer customer){
        customerRepository.save(customer);
    }

    @GetMapping("/customers")
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer findById(@PathVariable Long id){
        return customerRepository.findById(id).get();
    }

    @GetMapping("/search/{key}")
    public List<Customer> search(@PathVariable String key){
        return customerRepository.findCustomerByNameContains(key);
    }
}
