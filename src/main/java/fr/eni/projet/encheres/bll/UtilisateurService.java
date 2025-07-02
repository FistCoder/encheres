package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.Utilisateur;

public interface UtilisateurService {
    Utilisateur consulterUtilisateurById(int noUtilisateur);
    void updateUtilisateur(Utilisateur utilisateur);
}
