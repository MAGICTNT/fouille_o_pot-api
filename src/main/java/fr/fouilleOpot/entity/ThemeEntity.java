package fr.fouilleOpot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "theme", schema = "dbo", catalog = "[fouille-o-pot]")
@Data
public class ThemeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_theme")
    private int idTheme;
    @Basic
    @Column(name = "label")
    private String label;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_theme")
    private List<RecetteEntity> recette;

 

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThemeEntity that = (ThemeEntity) o;
        if (idTheme != that.idTheme) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        return true;
    }

    @Override
    public int hashCode() {

        int result = idTheme;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }
}
