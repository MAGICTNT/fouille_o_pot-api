package fr.fouilleOpot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Table(name = "ustensile", schema = "dbo", catalog = "[fouille-o-pot]")
public class UstensileEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_ustensile")
    private int idUstensile;
    @Basic
    @Column(name = "label")
    private String label;

    public int getIdUstensile() {
        return idUstensile;
    }

    public void setIdUstensile(int idUstensile) {
        this.idUstensile = idUstensile;
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
        UstensileEntity that = (UstensileEntity) o;
        return idUstensile == that.idUstensile && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUstensile, label);
    }
}
