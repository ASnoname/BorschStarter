package shift.borsch.services.Interfaces;

import shift.borsch.entities.data.FoodData;

import java.util.List;

public interface FoodService {

    FoodData provideFood(Long idFood);

    Boolean deleteFood(Long idFood);

    FoodData createFood(FoodData foodData);

    List<FoodData> provideListFoodStartWith(String start);

    FoodData updateFood(Long idFood, FoodData newFoodData);
}