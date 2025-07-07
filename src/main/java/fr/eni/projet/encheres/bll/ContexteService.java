package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.Utilisateur;

public interface ContexteService {
    Utilisateur charger(String email);
}
