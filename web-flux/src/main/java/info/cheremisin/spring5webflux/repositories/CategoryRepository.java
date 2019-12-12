package info.cheremisin.spring5webflux.repositories;

import info.cheremisin.spring5webflux.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
