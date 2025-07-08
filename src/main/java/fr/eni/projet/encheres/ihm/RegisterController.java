package fr.eni.projet.encheres.ihm;

import fr.eni.projet.encheres.bll.UtilisateurService;
import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.exceptions.BusinessException;
import fr.eni.projet.encheres.exceptions.DuplicateUserException;
import fr.eni.projet.encheres.exceptions.UserValidationException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String register(@Valid @ModelAttribute("user") Utilisateur utilisateur,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            utilisateurService.createUtilisateur(utilisateur);
            return "redirect:/login";
        } catch (DuplicateUserException | UserValidationException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        } catch (BusinessException e) {
            model.addAttribute("error", "Une erreur est survenue lors de l'inscription. Veuillez réessayer.");
            return "register";
        }
    }
}
