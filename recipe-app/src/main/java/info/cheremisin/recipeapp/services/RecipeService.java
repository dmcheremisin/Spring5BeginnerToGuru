package info.cheremisin.recipeapp.services;

import info.cheremisin.recipeapp.commands.RecipeCommand;
import info.cheremisin.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
