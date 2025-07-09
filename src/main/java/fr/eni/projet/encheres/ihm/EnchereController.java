package fr.eni.projet.encheres.ihm;

import fr.eni.projet.encheres.bll.ArticleVenduService;
import fr.eni.projet.encheres.bll.EnchereService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnchereController {
    private EnchereService enchereService;
    private ArticleVenduService articlesService;

    public EnchereController(EnchereService enchereService, ArticleVenduService articlesService) {
        this.enchereService  = enchereService;
        this.articlesService = articlesService;
    }

    @GetMapping("/")
    public String encheres(Model model) {

        model.addAttribute("encheres", enchereService.listerEncheres());
        model.addAttribute("articles", articlesService.getAllArticleVenduAndUser());

        return "index";
    }
}
