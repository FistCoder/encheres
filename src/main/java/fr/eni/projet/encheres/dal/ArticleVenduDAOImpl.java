package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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


    private class ArticleVenduMapper implements RowMapper<ArticleVendu> {
        @Override
        public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
            ArticleVendu article = new ArticleVendu();

            // ArticleVendu
            article.setNoArticle(rs.getInt("no_article"));
            article.setNomArticle(rs.getString("nom_article"));
            article.setDescription(rs.getString("description"));
            article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
            article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
            article.setMiseAPrix(rs.getInt("prix_initial"));
            article.setPrixVente(rs.getInt("prix_vente"));


            // Utilisateur (vendeur)
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
            utilisateur.setPseudo(rs.getString("pseudo"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setTelephone(rs.getString("telephone"));
            utilisateur.setRue(rs.getString("rue"));
            utilisateur.setCodePostal(rs.getString("code_postal"));
            utilisateur.setVille(rs.getString("ville"));
            utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
            utilisateur.setCredit(rs.getInt("credit"));
            utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
            article.setUtilisateur(utilisateur);

            // Categorie
            Categorie categorie = new Categorie();
            categorie.setLibelle(rs.getString("libelle"));
            article.setCategorie(categorie);

            // Retrait (peut être null)
            String retraitRue = rs.getString("rue");
            if (retraitRue != null) {
                Retrait retrait = new Retrait();
                retrait.setRue(retraitRue);
                retrait.setCode_postal(rs.getString("code_postal"));
                retrait.setVille(rs.getString("ville"));
                article.setLieuRetrait(retrait);
            }

            // Enchere (peut être null)
            int noEnchere = rs.getInt("no_enchere");
            if (!rs.wasNull()) {
                Enchere enchere = new Enchere();


                enchere.setDateEnchere(rs.getTimestamp("date_enchere").toLocalDateTime());
                enchere.setMontantEnchere(rs.getInt("montant_enchere"));
                enchere.setNoUtilisateur(rs.getInt("no_utilisateur"));
                article.setEnchere(enchere);
            }

            return article;
        }
    }

}
