package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {

    public Utilisateur findUtilisateur(int noUtilisateur);

    public List<Utilisateur> getAllUtilisateurs();

    public boolean addUtilisateur(Utilisateur utilisateur);

    public boolean updateUtilisateur(Utilisateur utilisateur);

    public boolean deleteUtilisateur(int id);



}
