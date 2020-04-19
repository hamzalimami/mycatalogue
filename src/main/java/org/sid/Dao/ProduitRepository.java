package org.sid.Dao;

import org.sid.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    public Page<Produit> findByDesignationContains(String mc, Pageable pageable);

    @Query("select p from  Produit p where p.designation like :x and p.prix >:y")
    public Page<Produit> chercher
            (@Param("x") String mc,
             @Param("y") double prixmin, Pageable pageable);
}
