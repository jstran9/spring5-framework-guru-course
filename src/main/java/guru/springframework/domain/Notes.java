package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    // no cascade here because we are going to allow the recipe to own the note.
    // a cascade here would mean if we delete the notes then we would delete the recipe which isn't something we want.
    private Recipe recipe;

    // store into a CLOB field which allows for much more than 255 characters.
    @Lob
    private String recipeNotes;

}
