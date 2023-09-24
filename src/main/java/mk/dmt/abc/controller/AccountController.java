package mk.dmt.abc.controller;

import mk.dmt.abc.model.Account;
import mk.dmt.abc.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/overview")
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @GetMapping(value="{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getAccountOverview(@PathVariable String accountNumber) {
        final Account account = accountService.findAccount(accountNumber);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
