package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.exceptions.BusinessException;

public interface UtilisateurService {
    Utilisateur getUtilisateur(int id) throws BusinessException;
    Utilisateur consulterUtilisateurById(int noUtilisateur) throws BusinessException;
    void updateUtilisateur(Utilisateur utilisateur) throws BusinessException;
    void createUtilisateur(Utilisateur utilisateur) throws BusinessException;
    void deleteUtilisateur(String email);
    Utilisateur checkEmailExists(String email);
}