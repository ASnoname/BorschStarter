package shift.borsch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shift.borsch.entities.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {
}
