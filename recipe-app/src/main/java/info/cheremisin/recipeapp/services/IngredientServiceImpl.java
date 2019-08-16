package info.cheremisin.recipeapp.services;

import info.cheremisin.recipeapp.commands.IngredientCommand;
import info.cheremisin.recipeapp.converters.IngredientToIngredientCommand;
import info.cheremisin.recipeapp.domain.Ingredient;
import info.cheremisin.recipeapp.domain.Recipe;
import info.cheremisin.recipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private RecipeRepository recipeRepository;
    private IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findIngredientByRecipeIdAndId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
        Recipe recipe = recipeOpt.orElseThrow(() -> new RuntimeException("Recipe not found with id = " + recipeId));

        Ingredient ingredient = recipe.getIngredients().stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Ingredient not found with id = %d and recipeId = %d", ingredientId, recipeId)));
        return ingredientToIngredientCommand.convert(ingredient);
    }
}
