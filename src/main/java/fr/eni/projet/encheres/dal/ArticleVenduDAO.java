package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.ArticleVendu;

import java.util.List;

public interface ArticleVenduDAO {
    public List<ArticleVendu> getArticleVenduEnCoursByUserId(int noUtilisateur);
    public List<ArticleVendu> getArticleVenduNonDebuteeByUserId(int noUtilisateur);
    public List<ArticleVendu> getArticleVenduTermineeByUserId(int noUtilisateur);
}
