package shift.borsch.services.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shift.borsch.entities.Food;
import shift.borsch.entities.data.FoodData;
import shift.borsch.repositories.FoodRepository;
import shift.borsch.services.Interfaces.FoodService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(final FoodRepository foodRepository) {

        this.foodRepository = foodRepository;
    }

    @Override
    public FoodData provideFood(Long idFood){

        try {
            return foodRepository.findById(idFood)
                    .map(Food::getFoodData)
                    .orElse(null);
        }
        catch (IllegalArgumentException e){
            return null;
        }
    }

    @Override
    public FoodData createFood(FoodData foodData){

        if (foodData != null && foodData.getCategory() != null && foodData.getName() != null){

            Food food = new Food();

            food.setFoodData(foodData);

            food = foodRepository.save(food);

            food
                    .getFoodData()
                    .setId(food
                            .getId());

            foodRepository.save(food);

            return food.getFoodData();
        }
        else {
            return null;
        }
    }

    @Override
    public Boolean deleteFood(Long idFood){

        try {
            if (foodRepository.findById(idFood).isPresent()) {
                foodRepository.deleteById(idFood);
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
    public List<FoodData> provideListFoodStartWith(String start){

        if (start == null){
            return null;
        }

        List<FoodData> correctFoods = new ArrayList<>();

        StreamSupport.stream(foodRepository.findAll().spliterator(), true)

                .filter(food -> food.getFoodData().getName().startsWith(start))

                .forEach(food -> correctFoods.add(food.getFoodData()));

        return correctFoods;
    }

    @Override
    public FoodData updateFood(Long idFood, FoodData newFoodData){

        if (newFoodData == null){
            return null;
        }

        Optional<Food> oldFood;

        try {
            oldFood = foodRepository.findById(idFood);
        }
        catch (IllegalArgumentException e){
            return null;
        }

        if (oldFood.isPresent() && newFoodData.getName() != null && newFoodData.getCategory() != null){

            newFoodData
                    .setId(oldFood
                            .get()
                            .getFoodData()
                            .getId());

            oldFood
                    .get()
                    .setFoodData(newFoodData);

            foodRepository.save(oldFood.get());

            return oldFood
                    .get()
                    .getFoodData();
        }
        else {
            return null;
        }
    }
}