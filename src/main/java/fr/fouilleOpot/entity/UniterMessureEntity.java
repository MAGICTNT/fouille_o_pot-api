package fr.fouilleOpot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "uniter_messure", schema = "dbo", catalog = "[fouille-o-pot]")
@Data
public class UniterMessureEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_uniter")
    private int idUniter;
    @Basic
    @Column(name = "label")
    private String label;

    public int getIdUniter() {
        return idUniter;
    }

    public void setIdUniter(int idUniter) {
        this.idUniter = idUniter;
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
        UniterMessureEntity that = (UniterMessureEntity) o;
        return idUniter == that.idUniter && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUniter, label);
    }
}
