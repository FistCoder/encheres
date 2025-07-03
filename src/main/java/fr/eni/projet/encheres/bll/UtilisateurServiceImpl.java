package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.dal.UtilisateurDAO;
import fr.eni.projet.encheres.dal.UtilisateurDAOImpl;
import fr.eni.projet.encheres.exceptions.InvalidLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Class UtilisateurServiceImpl for
 */
@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UtilisateurDAO utilisateurDAO;
    @Autowired
    private UtilisateurDAOImpl utilisateurDAOImpl;


    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    @Override
    public Utilisateur getUtilisateur(int id) {
        return utilisateurDAO.findUtilisateur(id);

    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        return utilisateurDAO.addUtilisateur(utilisateur) ? utilisateur : null;
    }


    //(Samir) : Ajout de la méthode pour vérifier si l'email existe déjà
    @Override
    public boolean checkEmailExists(String email) {
        // Vérifie si l'email existe déjà en base de données via le DAO
        int noUtilisateur = utilisateurDAO.checkEmailExists(email);
        // Si aucun utilisateur n'existe avec cet email, on lève une exception
        if (noUtilisateur == 0) {
            throw new InvalidLoginException("validation.email.NotUsed");
        }
        // Si l'email existe, on retourne null (ou l'utilisateur si besoin)
        return true ;
    }


}
