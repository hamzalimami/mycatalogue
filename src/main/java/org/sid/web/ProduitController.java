package org.sid.web;

import org.sid.Dao.ProduitRepository;
import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProduitController {
    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping(path = "/index")
    public String index() {
        return "index";

    }

    @GetMapping(path = "/products")
    public String products(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int p,
                           @RequestParam(name = "size", defaultValue = "5") int s,
                           @RequestParam(name = "keyword", defaultValue = "") String k) {
        Page<Produit> pageproduits = produitRepository.findByDesignationContains(k, PageRequest.of(p, s));
        model.addAttribute("pageProduits", pageproduits);
        model.addAttribute("currentpage", p);
        model.addAttribute("size", s);
        model.addAttribute("keyword", k);
        model.addAttribute("pages", new int[pageproduits.getTotalPages()]);
        return "products";

    }
}
