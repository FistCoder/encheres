package fr.eni.projet.encheres.ihm;

import fr.eni.projet.encheres.bll.UtilisateurService;
import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.exceptions.BusinessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/profil")
    public String displayProfil(Model model) {
        try {
            Utilisateur user = getCurrentUser();
            if (user == null) {
                return "redirect:/login";
            }
            model.addAttribute("user", user);
            return "view_profil";
        } catch (BusinessException e) {
            model.addAttribute("error", "Impossible de récupérer les informations du profil: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/profil/update")
    public String displayUpdate(Model model) {
        try {
            Utilisateur user = getCurrentUser();
            if (user == null) {
                return "redirect:/login";
            }
            model.addAttribute("user", user);
            return "view_update_profil";
        } catch (BusinessException e) {
            model.addAttribute("error", "Impossible de récupérer les informations du profil: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping("/profil/update")
    public String update(@Valid @ModelAttribute("user") Utilisateur user,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        try {
            // Verify that the current user is updating their own profile
            Utilisateur currentUser = getCurrentUser();
            if (currentUser == null) {
                return "redirect:/login";
            }

            if (bindingResult.hasErrors()) {
                return "view_update_profil";
            }

            // Ensure the user can't change their ID
            user.setNoUtilisateur(currentUser.getNoUtilisateur());

            // Keep the original credit and admin status
            user.setCredit(currentUser.getCredit());
            user.setAdministrateur(currentUser.isAdministrateur());

            utilisateurService.updateUtilisateur(user);
            redirectAttributes.addFlashAttribute("success", "Profil mis à jour avec succès");
            return "redirect:/profil";

        } catch (BusinessException e) {
            model.addAttribute("error", "Erreur lors de la mise à jour du profil: " + e.getMessage());
            return "view_update_profil";
        }
    }

    private Utilisateur getCurrentUser() throws BusinessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                return utilisateurService.checkEmailExists(userDetails.getUsername());
            }
        }
        return null;
    }
}