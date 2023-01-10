package fr.fouilleOpot.repository;

import fr.fouilleOpot.entity.UstensileEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UstensileRepository implements PanacheRepositoryBase <UstensileEntity,Integer> {
}
