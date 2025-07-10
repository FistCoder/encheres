package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.ArticleVendu;

import java.util.List;

public interface ArticleVenduDAO {

    List<ArticleVendu> getAllArticleVenduAndUser();
    List<ArticleVendu> getArticleVenduEnCoursByUserMail(String email);
    List<ArticleVendu> getArticleVenduNonDebuteeByUserId(int noUtilisateur);
    List<ArticleVendu> getArticleVenduTermineeByUserId(int noUtilisateur);

}
