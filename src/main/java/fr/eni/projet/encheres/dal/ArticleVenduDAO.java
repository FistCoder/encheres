package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.ArticleVendu;

import java.util.List;

public interface ArticleVenduDAO {

    List<ArticleVendu> getAllArticleVenduAndUser();
    List<ArticleVendu> getArticleVenduEnCoursByUserMail(String email);
    List<ArticleVendu> getArticleVenduNonDebuteeByUserMail(String email);
    List<ArticleVendu> getArticleVenduTermineeByUserMail(String email);
    List<ArticleVendu> getEnchereOuvertes();
    List<ArticleVendu> getMesEncheresByUserMail(String email);
    List<ArticleVendu> getEncheresRemporteesByUserMail(String email);

}
