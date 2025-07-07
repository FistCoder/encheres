package fr.eni.projet.encheres.ihm;

import fr.eni.projet.encheres.bll.UtilisateurService;
import fr.eni.projet.encheres.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("membreSession")
public class UtilisateurController {

    UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/profil")

    //public String displayProfil(Model model){
    public String displayProfil(Model model, @ModelAttribute("membreSession") Utilisateur membreSession) {

       Utilisateur user = utilisateurService.consulterUtilisateurById(membreSession.getNoUtilisateur());
       //Utilisateur user = utilisateurService.consulterUtilisateurById(1);

        model.addAttribute("user", user);

        /*if(membreSession != null) {
            model.addAttribute("membreSession", user);

            return "view_profil";
        }*/
        return "view_profil";
    }

    @GetMapping("/profil/update")
    public String displayUpdate(Model model){
        Utilisateur user = utilisateurService.consulterUtilisateurById(1);
        model.addAttribute("user", user);
        return "view_update_profil";
    }

    @PostMapping("/profil/update")
    public String update(@ModelAttribute("user") Utilisateur user, Model model){
        utilisateurService.updateUtilisateur(user);
        return "redirect:/profil";
    }
}
