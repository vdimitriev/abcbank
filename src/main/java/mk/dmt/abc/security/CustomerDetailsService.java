package mk.dmt.abc.security;

import mk.dmt.abc.entity.CustomerEntity;
import mk.dmt.abc.exception.CustomerNotFoundException;
import mk.dmt.abc.repository.CustomerRepository;
import mk.dmt.abc.security.model.CustomerDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class CustomerDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CustomerDetails loadUserByUsername(String username) throws CustomerNotFoundException {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findByUsername(username);
        if(customerEntityOptional.isEmpty()) {
            throw new CustomerNotFoundException("Customer Not Found with username: " + username);
        }
        CustomerEntity customer = customerEntityOptional.get();
        return new CustomerDetails(customer.getUsername(), customer.getPassword());
    }
}
