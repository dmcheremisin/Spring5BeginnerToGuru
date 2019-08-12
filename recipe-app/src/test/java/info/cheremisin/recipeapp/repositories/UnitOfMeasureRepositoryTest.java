package info.cheremisin.recipeapp.repositories;

import info.cheremisin.recipeapp.domain.UnitOfMeasure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryTest {

    public static final String TEASPOON = "Teaspoon";
    public static final String CUP = "Cup";

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    public void findByDescriptionTest1() {
        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        assertEquals(TEASPOON, teaspoon.get().getDescription());
    }

    @Test
    public void findByDescriptionTest2() {
        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByDescription(CUP);
        assertEquals(CUP, teaspoon.get().getDescription());
    }
}