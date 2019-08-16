package info.cheremisin.recipeapp.controllers;

import info.cheremisin.recipeapp.commands.IngredientCommand;
import info.cheremisin.recipeapp.services.IngredientService;
import info.cheremisin.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {

    private RecipeService recipeService;
    private IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
    }

    @RequestMapping(value = "/recipe/{id}/ingredients")
    public String getIngredientList(@PathVariable Long id, Model model) {
        log.debug("Getting ingredient list for the recipe id = " + id);

        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "/recipe/ingredient/list";
    }

    @RequestMapping(value = "/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String getIngredientDetails(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model) {
        log.debug(String.format("Getting information about ingredient with id = %d and recipe id = %d", ingredientId, recipeId));

        IngredientCommand ingredientCommand = ingredientService.findIngredientByRecipeIdAndId(recipeId, ingredientId);
        model.addAttribute("ingredient", ingredientCommand);
        return "/recipe/ingredient/show";
    }

}
