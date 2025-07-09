package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.ArticleVendu;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class ArticleVenduDAOImpl for
 *
 */
@Repository
public class ArticleVenduDAOImpl implements ArticleVenduDAO{

    private NamedParameterJdbcTemplate jdbcTemplate;

    public ArticleVenduDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ArticleVendu> getAllArticleVenduAndUser() {
        String sql = "SELECT nom_article, prix_initial, date_fin_encheres, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON UTILISATEURS.no_Utilisateur = ARTICLES_VENDUS.no_Utilisateur";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ArticleVendu.class));
    }

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
