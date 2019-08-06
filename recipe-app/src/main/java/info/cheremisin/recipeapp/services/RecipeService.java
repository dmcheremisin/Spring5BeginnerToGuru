package info.cheremisin.recipeapp.services;

import info.cheremisin.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    public Set<Recipe> getRecipes();
}
