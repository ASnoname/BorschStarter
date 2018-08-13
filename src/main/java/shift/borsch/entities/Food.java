package shift.borsch.entities;

import lombok.*;
import shift.borsch.entities.enums.TypeFood;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Food")
public class Food implements Serializable {

    {
        this.products = new ArrayList<>();
        this.category = TypeFood.Element;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique = true)
    private long id;

    @OneToMany(fetch = FetchType.LAZY)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "listProductsByFood")
    @Column(name = "products")
    private List<ProductByFridge> products;

    @Column(name = "name",unique = true,nullable = false)
    private String name;

    @Column(name = "category",nullable = false)
    private TypeFood category;
}