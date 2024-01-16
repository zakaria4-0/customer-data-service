package net.kerouad.customerdataservice.mapper;

import net.kerouad.customerdataservice.dto.CustomerRequest;
import net.kerouad.customerdataservice.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    private ModelMapper modelMapper = new ModelMapper();
    public Customer from(CustomerRequest customerRequest){
        return modelMapper.map(customerRequest, Customer.class);
    }
}
