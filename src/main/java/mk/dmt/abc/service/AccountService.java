package mk.dmt.abc.service;

import mk.dmt.abc.model.Account;
import mk.dmt.abc.repository.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Account findAccount(String accountNumber) {
        return null;
    }

    public String findNextAccountNumber() {
        final Optional<String> lastIbanOpt = accountRepository.findLastIbanNumber();
        if(lastIbanOpt.isEmpty()) {
            return "0000000001";
        }
        String lastIban = lastIbanOpt.get();
        String accountNumber = lastIban.substring(8);
        int account = Integer.valueOf(accountNumber) + 1;
        String newAccountNumber = String.valueOf(account);
        int l = newAccountNumber.length();
        StringBuilder zeroedAccountNumber = new StringBuilder();
        for(int i = 0; i < (10 - l); i++) {
            zeroedAccountNumber.append("0");
        }
        zeroedAccountNumber.append(newAccountNumber);
        return zeroedAccountNumber.toString();
    }
}
