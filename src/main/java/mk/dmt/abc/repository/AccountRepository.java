package mk.dmt.abc.repository;

import mk.dmt.abc.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long>  {

    @Query("SELECT acc.iban FROM AccountEntity acc WHERE acc.id=(select max(id) from AccountEntity)")
    Optional<String> findLastIbanNumber();

    Optional<AccountEntity> findByIban(String iban);

}
