package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.Utilisateur;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO{
    @Override
    public Utilisateur findUtilisateur(String email) {
        return null;
    }

    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        return List.of();
    }

    @Override
    public boolean addUtilisateur(Utilisateur utilisateur) {
        return false;
    }

    @Override
    public boolean updateUtilisateur(Utilisateur utilisateur) {
        return false;
    }

    @Override
    public boolean deleteUtilisateur(int id) {
        return false;
    }
}
