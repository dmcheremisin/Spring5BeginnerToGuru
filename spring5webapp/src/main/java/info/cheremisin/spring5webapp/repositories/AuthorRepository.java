package info.cheremisin.spring5webapp.repositories;

import info.cheremisin.spring5webapp.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
