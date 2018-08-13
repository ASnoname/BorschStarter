package shift.borsch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shift.borsch.entities.ProductByRecipe;

@Repository
public interface ProductByRecipeRepository extends JpaRepository<ProductByRecipe,Long> {
}
