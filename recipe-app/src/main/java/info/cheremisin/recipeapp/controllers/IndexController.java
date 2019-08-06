package info.cheremisin.recipeapp.controllers;

import info.cheremisin.recipeapp.repositories.CategoryRepository;
import info.cheremisin.recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage() {
        System.out.println(categoryRepository.findByDescription("Russian").get().getId()); // 1
        System.out.println(unitOfMeasureRepository.findByDescription("teaspoon").get().getId()); // 1

        return "index";
    }
}
