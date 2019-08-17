package info.cheremisin.recipeapp.services;

import info.cheremisin.recipeapp.domain.Recipe;
import info.cheremisin.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {
        log.debug("Received a file");

        Recipe recipe = recipeRepository.findById(recipeId).get();

        try {
            byte[] bytes = file.getBytes();
            Byte[] byteObject = new Byte[bytes.length];
            int i = 0;
            for (byte aByte : bytes) {
                byteObject[i++] = aByte;
            }

            recipe.setImage(byteObject);
            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error("Can't save image in the database");
        }
    }
}
