package info.cheremisin.mvc.rest.services;

import info.cheremisin.mvc.rest.api.v1.mapper.CategoryMapper;
import info.cheremisin.mvc.rest.api.v1.model.CategoryDTO;
import info.cheremisin.mvc.rest.domain.Category;
import info.cheremisin.mvc.rest.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    public static final CategoryMapper MAPPER = CategoryMapper.INSTANCE;
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> list = categoryRepository.findAll().stream()
                .map(MAPPER::categoryToCategoryDTO)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryRepository.findCategoriesByName(name);
        return MAPPER.categoryToCategoryDTO(category);
    }
}
