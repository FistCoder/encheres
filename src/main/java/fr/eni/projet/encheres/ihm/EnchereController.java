package fr.eni.projet.encheres.ihm;

import fr.eni.projet.encheres.bll.EnchereService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnchereController {
    private EnchereService enchereService;

    public EnchereController(EnchereService enchereService) {
        this.enchereService = enchereService;
    }


    @GetMapping("/")
    public String encheres(Model model) {

        model.addAttribute("encheres", enchereService.listerEncheres());

        return "index";
    }
}
