package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.ArticleVendu;

import java.util.List;

public interface ArticleVenduService {
    List<ArticleVendu> getAllArticleVenduAndUser();
}
