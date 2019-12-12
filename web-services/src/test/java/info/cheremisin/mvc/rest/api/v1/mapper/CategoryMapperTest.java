package info.cheremisin.mvc.rest.api.v1.mapper;

import info.cheremisin.mvc.rest.api.v1.model.CategoryDTO;
import info.cheremisin.mvc.rest.domain.Category;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryMapperTest {

    public static final String NAME = "Super food";
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO(){
        Category category  = new Category();
        category.setId(1L);
        category.setName(NAME);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(category.getId(), categoryDTO.getId());
        assertEquals(category.getName(), categoryDTO.getName());
    }
}