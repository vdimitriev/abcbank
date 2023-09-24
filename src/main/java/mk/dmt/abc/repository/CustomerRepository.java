package mk.dmt.abc.repository;

import mk.dmt.abc.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>  {
}
