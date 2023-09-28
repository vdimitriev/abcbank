package mk.dmt.abc.service;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import mk.dmt.abc.model.Account;
import mk.dmt.abc.model.Customer;
import mk.dmt.abc.model.Country;
import mk.dmt.abc.model.CustomerResponse;
import org.springframework.stereotype.Component;

@RateLimiter(name = "basic")
@Component
public class RateLimitedService {

    private final CustomerService customerService;

    private final AccountService accountService;
    private final CountryService countryService;


    public RateLimitedService(CustomerService customerService, AccountService accountService, CountryService countryService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.countryService = countryService;
    }

    public CustomerResponse registerNewCustomer(final Customer customer) {
        return customerService.registerNewCustomer(customer);
    }

    public Account findAccount(String iban) {
        return accountService.findAccount(iban);
    }

    public void addCountry(final Country country) {
        countryService.addCountry(country);
    }

    public void removeCountry(final String code) {
        countryService.removeCountry(code);
    }

}
