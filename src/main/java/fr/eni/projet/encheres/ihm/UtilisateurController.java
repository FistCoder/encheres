package fr.eni.projet.encheres.ihm;

import fr.eni.projet.encheres.bll.UtilisateurService;
import fr.eni.projet.encheres.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class UtilisateurController {

    UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/profil")
    //public String displayProfil(Model model){
    public String displayProfil(Model model) {

        // Récupère l'objet Authentication depuis le contexte de sécurité de Spring
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

// Vérifie que l'utilisateur est authentifié et que l'objet Authentication n'est pas nul
        if (authentication != null && authentication.isAuthenticated()) {

            // Récupère le principal, qui représente l'utilisateur connecté
            Object principal = authentication.getPrincipal();

            // Affiche dans la console le contenu du principal (utile pour le debug)
            System.out.println("principal" + principal);

            // Vérifie si le principal est une instance de UserDetails (ou d'une classe qui l'implémente)
            if (principal instanceof UserDetails utilisateur) {

                // Récupère l'identifiant de l'utilisateur, ici l'email (ou le username selon l'implémentation)
                String email = utilisateur.getUsername();

                // Recherche de l'utilisateur complet à partir de l'email via un service
                Utilisateur user = utilisateurService.checkEmailExists(email);

                // Ajoute l'utilisateur au modèle pour qu'il soit accessible dans la vue
                model.addAttribute("user", user);

            } else {
                // Si le principal n'est pas un UserDetails, on récupère simplement son nom sous forme de chaîne
                String username = principal.toString();
            }
        }

        // Retourne le nom de la vue à afficher (ex : view_profil.html ou .jsp selon ta config)
        return "view_profil";}


    @GetMapping("/profil/update")
    public String displayUpdate (Model model){
        Utilisateur user = utilisateurService.consulterUtilisateurById(1);
        model.addAttribute("user", user);
        return "view_update_profil";
    }

    @PostMapping("/profil/update")
    public String update (@ModelAttribute("user") Utilisateur user, Model model){
        utilisateurService.updateUtilisateur(user);
        return "redirect:/profil";
    }
}
