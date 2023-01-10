package fr.fouilleOpot.repository;

import fr.fouilleOpot.entity.NecessiterEntity;
import fr.fouilleOpot.entity.NecessiterEntityPK;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class NecessiterRepository implements PanacheRepositoryBase<NecessiterEntity, NecessiterEntityPK> {
}
