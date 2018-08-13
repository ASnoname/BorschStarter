package shift.borsch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shift.borsch.entities.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {
}
