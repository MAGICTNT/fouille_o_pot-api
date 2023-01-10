package fr.fouilleOpot.repository;

import fr.fouilleOpot.entity.ThemeEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ThemeRepository implements PanacheRepositoryBase <ThemeEntity,Integer> {
}
