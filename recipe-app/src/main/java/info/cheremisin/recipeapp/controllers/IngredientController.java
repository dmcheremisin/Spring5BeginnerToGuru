package info.cheremisin.recipeapp.controllers;

import info.cheremisin.recipeapp.domain.Recipe;
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

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(value = "/recipe/{id}/ingredients")
    public String getIngredientList(@PathVariable Long id, Model model) {
        log.debug("Getting ingredient list for the recipe id = " + id);

        model.addAttribute("recipe", recipeService.findById(id));
        return "/recipe/ingredient/list";
    }


}
