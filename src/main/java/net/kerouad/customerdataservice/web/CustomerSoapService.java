package net.kerouad.customerdataservice.web;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;
import net.kerouad.customerdataservice.dto.CustomerRequest;
import net.kerouad.customerdataservice.entities.Customer;
import net.kerouad.customerdataservice.mapper.CustomerMapper;
import net.kerouad.customerdataservice.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@WebService(serviceName = "CustomerService")
public class CustomerSoapService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @WebMethod
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @WebMethod
    public Customer findCustomerById(@WebParam(name = "id") Long id){
        return customerRepository.findById(id).get();
    }

    @WebMethod
    public Customer save(@WebParam CustomerRequest customerRequest){
        Customer customer = customerMapper.from(customerRequest);
        return customerRepository.save(customer);
    }
}
