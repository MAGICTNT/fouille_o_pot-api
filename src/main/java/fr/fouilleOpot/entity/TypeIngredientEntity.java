package fr.fouilleOpot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "type_ingredient", schema = "dbo", catalog = "[fouille-o-pot]")
@Data
public class TypeIngredientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_type_ingredient")
    private int idTypeIngredient;
    @Basic
    @Column(name = "label")
    private String label;

    public int getIdTypeIngredient() {
        return idTypeIngredient;
    }

    public void setIdTypeIngredient(int idTypeIngredient) {
        this.idTypeIngredient = idTypeIngredient;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeIngredientEntity that = (TypeIngredientEntity) o;
        return idTypeIngredient == that.idTypeIngredient && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTypeIngredient, label);
    }
}
