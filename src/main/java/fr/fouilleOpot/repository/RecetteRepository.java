package fr.fouilleOpot.repository;

import fr.fouilleOpot.entity.RecetteEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RecetteRepository implements PanacheRepositoryBase<RecetteEntity, Integer> {
}
