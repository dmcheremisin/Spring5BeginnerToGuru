package info.cheremisin.spring5webapp.repositories;

import info.cheremisin.spring5webapp.model.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
