package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.Utilisateur;

public interface UtilisateurService {

    public Utilisateur getUtilisateur(int id);

    public Utilisateur createUtilisateur(Utilisateur utilisateur);
    public boolean checkEmailExists(String email);


}
