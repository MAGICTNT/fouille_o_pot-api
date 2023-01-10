package fr.fouilleOpot.repository;

import fr.fouilleOpot.entity.IngrediantEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class IngredientRepository implements PanacheRepositoryBase <IngrediantEntity,Integer> {
}
