package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.ArticleVendu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleVenduImpl implements ArticleVenduDAO {

    @Override
    public List<ArticleVendu> getArticleVenduEnCoursByUserId(int noUtilisateur) {
        return List.of();
    }

    @Override
    public List<ArticleVendu> getArticleVenduNonDebuteeByUserId(int noUtilisateur) {
        return List.of();
    }

    @Override
    public List<ArticleVendu> getArticleVenduTermineeByUserId(int noUtilisateur) {
        return List.of();
    }
}
