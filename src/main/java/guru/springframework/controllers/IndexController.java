package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

//    private CategoryRepository categoryRepository;
//    private RecipeRepository recipeRepository;
//    private UnitOfMeasureRepository unitOfMeasureRepository;

    private RecipeServiceImpl recipeService;

//    // wire by constructor.
//    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
//        this.categoryRepository = categoryRepository;
//        this.unitOfMeasureRepository = unitOfMeasureRepository;
//    }

    public IndexController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping(value={"/", "", "/index"})
    public String displayIndexPage(Model model) {
//        System.out.println("this is a change!");

//        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
//        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
//
//        System.out.println("Cat Id is: " + categoryOptional.get().getId());
//        System.out.println("UOM Id is: " + unitOfMeasureOptional.get().getId());

        log.debug("The user has viewed the index page!");
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
