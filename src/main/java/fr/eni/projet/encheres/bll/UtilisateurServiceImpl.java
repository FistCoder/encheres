package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.dal.UtilisateurDAO;
import fr.eni.projet.encheres.exceptions.InvalidLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Class UtilisateurServiceImpl for
 */
@Service
public class UtilisateurServiceImpl implements UtilisateurService {


 private UtilisateurDAO utilisateurDAO;
    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }


    @Override
    public Utilisateur getUtilisateur(int id) {
        return utilisateurDAO.findUtilisateur(id);
    }

    @Override
    public Utilisateur consulterUtilisateurById(int noUtilisateur) {

        return utilisateurDAO.findUtilisateur(noUtilisateur);
    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur) {
        utilisateurDAO.updateUtilisateur(utilisateur);
    }

    @Override
    public void createUtilisateur(Utilisateur utilisateur) {
        utilisateurDAO.addUtilisateur(utilisateur);}


    //(Samir) : Ajout de la méthode pour vérifier si l'email existe déjà
    @Override
    public Utilisateur checkEmailExists(String email) {
        return utilisateurDAO.findByEmail(email);
    }


}
