package info.cheremisin.mvc.rest.services;

import info.cheremisin.mvc.rest.api.v1.model.CategoryDTO;
import info.cheremisin.mvc.rest.domain.Category;
import info.cheremisin.mvc.rest.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    public static final String SUPER_FOOD = "Super food";
    public static final Long ID = 1L;
    @Mock
    CategoryRepository categoryRepository;

    CategoryService categoryService;

    Category category;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryServiceImpl(categoryRepository);

        category = new Category();
        category.setId(ID);
        category.setName(SUPER_FOOD);
    }

    @Test
    public void getAllCategories() {
        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(category));

        List<CategoryDTO> allCategories = categoryService.getAllCategories();

        assertNotNull(allCategories);
        assertEquals(1, allCategories.size());
    }

    @Test
    public void getCategoryByName() {

        when(categoryRepository.findCategoriesByName(anyString())).thenReturn(category);

        CategoryDTO categoryByName = categoryService.getCategoryByName(SUPER_FOOD);

        assertNotNull(categoryByName);
        assertEquals(ID, categoryByName.getId());
        assertEquals(SUPER_FOOD, categoryByName.getName());

    }
}