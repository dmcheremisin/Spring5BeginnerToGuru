package info.cheremisin.recipeapp.controllers;

import info.cheremisin.recipeapp.services.ImageService;
import info.cheremisin.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    private ImageService imageService;
    private RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/image")
    public String getImageForm(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "/recipe/imageUploadForm";
    }

    @PostMapping("/recipe/{id}/image")
    public String uploadImage(@PathVariable Long id, @RequestParam("imagefile") MultipartFile file) {
        imageService.saveImageFile(id, file);

        return "redirect:/recipe/" + id + "/show";
    }
}
