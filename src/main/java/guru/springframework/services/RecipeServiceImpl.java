package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in the service.");
        Set<Recipe> recipes = new HashSet<>();
//        Iterable<Recipe> recipeIterable = recipeRepository.findAll();
//        for(Recipe recipe: recipeIterable) {
//            recipes.add(recipe);
//        }
        // java 8 syntax.
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

}
