package info.cheremisin.recipeapp.services;

import info.cheremisin.recipeapp.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findIngredientByRecipeIdAndId(Long recipeId, Long ingredientId);

    void saveIngredientCommand(IngredientCommand ingredientCommand);
}
