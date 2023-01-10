package fr.fouilleOpot.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.fouilleOpot.entity.RoleEntity;
import fr.fouilleOpot.hateOas.HateOas;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonPropertyOrder({"idRole","labelRole"})
public class RoleDto extends HateOas {

    private int idRole;
    private String labelRole;

    public RoleDto(RoleEntity roleEntity){
        this.idRole = roleEntity.getId_role();
        this.labelRole = roleEntity.getLabel_role();
    }

    public static List<RoleDto> roleDTO(List<RoleEntity> listRoleEntities){
        List<RoleDto> listRoleDTOS = new ArrayList<>();
        for(RoleEntity role : listRoleEntities){
            listRoleDTOS.add(new RoleDto(role));
        }
        return listRoleDTOS;
    }
}
