package info.cheremisin.mvc.rest.controller;

import info.cheremisin.mvc.rest.api.v1.model.CategoryDTO;
import info.cheremisin.mvc.rest.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> allCategories = categoryService.getAllCategories();
        return allCategories;
    }

    @GetMapping("/{name}")
    public CategoryDTO getCategoryByName(@PathVariable String name) {
        CategoryDTO categoryByName = categoryService.getCategoryByName(name);
        return categoryByName;
    }
}
