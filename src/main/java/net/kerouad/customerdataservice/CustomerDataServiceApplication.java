package net.kerouad.customerdataservice;

import net.kerouad.customerdataservice.entities.Customer;
import net.kerouad.customerdataservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class CustomerDataServiceApplication implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(CustomerDataServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        customerRepository.save(new Customer(null, "Ahmed", "Ahmed@gmail.com"));
        customerRepository.save(new Customer(null, "Samir", "Samir@gmail.com"));
        customerRepository.save(new Customer(null, "Kamal", "Kamal@gmail.com"));
        List<Customer> customers = customerRepository.findAll();
        customers.forEach(System.out::println);
        System.out.println("----------------------");
        Customer customer1 = customerRepository.findById(1L).get();
        System.out.println(customer1);
        System.out.println("------------------------");
        List<Customer> customers1 = customerRepository.findCustomerByNameContains("a");
        customers1.forEach(System.out::println);

    }
}
