package fr.fouilleOpot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ingrediant", schema = "dbo", catalog = "[fouille-o-pot]")
@Data
public class IngrediantEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_ingrediant")
    private int idIngrediant;
    @Basic
    @Column(name = "label")
    private String label;
    @Basic
    @Column(name = "id_type_ingredient")
    private int idTypeIngredient;
    @Basic
    @Column(name = "id_uniter")
    private int idUniter;

    public int getIdIngrediant() {
        return idIngrediant;
    }

    public void setIdIngrediant(int idIngrediant) {
        this.idIngrediant = idIngrediant;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getIdTypeIngredient() {
        return idTypeIngredient;
    }

    public void setIdTypeIngredient(int idTypeIngredient) {
        this.idTypeIngredient = idTypeIngredient;
    }

    public int getIdUniter() {
        return idUniter;
    }

    public void setIdUniter(int idUniter) {
        this.idUniter = idUniter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngrediantEntity that = (IngrediantEntity) o;
        return idIngrediant == that.idIngrediant && idTypeIngredient == that.idTypeIngredient && idUniter == that.idUniter && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idIngrediant, label, idTypeIngredient, idUniter);
    }
}
