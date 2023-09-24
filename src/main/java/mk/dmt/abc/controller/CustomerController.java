package mk.dmt.abc.controller;

import mk.dmt.abc.model.Customer;
import mk.dmt.abc.model.CustomerResponse;
import mk.dmt.abc.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> register(@RequestBody Customer customer) {
        final CustomerResponse response = customerService.registerNewCustomer(customer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
