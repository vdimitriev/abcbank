package mk.dmt.abc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import mk.dmt.abc.annotation.CommonApiResponse;
import mk.dmt.abc.model.Account;
import mk.dmt.abc.service.RateLimitedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/overview")
public class AccountController {
    private final RateLimitedService rateLimitedService;

    public AccountController(RateLimitedService rateLimitedService) {
        this.rateLimitedService = rateLimitedService;
    }

    @Operation(summary = "Customer account overview.")
    @ApiResponse(responseCode = "200", description = "Customer can see, own account details.")
    @CommonApiResponse
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getAccountOverview(@PathVariable String accountNumber) {
        final Account account = rateLimitedService.findAccount(accountNumber);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
