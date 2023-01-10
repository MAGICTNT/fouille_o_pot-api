package fr.fouilleOpot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmailDto {
    private String mail;
    private String sujet;
    private String texte;
}
