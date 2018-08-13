package shift.borsch.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shift.borsch.entities.enums.StateByProduct;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Data
@Builder
public class User implements Serializable {

    {
        this.fridge = new Fridge();
        this.recipes = new ArrayList<>();
        this.stateByProductMap = new HashMap<>();
        this.roles = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToOne
    @JoinColumn(name = "fridgeId")
    Fridge fridge;

    @OneToMany(fetch = FetchType.LAZY)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "recipesByUserId")
    @Column(name = "recipe")
    private Collection<Recipe> recipes;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "stateByProduct")
    @MapKeyColumn(name = "productId")
    @Column(name = "state")
    private Map<Long, StateByProduct> stateByProductMap;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "secondName")
    private String secondName;

    @Column(name = "city")
    private String city;

    @Column(name = "unversity")
    private String university;

    @Column(name = "dormitory")
    private String dormitory;

    @Column(name = "room")
    private String room;

    @Column(name = "linkToVk")
    private String linkToVk;

    @Column(name = "linkToTelegram")
    private String linkToTelegram;

    @Column(name = "cookRate")
    private Double cookRate;

    @Column(name = "eatRate")
    private Double eatRate;
}