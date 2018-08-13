package shift.borsch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shift.borsch.entities.ProductByFridge;

@Repository
public interface ProductByFridgeRepository extends JpaRepository<ProductByFridge,Long> {
}
