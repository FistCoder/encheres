package fr.eni.projet.encheres.ihm;

import fr.eni.projet.encheres.bll.ContexteService;
import fr.eni.projet.encheres.bo.Utilisateur;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import fr.eni.projet.encheres.bll.UtilisateurService;

import fr.eni.projet.encheres.exceptions.InvalidLoginException;
import org.springframework.ui.Model;

import static fr.eni.projet.encheres.exceptions.InvalidLoginCode.NOT_FOUND;

@Controller
@SessionAttributes({ "membreEnSession" })
class LoginController {


    private ContexteService contexteService;

    public LoginController(ContexteService contexteService) {
        this.contexteService = contexteService;
    }

    private UtilisateurService utilisateurService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    // (Samir) : Ajout de la méthode pour vérifier si l'email existe déjà
    @GetMapping("/login-error")
    public String login(Model model) {
        model.addAttribute("error", "Email ou mot de passe incorrect");
        return "login";
    }
}