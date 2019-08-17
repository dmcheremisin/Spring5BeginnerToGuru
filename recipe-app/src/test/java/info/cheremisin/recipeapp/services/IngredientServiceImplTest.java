package info.cheremisin.recipeapp.services;

import info.cheremisin.recipeapp.commands.IngredientCommand;
import info.cheremisin.recipeapp.commands.UnitOfMeasureCommand;
import info.cheremisin.recipeapp.converters.IngredientCommandToIngredient;
import info.cheremisin.recipeapp.converters.IngredientToIngredientCommand;
import info.cheremisin.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import info.cheremisin.recipeapp.domain.Ingredient;
import info.cheremisin.recipeapp.domain.Recipe;
import info.cheremisin.recipeapp.domain.UnitOfMeasure;
import info.cheremisin.recipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    @Mock
    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    IngredientCommandToIngredient ingredientCommandToIngredient;

    IngredientService ingredientService;

    Recipe recipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ingredientToIngredientCommand = new IngredientToIngredientCommand(uomConverter);
        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand, ingredientCommandToIngredient);

        recipe = new Recipe();
        recipe.setId(123L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
    }

    @Test
    public void findIngredientByRecipeIdAndId() {
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        IngredientCommand ingredientCommand = ingredientService.findIngredientByRecipeIdAndId(123L, 3L);

        assertEquals(Long.valueOf(3), ingredientCommand.getId());
        assertEquals(Long.valueOf(123), ingredientCommand.getRecipeId());

        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void saveIngredientTest() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipe.getId());

        Ingredient ingredient = new Ingredient();
        ingredient.setRecipe(recipe);
        ingredient.setUnitOfMeasure(new UnitOfMeasure());
        
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        when(ingredientCommandToIngredient.convert(any())).thenReturn(ingredient);
        when(recipeRepository.save(any())).thenReturn(recipe);

        ingredientService.saveIngredientCommand(ingredientCommand);

        verify(recipeRepository, times(1)).save(any());

        Set<Ingredient> ingredients = recipe.getIngredients();
        assertEquals(4, ingredients.size());
    }

    @Test
    public void deleteIngredientTest() {
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        ingredientService.deleteIngredientById(123L, 2L);

        verify(recipeRepository, times(1)).findById(anyLong());

        Set<Ingredient> ingredients = recipe.getIngredients();
        assertEquals(2, ingredients.size());
        assertFalse(ingredients.stream().anyMatch(i -> i.getId().equals(2L)));
    }
}