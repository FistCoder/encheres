package fr.eni.projet.encheres.ihm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {
    @GetMapping("/articles")
    public String articles() {
        return "articles";
    }


    @GetMapping("/articles/mesventescours")
    public String mesventescours(Model model) {
        return "mesventescours";
    }
}
