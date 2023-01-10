package fr.fouilleOpot.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.fouilleOpot.entity.RecetteEntity;
import fr.fouilleOpot.entity.ThemeEntity;
import fr.fouilleOpot.hateOas.HateOas;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@JsonPropertyOrder({"id","label","duree","nbPersonne","theme"})
public class RecetteDto extends HateOas{
    private int id;
    private String label;
    private int duree;
    private int nbPersonne;
    private Theme theme;

    @Getter
    @Setter
    private class Theme{
        int id;
        String label;
        public Theme (ThemeEntity themeEntity){
            id = themeEntity.getIdTheme();
            label = themeEntity.getLabel();
        }
    }

    public RecetteDto(RecetteEntity recetteEntity){
        this.id = recetteEntity.getIdRecette();
        this.label = recetteEntity.getLabel();
        this.duree = recetteEntity.getDuree();
        this.nbPersonne = recetteEntity.getPersonne();
        this.theme = new Theme(recetteEntity.getTheme());
    }

    public static List<RecetteDto> toRecetteDto(List<RecetteEntity> recetteEntities){
        List<RecetteDto> recetteDtos = new ArrayList<>();
        for (RecetteEntity recetteEntity : recetteEntities){
            recetteDtos.add(new RecetteDto(recetteEntity));
        }
        return recetteDtos;
    }

}
