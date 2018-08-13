package shift.borsch.entities;

import lombok.*;
import shift.borsch.entities.enums.StateRecipe;

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
        this.state = StateRecipe.ACTIVE;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique = true)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInfoId")
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "productsInRecipe")
    @Column(name = "product")
    private List<ProductByRecipe> products;

    @Column(name = "name")
    private String name;

    @Column(name = "state",nullable = false)
    private StateRecipe state;
}