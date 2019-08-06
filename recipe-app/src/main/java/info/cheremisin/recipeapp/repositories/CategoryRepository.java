package info.cheremisin.recipeapp.repositories;

import info.cheremisin.recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
