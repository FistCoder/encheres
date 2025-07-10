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
        if ("ventes_en_cours".equals(param)) {
            articles = articlesService.getAllArticleVenduAndUserByMail(authentication.getName());
        } else {
            articles = articlesService.getAllArticleVenduAndUser();
        }
        model.addAttribute("articles", articles);
        return "articles";
    }
}
