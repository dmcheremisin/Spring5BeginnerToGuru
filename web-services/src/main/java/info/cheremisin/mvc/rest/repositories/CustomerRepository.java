package info.cheremisin.mvc.rest.repositories;

import info.cheremisin.mvc.rest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByLastName(String lastName);
}
