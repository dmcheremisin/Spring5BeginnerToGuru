package info.cheremisin.mvc.rest.repositories;

import info.cheremisin.mvc.rest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoriesByName(String name);
}
