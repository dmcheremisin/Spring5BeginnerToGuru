package info.cheremisin.recipeapp.controllers;

import info.cheremisin.recipeapp.commands.IngredientCommand;
import info.cheremisin.recipeapp.commands.UnitOfMeasureCommand;
import info.cheremisin.recipeapp.services.IngredientService;
import info.cheremisin.recipeapp.services.RecipeService;
import info.cheremisin.recipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class IngredientController {

    private RecipeService recipeService;
    private IngredientService ingredientService;
    private UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String getIngredientList(@PathVariable Long id, Model model) {
        log.debug("Getting ingredient list for the recipe id = " + id);

        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "/recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String getIngredientDetails(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model) {
        log.debug(String.format("Getting information about ingredient with id = %d and recipe id = %d", ingredientId, recipeId));

        IngredientCommand ingredientCommand = ingredientService.findIngredientByRecipeIdAndId(recipeId, ingredientId);
        model.addAttribute("ingredient", ingredientCommand);
        return "/recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model) {
        log.debug(String.format("Update form for ingredient with id = %d of recipe id = %d", ingredientId, recipeId));

        IngredientCommand ingredientCommand = ingredientService.findIngredientByRecipeIdAndId(recipeId, ingredientId);
        model.addAttribute("ingredient", ingredientCommand);
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "/recipe/ingredient/ingredientForm";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveIngredient(@ModelAttribute IngredientCommand ingredientCommand) {
        ingredientService.saveIngredientCommand(ingredientCommand);
        return "redirect:/recipe/" + ingredientCommand.getRecipeId() + "/ingredients";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable Long recipeId, Model model){
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeId);
        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());

        model.addAttribute("ingredient", ingredientCommand);
        model.addAttribute("uomList",  unitOfMeasureService.listAllUoms());
        return "/recipe/ingredient/ingredientForm";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId) {
        log.debug(String.format("Delete ingredient with id = %d of recipe id = %d", ingredientId, recipeId));

        ingredientService.deleteIngredientById(recipeId, ingredientId);

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
