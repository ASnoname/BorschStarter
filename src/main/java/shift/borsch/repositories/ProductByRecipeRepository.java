package shift.borsch.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shift.borsch.entities.ProductByRecipe;

@Repository
public interface ProductByRecipeRepository extends CrudRepository<ProductByRecipe,Long> {
}
