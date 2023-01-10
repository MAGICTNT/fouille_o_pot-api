package fr.fouilleOpot.repository;

import fr.fouilleOpot.entity.RoleEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RoleRepository implements PanacheRepositoryBase<RoleEntity, Integer> {
}
