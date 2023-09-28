package mk.dmt.abc.service;

import mk.dmt.abc.entity.CountryEntity;
import mk.dmt.abc.entity.CustomerEntity;
import mk.dmt.abc.exception.CountryNotAllowedException;
import mk.dmt.abc.exception.UsernameAlreadyExistsException;
import mk.dmt.abc.mapper.CustomerMapper;
import mk.dmt.abc.model.Customer;
import mk.dmt.abc.model.CustomerResponse;
import mk.dmt.abc.repository.CountryRepository;
import mk.dmt.abc.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CountryRepository countryRepository;

    private final CustomerMapper customerMapper;

    public CustomerService(final CustomerRepository customerRepository, CountryRepository countryRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.countryRepository = countryRepository;
        this.customerMapper = customerMapper;
    }


    public CustomerResponse registerNewCustomer(final Customer customer) {
        final Optional<CustomerEntity> customerEntityOptional = customerRepository.findByUsername(customer.getUsername());
        if(customerEntityOptional.isPresent()) {
            throw new UsernameAlreadyExistsException(String.format("Customer with username %s already exists in the database", customer.getUsername()));
        }
        if(!allowedCountry(customer.getCountry())) {
            throw new CountryNotAllowedException(String.format("Not allowed to register new customers from country %s", customer.getCountry()));
        }
        final CustomerEntity customerEntity = customerMapper.apply(customer);
        final CustomerEntity responseEntity = customerRepository.save(customerEntity);
        return new CustomerResponse(responseEntity.getUsername(), responseEntity.getPlainPassword());
    }

    private boolean allowedCountry(String customerCountry) {
        final List<CountryEntity> countries = countryRepository.findAll();
        for(CountryEntity country : countries) {
            if(country.getCode().equals(customerCountry)) return true;
        }
        return false;
    }

}
