package fr.eni.projet.encheres.ihm;

import fr.eni.projet.encheres.bll.ContexteService;
import fr.eni.projet.encheres.bo.Utilisateur;
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

    @GetMapping("/session")
    String chargerMembreEnSession(@ModelAttribute("membreEnSession") Utilisateur membreEnSession, Principal principal) {
        String email = principal.getName();
        Utilisateur aCharger = contexteService.charger(email);
        if (aCharger != null) {
            membreEnSession.setNoUtilisateur(aCharger.getNoUtilisateur());
            membreEnSession.setNom(aCharger.getNom());
            membreEnSession.setPrenom(aCharger.getPrenom());
            membreEnSession.setPseudo(aCharger.getPseudo());

        } else {
            membreEnSession.setNom(null);
            membreEnSession.setPrenom(null);
            membreEnSession.setPseudo(null);

        }
        System.out.println(membreEnSession);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}