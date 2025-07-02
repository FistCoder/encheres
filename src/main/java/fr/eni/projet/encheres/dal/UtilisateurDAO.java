package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
    Utilisateur findUtilisateur(int noUtilisateur);
    void updateUtilisateur(Utilisateur utilisateur);

}
