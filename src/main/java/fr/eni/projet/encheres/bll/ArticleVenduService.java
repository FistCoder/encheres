package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.ArticleVendu;

import java.util.List;

public interface ArticleVenduService {
    List<ArticleVendu> getAllArticleVenduAndUser();
    List<ArticleVendu> getAllArticleVenduAndUserByMail (String mail);
    List<ArticleVendu> getArticleVenduNonDebuteeByUserMail(String email);
    List<ArticleVendu> getArticleVenduTermineeByUserMail(String email);
    List<ArticleVendu> getEnchereOuvertes();
    List<ArticleVendu> getMesEncheresByUserMail(String email);
    List<ArticleVendu> getEncheresRemporteesByUserMail(String email);

}
