package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.Utilisateur;

public interface UtilisateurService {

    public Utilisateur getUtilisateur(String nom);

    public Utilisateur createUtilisateur(Utilisateur utilisateur);

}
