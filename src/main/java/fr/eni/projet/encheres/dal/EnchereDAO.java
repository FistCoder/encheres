package fr.eni.projet.encheres.dal;


import fr.eni.projet.encheres.bo.Enchere;

import java.util.List;

public interface EnchereDAO {
    List<Enchere> findAll();
}