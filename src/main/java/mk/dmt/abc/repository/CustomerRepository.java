package mk.dmt.abc.repository;

import mk.dmt.abc.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>  {
    Optional<CustomerEntity> findByUsername(String username);
}
