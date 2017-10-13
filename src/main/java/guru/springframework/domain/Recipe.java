package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Identity uses the automatic generation of a sequence seen in MySQL via automatic PK.
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;

    @Lob // this field gets created as a BLOB field. (Large Object).
    private Byte[] image;

    // for EnumType.Ordinal the strings would be stored as 1, 2, 3 instead of Easy, Moderate, Hard.
    // for example: if we use ordinal with Easy, Moderate, Insert, Hard then hard would be labeled as 4 now instead of 3.
    // from the above example it is preferred that we use EnumType.String because Hard will remain as Hard inside the db.
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    // if we delete a recipe then we want to delete the note (so we need a cascade).
    private Notes notes;

    // mappedBy is the target property on the ingredient class.
    // notice that in the Ingredient class there is a property named "recipe"
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

}
