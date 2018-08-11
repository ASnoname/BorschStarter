package shift.borsch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shift.borsch.services.Interfaces.RecipeService;
import shift.borsch.entities.data.ProductByRecipeData;
import shift.borsch.entities.data.RecipeData;
import shift.borsch.entities.data.UserInfoData;

import java.util.List;
import java.util.Map;

@RestController
public class RecipeController {

    private static final String RECIPE_PATH = Resources.RECIPE_PREFIX;

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping(RECIPE_PATH + "new")
    public @ResponseBody
    BaseResponse<RecipeData> createRecipe(@RequestBody RecipeData recipeData, @PathVariable(name = "idUser") Long idUserInfo) {

        return new BaseResponse<>(recipeService.createRecipe(recipeData,idUserInfo));
    }

    @GetMapping(RECIPE_PATH + "{id}")
    public @ResponseBody
    BaseResponse<RecipeData> provideRecipe(@PathVariable(name = "id") Long id) {

        return new BaseResponse<>(recipeService.provideRecipe(id));
    }

    @DeleteMapping(RECIPE_PATH + "{id}")
    public @ResponseBody
    BaseResponse deleteRecipe(@PathVariable(name = "id") Long id) {

        return new BaseResponse(recipeService.deleteRecipe(id));
    }

    @PatchMapping(RECIPE_PATH + "{id}")
    public @ResponseBody
    BaseResponse<RecipeData> updateRecipe(@PathVariable(name = "id") Long id, @RequestBody RecipeData recipeData) {

        return new BaseResponse<>(recipeService.updateRecipe(recipeData,id));
    }

    @GetMapping(RECIPE_PATH + "{id}" + "/products")
    public @ResponseBody
    BaseResponse<List<ProductByRecipeData>> provideListProducts(@PathVariable(name = "id") Long id) {

        return new BaseResponse<>(recipeService.provideAllProductByRecipe(id));
    }

    @DeleteMapping(RECIPE_PATH + "{id}" + "/products")
    public @ResponseBody
    BaseResponse deleteAllProducts(@PathVariable(name = "id") Long id) {

        return new BaseResponse(recipeService.deleteAllProductByRecipe(id));
    }

    @GetMapping(RECIPE_PATH + "{id}" + "/users")
    public @ResponseBody
    BaseResponse<Map<Long, UserInfoData>> provideMapUsers(@PathVariable(name = "id") Long id) {

        return new BaseResponse<>(recipeService.provideMapFinalUsers(id));
    }
}