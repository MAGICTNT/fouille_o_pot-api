package fr.fouilleOpot.repository;

import fr.fouilleOpot.entity.UtilisateurEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UtilisateurRepository implements PanacheRepositoryBase<UtilisateurEntity, Integer> {
}
