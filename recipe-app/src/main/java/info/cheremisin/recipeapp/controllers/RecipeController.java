package info.cheremisin.recipeapp.controllers;

import info.cheremisin.recipeapp.commands.RecipeCommand;
import info.cheremisin.recipeapp.domain.Recipe;
import info.cheremisin.recipeapp.exceptions.NotFoundException;
import info.cheremisin.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String getRecipeById(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.findById(new Long(id));
        model.addAttribute("recipe", recipe);
        return "recipe/show";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeForm";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.parseLong(id));
        model.addAttribute("recipe", recipeCommand);
        return "recipe/recipeForm";
    }

    @PostMapping("/recipe")
    public String saveRecipe(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/" + savedRecipe.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFoundExceptionHandler(Exception exception) {
        log.error("Handling Not Found Exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

}
