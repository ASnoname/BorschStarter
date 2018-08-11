package shift.borsch.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shift.borsch.entities.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
