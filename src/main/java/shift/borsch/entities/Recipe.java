package shift.borsch.entities;

import lombok.*;
import shift.borsch.entities.data.RecipeData;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Recipe")
public class Recipe implements Serializable {

    {
        this.products = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique = true)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInfoId")
    private UserInfo userInfo;

    @OneToMany(fetch = FetchType.LAZY)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "productsInRecipe")
    @Column(name = "product")
    private List<ProductByRecipe> products;

    @OneToOne
    private RecipeData recipeData;
}