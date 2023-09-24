package mk.dmt.abc.mapper;

import mk.dmt.abc.entity.AccountEntity;
import mk.dmt.abc.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account apply(AccountEntity accountEntity) {
        final Account account = new Account();
        account.setBalance(accountEntity.getBalance());
        account.setIban(accountEntity.getIban());
        account.setCurrency(accountEntity.getCurrency());
        account.setTimestamp(accountEntity.getCreated());
        account.setType(accountEntity.getType());
        return account;
    }
}
