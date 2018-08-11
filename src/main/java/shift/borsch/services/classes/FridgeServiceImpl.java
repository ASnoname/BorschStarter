package shift.borsch.services.classes;

import shift.borsch.entities.Food;
import shift.borsch.entities.ProductByFridge;
import shift.borsch.entities.ProductByRecipe;
import shift.borsch.entities.UserInfo;
import shift.borsch.entities.data.ProductByFridgeData;
import shift.borsch.entities.enums.StateByProduct;
import shift.borsch.entities.enums.StateRecipe;
import shift.borsch.repositories.FoodRepository;
import shift.borsch.repositories.ProductByFridgeRepository;
import shift.borsch.repositories.ProductByRecipeRepository;
import shift.borsch.repositories.UserInfoRepository;
import shift.borsch.services.Interfaces.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shift.borsch.services.Interfaces.ProductByRecipeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class FridgeServiceImpl implements FridgeService {

    private final UserInfoRepository userInfoRepository;
    private final ProductByFridgeRepository productByFridgeRepository;
    private final FoodRepository foodRepository;
    private final ProductByRecipeService productByRecipeService;
    private final ProductByRecipeRepository productByRecipeRepository;

    @Autowired
    public FridgeServiceImpl(UserInfoRepository userInfoRepository, ProductByFridgeRepository productByFridgeRepository, FoodRepository foodRepository, ProductByRecipeService productByRecipeService, ProductByRecipeRepository productByRecipeRepository) {

        this.userInfoRepository = userInfoRepository;
        this.productByFridgeRepository = productByFridgeRepository;
        this.foodRepository = foodRepository;
        this.productByRecipeService = productByRecipeService;
        this.productByRecipeRepository = productByRecipeRepository;
    }

    @Override
    public List<ProductByFridgeData> provideAllProductByFridge(Long idUserInfo) {

        Optional<UserInfo> userInfo;

        try {
            userInfo = userInfoRepository.findById(idUserInfo);
        }
        catch (IllegalArgumentException e){
            return null;
        }

        if (userInfo.isPresent()){

            List<ProductByFridgeData> productByFridgeDataList = new ArrayList<>();

            userInfo
                    .get()
                    .getFridge()
                    .getProducts()
                    .parallelStream()
                    .forEach(productByFridge -> productByFridgeDataList
                            .add(productByFridge
                                    .getProductByFridgeData()));

            return productByFridgeDataList;
        }
        else {
            return null;
        }
    }

    @Override
    public Boolean deleteAllProductByFridge(Long idUserInfo) {

        Optional<UserInfo> userInfo;

        try {
            userInfo = userInfoRepository.findById(idUserInfo);
        }
        catch (IllegalArgumentException e){
            return false;
        }

        if (userInfo.isPresent()){

            userInfo
                    .get()
                    .getFridge()
                    .getProducts()
                    .parallelStream()
                    .forEach(p -> deleteProductByFridge(p.getId()));

            userInfoRepository.save(userInfo.get());

            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public ProductByFridgeData addProductByFridge(Long idUserInfo, ProductByFridgeData productByFridgeData) {

        if (productByFridgeData == null){
            return null;
        }

        Optional<UserInfo> userInfo;
        Optional<Food> food = findCorrectFood(productByFridgeData);

        try {
            userInfo = userInfoRepository.findById(idUserInfo);
        }
        catch (IllegalArgumentException e){
            return null;
        }

        if (food.isPresent() && userInfo.isPresent()){

            return saveProductByFridge(food.get(), userInfo.get(), productByFridgeData)
                    .getProductByFridgeData();
        }
        else {
            return null;
        }
    }

    private ProductByFridge saveProductByFridge(Food food, UserInfo userInfo, ProductByFridgeData productByFridgeData) {

        ProductByFridge productByFridge = new ProductByFridge();

        productByFridge
                .setFood(food);

        productByFridge
                .setFridge(userInfo
                        .getFridge());

        productByFridge.setProductByFridgeData(productByFridgeData);

        productByFridge = productByFridgeRepository.save(productByFridge);

        productByFridge
                .getProductByFridgeData()
                .setId(productByFridge
                        .getId());

        productByFridgeRepository
                .save(productByFridge);

        userInfo
                .getFridge()
                .getProducts()
                .add(productByFridge);

        userInfoRepository.save(userInfo);

        return productByFridge;
    }

    private Optional<Food> findCorrectFood(ProductByFridgeData productByFridgeData) {

        return StreamSupport.stream(foodRepository.findAll().spliterator(), true)

                .filter(f -> f
                        .getFoodData()
                        .getName()
                        .equals(productByFridgeData
                                .getName()))

                .findFirst();
    }

    @Override
    public Boolean changeStateByProduct(Long idUserInfo, Long idProductByRecipe, StateByProduct newState) {

        Optional<UserInfo> userInfo;
        Optional<ProductByRecipe> productByRecipe;

        try {
            userInfo = userInfoRepository.findById(idUserInfo);
            productByRecipe = productByRecipeRepository.findById(idProductByRecipe);

        }
        catch (IllegalArgumentException e){
            return false;
        }

        if (userInfo.isPresent() && productByRecipe.isPresent()){

            StateByProduct stateByProduct = userInfo.get().getStateByProductMap().get(idProductByRecipe);

            if (stateByProduct.equals(StateByProduct.ACCEPTED) && newState.equals(StateByProduct.DENIED)){

                return acceptedToDenied(idProductByRecipe, userInfo.get(), productByRecipe.get());
            }
            else if (stateByProduct.equals(StateByProduct.WAITING) && newState.equals(StateByProduct.DENIED)){

                return waitingToDenied(idProductByRecipe, userInfo.get());
            }
            else if (stateByProduct.equals(StateByProduct.WAITING) && newState.equals(StateByProduct.NOTCONFIRMED)){

                return waitingToNotConfirmed(idProductByRecipe, userInfo.get(), productByRecipe.get());
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    private Boolean waitingToNotConfirmed(Long idProductByRecipe, UserInfo userInfo, ProductByRecipe productByRecipe) {

        userInfo.getStateByProductMap().put(idProductByRecipe,StateByProduct.NOTCONFIRMED);

        productByRecipe.getOffers().add(userInfo);

        return true;
    }

    private Boolean waitingToDenied(Long idProductByRecipe, UserInfo userInfo) {

        userInfo.getStateByProductMap().put(idProductByRecipe, StateByProduct.DENIED);

        return true;
    }

    private Boolean acceptedToDenied(Long idProductByRecipe, UserInfo userInfo, ProductByRecipe productByRecipe) {

        Optional<ProductByFridge> productByFridge = getCorrectProductByFridge(userInfo, productByRecipe);

        if (productByFridge.isPresent()){

            Double oldFreeAmount = productByFridge.get().getProductByFridgeData().getFreeAmount();
            Double amount = productByRecipe.getProductByRecipeData().getAmount();

            productByFridge.get().getProductByFridgeData().setFreeAmount(oldFreeAmount + amount);

            userInfo.getStateByProductMap().put(idProductByRecipe, StateByProduct.DENIED);

            return true;
        }
        else {
            return false;
        }
    }

    private Optional<ProductByFridge> getCorrectProductByFridge(UserInfo userInfo, ProductByRecipe productByRecipe) {
        return productByRecipe
                .getFood()
                .getProducts()
                .parallelStream()
                .filter(p -> p
                        .getFridge()
                        .equals(userInfo
                                .getFridge()))
                .findFirst();
    }

    private void clearListProductByRecipes(ProductByFridge productByFridge) {

        UserInfo userInfo = productByFridge.getFridge().getUserInfo();

        productByFridge
                .getProductByRecipes()
                .parallelStream()
                .filter(p -> p.getRecipe().getRecipeData().getState().equals(StateRecipe.ACTIVE))
                .filter(p -> p.getUserInfo().equals(userInfo))
                .forEach(p -> changeStateByProduct(userInfo.getId(),p.getId(),StateByProduct.DENIED));
    }

    @Override
    public Boolean deleteProductByFridge(Long idProductByFridge) {

        try {
            Optional<ProductByFridge> productByFridge = productByFridgeRepository.findById(idProductByFridge);

            if (productByFridge.isPresent()){

                clearListProductByRecipes(productByFridge.get());

                productByFridge
                        .get()
                        .getFridge()
                        .getProducts()
                        .remove(productByFridge.get());

                productByFridgeRepository.deleteById(idProductByFridge);

                return true;
            }
            else {
                return false;
            }
        }
        catch (IllegalArgumentException e){
            return false;
        }
    }

    @Override
    public ProductByFridgeData provideProductByFridge(Long idProductByFridge) {

        try {
            return productByFridgeRepository
                    .findById(idProductByFridge)
                    .map(ProductByFridge::getProductByFridgeData)
                    .orElse(null);
        }
        catch (IllegalArgumentException e){
            return null;
        }
    }

    @Override
    public ProductByFridgeData updateProductByFridge(Long idProductByFridge, ProductByFridgeData newProductByFridgeData) {

        Optional<ProductByFridge> oldProductByFridge;

        try {
            oldProductByFridge = productByFridgeRepository.findById(idProductByFridge);
        }
        catch (IllegalArgumentException e){
            return null;
        }

        if (oldProductByFridge.isPresent() && newProductByFridgeData != null){

            checkCorrectAmount(newProductByFridgeData, oldProductByFridge.get());

            newProductByFridgeData
                    .setId(oldProductByFridge
                            .get()
                            .getProductByFridgeData()
                            .getId());

            oldProductByFridge
                    .get()
                    .setProductByFridgeData(newProductByFridgeData);

            productByFridgeRepository.save(oldProductByFridge.get());

            return oldProductByFridge.get().getProductByFridgeData();
        }
        else {
            return null;
        }
    }

    private void checkCorrectAmount(ProductByFridgeData newProductByFridgeData, ProductByFridge oldProductByFridge) {

        Double oldAllAmount = oldProductByFridge.getProductByFridgeData().getAllAmount();
        Double oldFreeAmount = oldProductByFridge.getProductByFridgeData().getFreeAmount();

        Double olddifferent = oldAllAmount - oldFreeAmount;

        if (newProductByFridgeData.getAllAmount() >= olddifferent){

            newProductByFridgeData.setFreeAmount(newProductByFridgeData.getAllAmount() - olddifferent);
        }
        else {
            newProductByFridgeData.setFreeAmount(newProductByFridgeData.getAllAmount());

            oldProductByFridge
                    .getProductByRecipes()
                    .parallelStream()
                    .forEach(p -> productByRecipeService
                            .clearFinalUserInfo(p.getId()));
        }
    }
}