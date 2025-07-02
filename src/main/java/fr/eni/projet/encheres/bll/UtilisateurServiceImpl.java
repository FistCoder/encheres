package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.dal.UtilisateurDAO;
import org.springframework.stereotype.Service;

/**
 * Class UtilisateurServiceImpl for
 *
 */
@Service
public class UtilisateurServiceImpl implements UtilisateurService {


    private UtilisateurDAO utilisateurDAO;

    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    @Override
    public Utilisateur getUtilisateur(String email) {
        return utilisateurDAO.findUtilisateur(email);

    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return null;
    }
}
