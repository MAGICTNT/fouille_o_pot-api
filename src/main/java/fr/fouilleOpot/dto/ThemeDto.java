package fr.fouilleOpot.dto;

import fr.fouilleOpot.entity.RecetteEntity;
import fr.fouilleOpot.entity.ThemeEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
public class ThemeDto {
    private int id;
    private String label;
    private Recette recette;

    @Getter
    @Setter
    private class Recette{
        int id;
        String label;
        int duree;
        int nbPersonne;
        public Recette(RecetteEntity recetteEntity){
            id = recetteEntity.getIdRecette();
            label = recetteEntity.getLabel();
            duree = recetteEntity.getDuree();
            nbPersonne = recetteEntity.getPersonne();
        }
    }
    public ThemeDto(ThemeEntity themeEntity){
        this.id = themeEntity.getIdTheme();
        this.label = themeEntity.getLabel();
        this.recette = new Recette((RecetteEntity) themeEntity.getRecette());
    }

    public static List<ThemeDto> toThemeDto(List<ThemeEntity> themeEntities){
        List<ThemeDto> themeDtos = new ArrayList<>();
        for (ThemeEntity themeEntity : themeEntities){
            themeDtos.add(new ThemeDto(themeEntity));
        }
        return themeDtos;
    }

}
