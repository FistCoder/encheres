package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.ArticleVendu;

import java.util.List;

public interface ArticleVenduDAO {

    List<ArticleVendu> getAllArticleVenduAndUser();
    List<ArticleVendu> getArticleVenduEnCoursByUserId(int noUtilisateur);
    List<ArticleVendu> getArticleVenduNonDebuteeByUserId(int noUtilisateur);
    List<ArticleVendu> getArticleVenduTermineeByUserId(int noUtilisateur);

}
