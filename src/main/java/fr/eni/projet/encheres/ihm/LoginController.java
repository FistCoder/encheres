package fr.eni.projet.encheres.ihm;

import fr.eni.projet.encheres.bll.ContexteService;
import fr.eni.projet.encheres.bo.Utilisateur;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@SessionAttributes({ "membreEnSession" })
class LoginController {

    private ContexteService contexteService;

    public LoginController(ContexteService contexteService) {
        this.contexteService = contexteService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
/*

    @GetMapping("/session")
    public String getProfile(@AuthenticationPrincipal Utilisateur utilisateurSession) {
        int id = utilisateurSession.getNoUtilisateur();
        return "redirect:/";
    }

    @GetMapping("/session")
    String chargerMembreEnSession(@ModelAttribute("membreEnSession") Utilisateur membreEnSession, Principal principal) {
        System.out.println("Rentré");
        String email = principal.getName();
        Utilisateur charger = contexteService.charger(email);
        if (charger != null) {
            membreEnSession.setNoUtilisateur(charger.getNoUtilisateur());
            membreEnSession.setNom(charger.getNom());
            membreEnSession.setPrenom(charger.getPrenom());
            membreEnSession.setPseudo(charger.getPseudo());

        } else {
            membreEnSession.setNom(null);
            membreEnSession.setPrenom(null);
            membreEnSession.setPseudo(null);

        }
        System.out.println(membreEnSession);

        return "redirect:/";
    }
*/


    /*@ModelAttribute("membreEnSession")
    public Utilisateur membreEnSession() {
        System.out.println("Add Attribut Session");
        return new Utilisateur();
    }*/
}