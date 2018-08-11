package shift.borsch.services.Interfaces;

import shift.borsch.entities.data.RecipeData;
import shift.borsch.entities.data.UserInfoData;
import shift.borsch.entities.enums.StateByProduct;

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