package fr.fouilleOpot.repository;

import fr.fouilleOpot.entity.UniterMessureEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UniterMessureRepository implements PanacheRepositoryBase <UniterMessureEntity,Integer> {
}
