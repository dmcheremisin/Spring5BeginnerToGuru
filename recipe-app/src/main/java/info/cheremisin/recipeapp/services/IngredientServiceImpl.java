package info.cheremisin.recipeapp.services;

import info.cheremisin.recipeapp.commands.IngredientCommand;
import info.cheremisin.recipeapp.converters.IngredientCommandToIngredient;
import info.cheremisin.recipeapp.converters.IngredientToIngredientCommand;
import info.cheremisin.recipeapp.domain.Ingredient;
import info.cheremisin.recipeapp.domain.Recipe;
import info.cheremisin.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private RecipeRepository recipeRepository;
    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
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

    @Override
    public void saveIngredientCommand(IngredientCommand ingredientCommand) {
        Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
        Optional<Recipe> recipeOpt = recipeRepository.findById(ingredientCommand.getRecipeId());
        Recipe recipe = recipeOpt.orElseThrow(() -> new RuntimeException("Recipe not found with id = " + ingredientCommand.getRecipeId()));

        if (ingredient.getId() != null) {
            Ingredient foundIngredient = recipe.getIngredients().stream()
                    .filter(i -> i.getId().equals(ingredient.getId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Ingredient not found with id = " + ingredient.getId()));
            foundIngredient.setAmount(ingredient.getAmount());
            foundIngredient.setDescription(ingredient.getDescription());
            foundIngredient.setUnitOfMeasure(ingredient.getUnitOfMeasure());
        } else {
            recipe.addIngredient(ingredient);
        }

        Recipe savedRecipe = recipeRepository.save(recipe);

        log.debug("Updated ingredient for the recipe id = " + savedRecipe.getId());
    }
}
