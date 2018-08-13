//package shift.borsch.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import shift.borsch.services.Interfaces.ProductByRecipeService;
//import shift.borsch.entities.data.ProductByRecipeData;
//
//import java.util.List;
//
//@RestController
//public class ProductByRecipeController {
//
//    private static final String PRODUCT_BY_RECIPE_PATH = Resources.PRODUCT_BY_RECIPE_PREFIX;
//
//    private final ProductByRecipeService productByRecipeService;
//
//    @Autowired
//    public ProductByRecipeController(ProductByRecipeService productByRecipeService) {
//        this.productByRecipeService = productByRecipeService;
//    }
//
//    @GetMapping(PRODUCT_BY_RECIPE_PATH + "new")
//    public @ResponseBody
//    BaseResponse<ProductByRecipeData> createProduct(@RequestBody ProductByRecipeData productByRecipeData, @PathVariable(name = "idRecipe") Long idRecipe) {
//
//        return new BaseResponse<>(productByRecipeService.createProductByRecipe(idRecipe,productByRecipeData));
//    }
//
//    @DeleteMapping(PRODUCT_BY_RECIPE_PATH + "{id}")
//    public @ResponseBody
//    BaseResponse deleteProduct(@PathVariable(name = "id") Long id) {
//
//        return new BaseResponse(productByRecipeService.deleteProductByRecipe(id));
//    }
//
//    @GetMapping(PRODUCT_BY_RECIPE_PATH + "{id}")
//    public @ResponseBody
//    BaseResponse<ProductByRecipeData> provideProduct(@PathVariable(name = "id") Long id) {
//
//        return new BaseResponse<>(productByRecipeService.provideProductByRecipe(id));
//    }
//
//    @PatchMapping(PRODUCT_BY_RECIPE_PATH + "{id}")
//    public @ResponseBody
//    BaseResponse<ProductByRecipeData> updateProduct(@PathVariable(name = "id") Long id, @RequestBody ProductByRecipeData productByRecipeData) {
//
//        return new BaseResponse<>(productByRecipeService.updateProductByRecipe(id,productByRecipeData));
//    }
//
//    @GetMapping(PRODUCT_BY_RECIPE_PATH + "{id}" + "/users")
//    public @ResponseBody
//    BaseResponse<List<UserInfoData>> provideListUsers(@PathVariable(name = "id") Long id) {
//
//        return new BaseResponse<>(productByRecipeService.provideOffers(id));
//    }
//
//    @DeleteMapping(PRODUCT_BY_RECIPE_PATH + "{id}" + "/users/" + "{idUserInfo}")
//    public @ResponseBody
//    BaseResponse deleteUserFromOffers(@PathVariable(name = "id") Long id, @PathVariable(name = "idUserInfo") Long idUserInfo) {
//
//        return new BaseResponse(productByRecipeService.deleteUserInfoFromOffers(id,idUserInfo));
//    }
//
//    @DeleteMapping(PRODUCT_BY_RECIPE_PATH + "{id}" + "/finalUser")
//    public @ResponseBody
//    BaseResponse deleteFinalUser(@PathVariable(name = "id") Long id) {
//
//        return new BaseResponse(productByRecipeService.clearFinalUserInfo(id));
//    }
//
//    @PatchMapping(PRODUCT_BY_RECIPE_PATH + "{id}" + "/finalUser/" + "{idNewFinalUser}")
//    public @ResponseBody
//    BaseResponse<UserInfoData> updateFinalUser(@PathVariable(name = "id") Long id, @PathVariable(name = "idNewFinalUser") Long idNewFinalUser) {
//
//        return new BaseResponse<>(productByRecipeService.changeFinalUserInfo(id,idNewFinalUser));
//    }
//}
