package fr.fouilleOpot.repository;

import fr.fouilleOpot.entity.TypeIngredientEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class TypeIngredientRepository implements PanacheRepositoryBase<TypeIngredientEntity,Integer> {
}
