package shift.borsch.entities.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import shift.borsch.entities.enums.TypeFood;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table(name = "FoodData")
public class FoodData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idData",unique = true)
    private long idData;

    @Column(name = "idFood",unique = true)
    private Long id;

    @Column(name = "name",unique = true,nullable = false)
    private String name;

    @Column(name = "category",nullable = false)
    private TypeFood category;
}
