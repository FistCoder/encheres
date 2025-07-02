package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.dal.UtilisateurDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Class UtilisateurServiceImpl for
 *
 */
@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UtilisateurDAO utilisateurDAO;


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
    @Override
    public Utilisateur ckeckEmail(String username) {
        return utilisateurDAO.findByEmail(username);
    }

}
