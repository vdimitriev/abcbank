package mk.dmt.abc.service;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import mk.dmt.abc.entity.CustomerEntity;
import mk.dmt.abc.mapper.CustomerMapper;
import mk.dmt.abc.model.Customer;
import mk.dmt.abc.model.CustomerResponse;
import mk.dmt.abc.repository.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public CustomerService(final CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    public CustomerResponse registerNewCustomer(final Customer customer) {
        final CustomerEntity customerEntity = customerMapper.apply(customer);
        final CustomerEntity responseEntity = customerRepository.save(customerEntity);
        return new CustomerResponse(responseEntity.getUsername(), responseEntity.getPassword());
    }

}
