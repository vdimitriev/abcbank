package mk.dmt.abc;

import mk.dmt.abc.entity.AccountEntity;
import mk.dmt.abc.repository.AccountRepository;
import mk.dmt.abc.service.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = AbcApplication.class)
@ActiveProfiles("test-containers-flyway")
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @AfterEach
    public void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    void findFirstAccountNumberTest() {
        String nextAccountNumber = accountService.findNextAccountNumber();
        assertEquals("0000000001", nextAccountNumber);
    }

    @Test
    void findNextAccountNumberTest() {
        final AccountEntity accountEntity = new AccountEntity();
        accountEntity.setCreated(Instant.now());
        accountEntity.setType("Savings account");
        accountEntity.setCurrency("EUR");
        accountEntity.setAccountId("1234567");
        accountEntity.setBalance(BigDecimal.ZERO);
        accountEntity.setIban("NL05ABCB0000001001");
        accountRepository.save(accountEntity);

        String nextAccountNumber = accountService.findNextAccountNumber();
        assertEquals("0000001002", nextAccountNumber);
    }
}
