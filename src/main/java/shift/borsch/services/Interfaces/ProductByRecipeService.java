//package shift.borsch.services.Interfaces;
//
//import shift.borsch.entities.data.ProductByRecipeData;
//
//import java.util.List;
//
//public interface ProductByRecipeService {
//
//    ProductByRecipeData createProductByRecipe(Long idRecipe, ProductByRecipeData productByRecipeData);
//
//    Boolean deleteProductByRecipe(Long idProductByRecipe);
//
//    ProductByRecipeData provideProductByRecipe(Long idProductByRecipe);
//
//    ProductByRecipeData updateProductByRecipe(Long idProductByRecipe, ProductByRecipeData newProductByRecipeData);
//
//    List<UserInfoData> provideOffers(Long idProductByRecipe);
//
//    Boolean deleteUserInfoFromOffers(Long idProductByRecipe, Long idUserInfo);
//
//    Boolean clearFinalUserInfo(Long idProductByRecipe);
//
//    UserInfoData changeFinalUserInfo(Long idProductByRecipe, Long newIdUserInfo);
//}