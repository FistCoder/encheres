package fr.eni.projet.encheres.ihm;

import fr.eni.projet.encheres.bll.UtilisateurService;
import fr.eni.projet.encheres.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    UtilisateurService utilisateurService;

    public RegisterController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model .addAttribute("user", new Utilisateur());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") Utilisateur utilisateur) {
        Utilisateur newUser = utilisateurService.createUtilisateur(utilisateur);

        if(newUser != null) {
            return "redirect:/login";
        } else {
            System.out.println("Error register");
        }
        return "redirect:/register";

    }
}
