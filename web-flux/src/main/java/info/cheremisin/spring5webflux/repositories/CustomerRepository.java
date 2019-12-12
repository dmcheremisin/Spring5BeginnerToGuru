package info.cheremisin.spring5webflux.repositories;

import info.cheremisin.spring5webflux.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
}
