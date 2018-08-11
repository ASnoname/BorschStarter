package shift.borsch.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shift.borsch.entities.Food;

@Repository
public interface FoodRepository extends CrudRepository<Food,Long> {
}
