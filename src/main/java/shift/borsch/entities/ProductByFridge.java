package borsch.entities;

import borsch.entities.data.ProductByFridgeData;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "ProductByFridge")
public class ProductByFridge implements Serializable {

    {
        this.productByRecipes = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique = true)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fridgeId")
    private Fridge fridge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodId")
    private Food food;

    @OneToMany
    @JoinColumn(name = "productByRecipes")
    private List<ProductByRecipe> productByRecipes;

    @OneToOne
    private ProductByFridgeData productByFridgeData;
}