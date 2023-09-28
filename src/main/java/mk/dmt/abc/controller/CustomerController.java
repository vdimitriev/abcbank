package mk.dmt.abc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import mk.dmt.abc.annotation.CommonApiResponse;
import mk.dmt.abc.model.Customer;
import mk.dmt.abc.model.CustomerResponse;
import mk.dmt.abc.service.RateLimitedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class CustomerController {

    private final RateLimitedService rateLimitedService;

    public CustomerController(RateLimitedService rateLimitedService) {
        this.rateLimitedService = rateLimitedService;
    }

    @Operation(summary = "Register new customer.")
    @ApiResponse(responseCode = "201", description = "One new customer was successfully created")
    @CommonApiResponse
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> register(@Valid @RequestBody Customer customer) {
        final CustomerResponse response = rateLimitedService.registerNewCustomer(customer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
