package shift.borsch.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shift.borsch.entities.ProductByFridge;

@Repository
public interface ProductByFridgeRepository extends CrudRepository<ProductByFridge,Long> {
}
