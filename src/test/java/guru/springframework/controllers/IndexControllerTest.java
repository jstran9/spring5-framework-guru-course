package guru.springframework.controllers;

import guru.springframework.services.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class IndexControllerTest {

    @Mock
    Model model;

    @Mock
    RecipeServiceImpl recipeService;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void displayIndexPage() throws Exception {
        String expectedModelName = "recipes";
        String expectedIndexPageName = "index";

        String returnedIndexPageName = indexController.displayIndexPage(model);

        assertEquals(expectedIndexPageName, returnedIndexPageName);
        verify(recipeService, times(1)).getRecipes();
        // expect to add in a key that equals "recipes" with a set of recipe object(s).
        verify(model, times(1)).addAttribute(eq(expectedModelName), anySet());

    }

}