package org.sid;

import org.sid.Dao.ProduitRepository;
import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class MycatalogueApplication implements CommandLineRunner {
    @Autowired
    private ProduitRepository produitRepository;

    public static void main(String[] args) {
        SpringApplication.run(MycatalogueApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        produitRepository.save(new Produit(null, "banane", 50, 10));
        produitRepository.save(new Produit(null, "mangue", 50, 8));
        produitRepository.save(new Produit(null, "ananas", 60, 15));
        produitRepository.save(new Produit(null, "fraise", 100, 12));
        produitRepository.save(new Produit(null, "framboise", 50, 12));


        Page<Produit> produits = produitRepository.findByDesignationContains("b", PageRequest.of(0, 3));
        System.out.println(produits.getSize());
        System.out.println(produits.getTotalPages());
        produits.getContent().forEach(p -> {
            System.out.println(p.toString());
        });
        System.out.println("----------------------------------------------");

        Page<Produit> prods = produitRepository.chercher("%f%", 40, PageRequest.of(0, 2));
        System.out.println(prods.getSize());
        System.out.println(prods.getTotalElements());
        System.out.println(prods.getTotalPages());
        prods.getContent().forEach(p -> {
            System.out.println(p.toString());
        });

    }
}
