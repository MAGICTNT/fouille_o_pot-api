package fr.fouilleOpot.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class NecessiterEntityPK implements Serializable {
    @Column(name = "id_ingrediant")
    private int idIngrediant;
    @Column(name = "id_recette")
    private int idRecette;

}
