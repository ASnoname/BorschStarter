package shift.borsch.entities.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import shift.borsch.entities.enums.TypeFood;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Table(name = "FoodData")
public class FoodData implements Serializable {

    @Column(name = "id",unique = true)
    private Long id;

    @Column(name = "name",unique = true,nullable = false)
    private String name;

    @Column(name = "category",nullable = false)
    private TypeFood category;
}
