package borsch.services.Interfaces;

import borsch.entities.data.ProductByRecipeData;
import borsch.entities.data.RecipeData;
import borsch.entities.data.UserInfoData;

import java.util.List;
import java.util.Map;

public interface RecipeService {

    RecipeData createRecipe(RecipeData recipeData, Long idUserInfo);

    RecipeData provideRecipe(Long idRecipe);

    Boolean deleteRecipe(Long idRecipe);

    RecipeData updateRecipe(RecipeData newRecipeData, Long idRecipe);

    List<ProductByRecipeData> provideAllProductByRecipe(Long idRecipe);

    Boolean deleteAllProductByRecipe(Long idRecipe);

    Map<Long, UserInfoData> provideMapFinalUsers(Long idRecipe);
}