package net.kerouad.customerdataservice.web;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import net.kerouad.customerdataservice.entities.Customer;
import net.kerouad.customerdataservice.mapper.CustomerMapper;
import net.kerouad.customerdataservice.repository.CustomerRepository;
import net.kerouad.customerdataservice.stub.CustomerServiceGrpc;
import net.kerouad.customerdataservice.stub.CustomerServiceOuterClass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class CustomerGRPCService extends CustomerServiceGrpc.CustomerServiceImplBase {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public void getAllCustomers(CustomerServiceOuterClass.GetAllCustomersRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomersResponse> responseObserver) {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerServiceOuterClass.Customer> grpcCustomer =
                customers.stream()
                        .map(customerMapper::fromCustomer).collect(Collectors.toList());

        CustomerServiceOuterClass.GetCustomersResponse customersResponse=
                CustomerServiceOuterClass.GetCustomersResponse.newBuilder()
                        .addAllCustomers(grpcCustomer)
                        .build();
        responseObserver.onNext(customersResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getCustomerById(CustomerServiceOuterClass.GetCustomerByIdRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomerByIdResponse> responseObserver) {
        Customer customerEntity = customerRepository.findById(request.getCustomerId()).get();
        CustomerServiceOuterClass.GetCustomerByIdResponse response =
                CustomerServiceOuterClass.GetCustomerByIdResponse.newBuilder()
                        .setCustomer(customerMapper.fromCustomer(customerEntity))
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void saveCustomer(CustomerServiceOuterClass.SaveCustomerRequest request, StreamObserver<CustomerServiceOuterClass.SaveCustomerResponse> responseObserver) {
        Customer customer = customerMapper.fromGrpcCustomerRequest(request.getCustomer());
        Customer savedCustomer = customerRepository.save(customer);
        CustomerServiceOuterClass.SaveCustomerResponse response=
                CustomerServiceOuterClass.SaveCustomerResponse.newBuilder()
                        .setCustomer(customerMapper.fromCustomer(savedCustomer))
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
