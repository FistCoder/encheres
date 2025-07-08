package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.dal.UtilisateurDAO;
import fr.eni.projet.encheres.exceptions.BusinessException;
import fr.eni.projet.encheres.exceptions.DuplicateUserException;
import fr.eni.projet.encheres.exceptions.UserValidationException;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurDAO utilisateurDAO;
    private static final int CREDIT_INITIAL = 100;

    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    @Override
    public Utilisateur getUtilisateur(int id) throws BusinessException {
        Utilisateur utilisateur = utilisateurDAO.findUtilisateur(id);
        if (utilisateur == null) {
            throw new BusinessException("L'utilisateur avec l'ID " + id + " n'existe pas.");
        }
        return utilisateur;
    }

    @Override
    public Utilisateur consulterUtilisateurById(int noUtilisateur) throws BusinessException {
        Utilisateur utilisateur = utilisateurDAO.findUtilisateur(noUtilisateur);
        if (utilisateur == null) {
            throw new BusinessException("L'utilisateur avec l'ID " + noUtilisateur + " n'existe pas.");
        }
        return utilisateur;
    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur) throws BusinessException {
        validateUser(utilisateur);

        // Vérifier si l'email est déjà utilisé par un autre utilisateur
        Utilisateur existingUser = utilisateurDAO.findByEmail(utilisateur.getEmail());
        if (existingUser != null && existingUser.getNoUtilisateur() != utilisateur.getNoUtilisateur()) {
            throw new DuplicateUserException("Cette adresse email est déjà utilisée.");
        }

        utilisateurDAO.updateUtilisateur(utilisateur);
    }

    @Override
    public void createUtilisateur(Utilisateur utilisateur) throws BusinessException {
        validateUser(utilisateur);

        // Vérifier si l'email existe déjà
        if (utilisateurDAO.checkEmailExists(utilisateur.getEmail()) != 0) {
            throw new DuplicateUserException("Cette adresse email est déjà utilisée.");
        }

        // Vérifier si le pseudo existe déjà
        if (utilisateurDAO.checkPseudoExists(utilisateur.getPseudo()) != 0) {
            throw new DuplicateUserException("Ce pseudo est déjà utilisé.");
        }

        // Initialiser les valeurs par défaut
        utilisateur.setCredit(CREDIT_INITIAL);
        utilisateur.setAdministrateur(false);

        utilisateurDAO.addUtilisateur(utilisateur);
    }

    @Override
    public void deleteUtilisateur(String email) {
        utilisateurDAO.deleteUtilisateur(email);
    }

    @Override
    public Utilisateur checkEmailExists(String email) {
        return utilisateurDAO.findByEmail(email);
    }

    private void validateUser(Utilisateur utilisateur) throws UserValidationException {
        StringBuilder errors = new StringBuilder();

        // Validation du pseudo
        if (utilisateur.getPseudo() == null || utilisateur.getPseudo().trim().isEmpty()) {
            errors.append("Le pseudo est obligatoire. ");
        } else if (utilisateur.getPseudo().length() > 30) {
            errors.append("Le pseudo ne doit pas dépasser 30 caractères. ");
        }

        // Validation de l'email
        if (utilisateur.getEmail() == null || utilisateur.getEmail().trim().isEmpty()) {
            errors.append("L'email est obligatoire. ");
        } else if (!isValidEmail(utilisateur.getEmail())) {
            errors.append("Format d'email invalide. ");
        }

        // Validation du mot de passe
        if (utilisateur.getMotDePasse() == null || utilisateur.getMotDePasse().trim().isEmpty()) {
            errors.append("Le mot de passe est obligatoire. ");
        } else if (!isValidPassword(utilisateur.getMotDePasse())) {
            errors.append("Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule et un chiffre. ");
        }

        // Validation du code postal
        if (utilisateur.getCodePostal() < 0 || utilisateur.getCodePostal() > 99999) {
            errors.append("Code postal invalide. ");
        }

        if (!errors.isEmpty()) {
            throw new UserValidationException(errors.toString().trim());
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        return Pattern.compile(passwordRegex).matcher(password).matches();
    }
}