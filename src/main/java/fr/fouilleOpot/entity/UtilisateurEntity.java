package fr.fouilleOpot.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "utilisateur", schema = "dbo", catalog = "[fouille-o-pot]")
public class UtilisateurEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
    private int id;

    @Basic
    @Column(name = "pseudo_utilisateur")
    private String pseudo;

    @Basic
    @Column(name = "mail_utilisateur")
    private String mail;

    @Basic
    @Column(name = "mdp_utilisateur")
    private String mdp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role")
    private RoleEntity role;

    public UtilisateurEntity(int id, String pseudo, String mail, String mdp) {
        this.id = id;
        this.pseudo = pseudo;
        this.mail = mail;
        this.mdp = mdp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public UtilisateurEntity(String pseudo, String mail, String mdp) {
        this.pseudo = pseudo;
        this.mail = mail;
        this.mdp = mdp;
    }

    public UtilisateurEntity(String pseudo, String mail, String mdp, RoleEntity role) {
        this.pseudo = pseudo;
        this.mail = mail;
        this.mdp = mdp;
        this.role = role;
    }

    public UtilisateurEntity() {

    }
}
