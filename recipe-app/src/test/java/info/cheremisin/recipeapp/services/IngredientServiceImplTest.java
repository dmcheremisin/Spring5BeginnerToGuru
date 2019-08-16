package info.cheremisin.recipeapp.services;

import info.cheremisin.recipeapp.commands.IngredientCommand;
import info.cheremisin.recipeapp.converters.IngredientToIngredientCommand;
import info.cheremisin.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import info.cheremisin.recipeapp.domain.Ingredient;
import info.cheremisin.recipeapp.domain.Recipe;
import info.cheremisin.recipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    IngredientToIngredientCommand ingredientToIngredientCommand;

    IngredientService ingredientService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ingredientToIngredientCommand = new IngredientToIngredientCommand(uomConverter);
        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand);
    }

    @Test
    public void findIngredientByRecipeIdAndId() {
        Recipe recipe = new Recipe();
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

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        IngredientCommand ingredientCommand = ingredientService.findIngredientByRecipeIdAndId(123L, 3L);

        assertEquals(Long.valueOf(3), ingredientCommand.getId());
        assertEquals(Long.valueOf(123), ingredientCommand.getRecipeId());

        verify(recipeRepository, times(1)).findById(anyLong());
    }
}