package fr.fouilleOpot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "role", schema = "dbo", catalog = "[fouille-o-pot]")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private int id_role;

    @Column(name = "label_role")
    private String label_role;
}
