package shift.borsch.entities.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table(name = "ProductByRecipeData")
public class ProductByRecipeData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idData",unique = true)
    private long idData;

    @Column(name = "id",unique = true)
    private Long id;

    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "Amount")
    private Double amount;
}
