package mk.dmt.abc.mapper;

import mk.dmt.abc.algorithm.Mod97_10;
import mk.dmt.abc.entity.AccountEntity;
import mk.dmt.abc.entity.CustomerEntity;
import mk.dmt.abc.entity.DocumentEntity;
import mk.dmt.abc.model.Customer;
import mk.dmt.abc.model.Document;
import mk.dmt.abc.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    private final AccountService accountService;

    private final Mod97_10 mod9710;

    public CustomerMapper(AccountService accountService, Mod97_10 mod9710) {
        this.accountService = accountService;
        this.mod9710 = mod9710;
    }

    public CustomerEntity apply(Customer customerModel) {
        final CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setAddress(customerModel.getAddress());
        customerEntity.setName(customerModel.getName());
        customerEntity.setDateOfBirth(customerModel.getDateOfBirth());
        customerEntity.setUsername(customerModel.getUsername());
        customerEntity.setCustomerId(UUID.randomUUID().toString());
        customerEntity.setPassword(generatePassword());
        customerEntity.setCountry(customerModel.getCountry());
        final DocumentEntity documentEntity = new DocumentEntity();
        final Document documentModel = customerModel.getDocument();
        documentEntity.setDocumentId(UUID.randomUUID().toString());
        documentEntity.setDocumentType(documentModel.getType());
        documentEntity.setDocumentNumber(documentModel.getNumber());
        documentEntity.setIssueCountry(documentModel.getCountry());
        documentEntity.setIssueDate(documentModel.getIssued());
        documentEntity.setExpiryDate(documentModel.getExpiring());
        customerEntity.setDocument(documentEntity);
        final AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(UUID.randomUUID().toString());
        accountEntity.setIban(generateIbanNumber());
        accountEntity.setType("Saving account");
        accountEntity.setCurrency("EUR");
        accountEntity.setBalance(BigDecimal.ZERO);
        accountEntity.setCreated(Instant.now());
        customerEntity.setAccount(accountEntity);
        return customerEntity;
    }

    public String generatePassword() {
        final String password = new Random()
                .ints(10, 33, 122)
                .mapToObj(i -> String.valueOf((char)i))
                .collect(Collectors.joining());
        return password;
    }

    private String generateIbanNumber() {
        String accountNumber = accountService.findNextAccountNumber();
        String ibanNumber = "NL" + mod9710.compute(accountNumber) + "ABCB" + accountNumber;
        return ibanNumber;
    }
}
