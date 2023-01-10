package fr.fouilleOpot.dto;

import fr.fouilleOpot.entity.RoleEntity;
import fr.fouilleOpot.entity.UtilisateurEntity;
import fr.fouilleOpot.hateOas.HateOas;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UtilisateurDto extends HateOas {

    private int id ;
    private String pseudo;
    private String mail;
    private String mdp;
    private RoleEntity role;

    public UtilisateurDto(UtilisateurEntity utilisateurEntity){
        this.id = utilisateurEntity.getId();
        this.pseudo = utilisateurEntity.getPseudo();
        this.mail = utilisateurEntity.getMail();
        this.mdp = utilisateurEntity.getMdp();
        this.role = utilisateurEntity.getRole();
    }

    public static List<UtilisateurDto> compteDTO(List<UtilisateurEntity> listCompteEntities){
        List<UtilisateurDto> listCompteDTOS = new ArrayList<>();
        for(UtilisateurEntity compte : listCompteEntities){
            listCompteDTOS.add(new UtilisateurDto(compte));
        }
        return listCompteDTOS;
    }
}
