package fr.eni.projet.encheres.ihm;

import fr.eni.projet.encheres.bll.ArticleVenduService;
import fr.eni.projet.encheres.bo.ArticleVendu;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArticleController {

    private ArticleVenduService articlesService;

    public ArticleController(ArticleVenduService articlesService) {
        this.articlesService = articlesService;
    }

    @GetMapping("/articles")
    public String articles() {
        return "articles";
    }

    @PostMapping("/articles")
    public String articles(@RequestParam(name = "check", required = false) String param, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<ArticleVendu> articles;
        if(param == null) param = "default";
        switch (param) {
            case "ventes_en_cours":
                articles = articlesService.getAllArticleVenduAndUserByMail(authentication.getName());
                break;
            case "ventes_non_debutees":
                // Assuming you have a method to get user ID from email in your service
                articles = articlesService.getArticleVenduNonDebuteeByUserMail(authentication.getName());
                break;
            case "ventes_terminees":
                articles = articlesService.getArticleVenduTermineeByUserMail(authentication.getName());
                break;
            case "enchere_ouverte":
                articles = articlesService.getEnchereOuvertes();
                break;
            case "mes_encheres":
                // You'll need to add this method to your DAO and Service
                articles = articlesService.getMesEncheresByUserMail(authentication.getName());
                break;
            case "mes_encheres_remportees":
                // You'll need to add this method to your DAO and Service
                articles = articlesService.getEncheresRemporteesByUserMail(authentication.getName());
                break;
            default:
                articles = articlesService.getAllArticleVenduAndUser();
                break;
        }

        model.addAttribute("articles", articles);
        return "articles";
    }
}
