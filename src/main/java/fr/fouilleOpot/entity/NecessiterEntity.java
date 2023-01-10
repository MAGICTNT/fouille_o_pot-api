package fr.fouilleOpot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "necessiter", schema = "dbo", catalog = "fouille-o-pot")
public class NecessiterEntity {

    @EmbeddedId
    NecessiterEntityPK necessiterEntityPK;

    @Basic
    @Column(name = "uniter")
    private int uniter;

}
