package shift.borsch.services.Interfaces;

import shift.borsch.entities.data.ProductByFridgeData;
import shift.borsch.entities.enums.StateByProduct;

import java.util.List;

public interface FridgeService {

    ProductByFridgeData addProductByFridge(Long idUserInfo, ProductByFridgeData productByFridgeData);

    Boolean deleteProductByFridge(Long idProductByFridge);

    ProductByFridgeData provideProductByFridge(Long idProductByFridge);

    ProductByFridgeData updateProductByFridge(Long idProductByFridge, ProductByFridgeData newProductByFridgeData);

    List<ProductByFridgeData> provideAllProductByFridge(Long idUserInfo);

    Boolean deleteAllProductByFridge(Long idUserInfo);

    Boolean changeStateByProduct(Long idUserInfo, Long idProductByRecipe, StateByProduct newState);
}