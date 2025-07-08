package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.ArticleVendu;
import fr.eni.projet.encheres.bo.Enchere;
import fr.eni.projet.encheres.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EnchereDAOImpl implements EnchereDAO {
    // Requête SQL pour récupérer toutes les enchères
    private final String SELECT_ALL_ENCHERES = "SELECT * FROM ENCHERES";

    // Injection du template JDBC pour exécuter les requêtes SQL
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    // Récupère toutes les enchères de la base de données
    @Override
    public List<Enchere> findAll() {
        List<Enchere> encheres = jdbcTemplate.query(SELECT_ALL_ENCHERES, new EnchereRowMapper());
        return encheres;
    }

    // Classe interne pour mapper chaque ligne du résultat SQL vers un objet Enchere
    class EnchereRowMapper implements RowMapper<Enchere> {
        @Override
        public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {
            Enchere e = new Enchere();
            // Conversion de la date SQL en LocalDate
            e.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
            // Récupération du montant de l'enchère
            e.setMontant_enchere(rs.getLong("montant_enchere"));

            // Création et association de l'utilisateur lié à l'enchère
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
            e.setUtilisateur(utilisateur);

            // Création et association de l'article vendu lié à l'enchère
            ArticleVendu article = new ArticleVendu();
            article.setNoArticle(rs.getInt("no_article"));
            e.setArticleVendu(article);

            return e;
        }
    }
}