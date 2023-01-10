package fr.fouilleOpot.entity;

import javax.persistence.*;

@Entity
@Table(name = "recette", schema = "dbo", catalog = "[fouille-o-pot]")
public class RecetteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_recette")
    private int idRecette;
    @Basic
    @Column(name = "label")
    private String label;
    @Basic
    @Column(name = "duree")
    private int duree;

    @Basic
    @Column(name = "personne")
    private int personne;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_theme")
    private ThemeEntity theme;

    public int getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(int idRecette) {
        this.idRecette = idRecette;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getPersonne() {
        return personne;
    }

    public void setPersonne(int personne) {
        this.personne = personne;
    }

    public ThemeEntity getTheme() {
        return theme;
    }

    public void setTheme(ThemeEntity theme) {
        this.theme = theme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecetteEntity that = (RecetteEntity) o;
        if (idRecette != that.idRecette) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRecette;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }



}
