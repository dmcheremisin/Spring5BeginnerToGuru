package info.cheremisin.recipeapp.repositories;

import info.cheremisin.recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
