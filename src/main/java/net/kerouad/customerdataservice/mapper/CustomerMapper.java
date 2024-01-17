package net.kerouad.customerdataservice.mapper;

import net.kerouad.customerdataservice.dto.CustomerRequest;
import net.kerouad.customerdataservice.entities.Customer;
import net.kerouad.customerdataservice.stub.CustomerServiceOuterClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    private ModelMapper modelMapper = new ModelMapper();
    public Customer from(CustomerRequest customerRequest){
        return modelMapper.map(customerRequest, Customer.class);
    }

    public CustomerServiceOuterClass.Customer fromCustomer(Customer customer){
        return modelMapper.map(customer, CustomerServiceOuterClass.Customer.Builder.class).build();
    }

    public Customer fromGrpcCustomerRequest(CustomerServiceOuterClass.CustomerRequest customer){
        return modelMapper.map(customer, Customer.class);
    }
}

