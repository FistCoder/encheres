package fr.eni.projet.encheres.ihm;

import fr.eni.projet.encheres.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String register(Model model) {
        model .addAttribute("user", new Utilisateur());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") Utilisateur utilisateur) {

        return "redirect:/";
    }
}
