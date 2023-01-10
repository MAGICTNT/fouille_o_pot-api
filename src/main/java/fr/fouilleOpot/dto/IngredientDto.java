package fr.fouilleOpot.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.fouilleOpot.entity.IngrediantEntity;
import fr.fouilleOpot.hateOas.HateOas;
import lombok.Data;

@Data
@JsonPropertyOrder({"idIngrediant","label"})
public class IngredientDto extends HateOas {
    int idIngrediant;
    String label;

    public IngredientDto(IngrediantEntity ingrediantEntity){
        this.idIngrediant = ingrediantEntity.getIdIngrediant();
        this.label = ingrediantEntity.getLabel();
    }
}
