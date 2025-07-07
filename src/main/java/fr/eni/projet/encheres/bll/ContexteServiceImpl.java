package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.dal.UtilisateurDAO;
import org.springframework.stereotype.Service;

@Service
public class ContexteServiceImpl implements ContexteService {

    private UtilisateurDAO utilisateurDAO;

    @Override
    public Utilisateur charger(String email) {
        return utilisateurDAO.findByEmail(email);
    }
}
