package info.cheremisin.recipeapp.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    private Category category;

    @Before
    public void setUp() {
        category = new Category();
    }

    @Test
    public void getId() {
        Long idValue = 4L;

        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {
        String description = "very important category";

        category.setDescription(description);
        assertEquals(description, category.getDescription());
    }

}