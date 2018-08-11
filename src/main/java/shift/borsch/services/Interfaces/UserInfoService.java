package borsch.services.Interfaces;

import borsch.entities.data.RecipeData;
import borsch.entities.data.UserInfoData;
import borsch.entities.enums.StateByProduct;

import java.util.List;
import java.util.Map;

public interface UserInfoService {

    UserInfoData createUserInfo(UserInfoData userInfoData);

    Boolean deleteUserInfo(Long idUserInfo);

    UserInfoData provideUserInfo(Long idUserInfo);

    UserInfoData updateUserInfo(Long idUserInfo, UserInfoData newUserInfoData);

    List<RecipeData> provideAllRecipesByUserInfo(Long idUserInfo);

    Map<Long, StateByProduct> provideStatesByProduct(Long idUserInfo);
}