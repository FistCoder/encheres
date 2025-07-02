package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.dal.UtilisateurDAO;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{


    private final UtilisateurDAO utilisateurDAO;

    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    @Override
    public Utilisateur consulterUtilisateurById(int noUtilisateur) {

        return utilisateurDAO.findUtilisateur(noUtilisateur);
    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur) {
        utilisateurDAO.updateUtilisateur(utilisateur);
    }
}
