package info.cheremisin.mvc.rest.services;

import info.cheremisin.mvc.rest.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
