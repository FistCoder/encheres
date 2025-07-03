package fr.eni.projet.encheres.ihm;

import fr.eni.projet.encheres.bll.UtilisateurService;
import fr.eni.projet.encheres.bo.Utilisateur;

import fr.eni.projet.encheres.exceptions.InvalidLoginException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static fr.eni.projet.encheres.exceptions.InvalidLoginCode.NOT_FOUND;

@Controller
class LoginController {

    private UtilisateurService utilisateurService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    // (Samir) : Ajout de la méthode pour vérifier si l'email existe déjà
    @PostMapping("/login")
    public String login(@ModelAttribute Utilisateur utilisateur, Model model) {
        try {
            // Vérifie si l'email de l'utilisateur existe déjà via le service
            utilisateurService.checkEmailExists(utilisateur.getEmail());
            // Si l'email existe, on redirige vers la page de login
            return "redirect:/index";
        } catch (InvalidLoginException e) {
            // Si une exception est levée (email déjà utilisé), on ajoute un message d'erreur au modèle
            model.addAttribute("error", e.getMessage());
            // Puis on redirige vers la page d'inscription
            return "redirect:/register";
        }
    }


}