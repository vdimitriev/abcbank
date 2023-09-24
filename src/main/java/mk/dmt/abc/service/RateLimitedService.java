package mk.dmt.abc.service;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import mk.dmt.abc.model.Account;
import mk.dmt.abc.model.Customer;
import mk.dmt.abc.model.CustomerResponse;
import org.springframework.stereotype.Component;

@RateLimiter(name = "basic")
@Component
public class RateLimitedService {

    private final CustomerService customerService;

    private final AccountService accountService;


    public RateLimitedService(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    public CustomerResponse registerNewCustomer(final Customer customer) {
        return customerService.registerNewCustomer(customer);
    }

    public Account findAccount(String iban) {
        return accountService.findAccount(iban);
    }

}
