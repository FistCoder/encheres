package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Class ArticleVenduDAOImpl for
 */
@Repository
public class ArticleVenduDAOImpl implements ArticleVenduDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public ArticleVenduDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ArticleVendu> getAllArticleVenduAndUser() {
        String sql = "SELECT " +
                "    av.no_article," +
                "    av.nom_article," +
                "    av.description," +
                "    av.date_debut_encheres," +
                "    av.date_fin_encheres," +
                "    av.prix_initial," +
                "    av.prix_vente," +
                "    av.no_utilisateur," +
                "    av.no_categorie," +
                "    u.pseudo," +
                "    u.nom," +
                "    u.prenom," +
                "    u.email," +
                "    u.telephone," +
                "    u.rue AS rue," +
                "    u.code_postal AS code_postal," +
                "    u.ville AS ville," +
                "    u.mot_de_passe," +
                "    u.credit," +
                "    u.administrateur," +
                "    c.libelle AS libelle," +
                "    r.rue AS retrait_rue," +
                "    r.code_postal AS retrait_code_postal," +
                "    r.ville AS retrait_ville," +
                "    e.no_enchere," +
                "    e.date_enchere," +
                "    e.montant_enchere," +
                "    e.no_utilisateur AS enchere_no_utilisateur " +
                "    FROM ARTICLES_VENDUS av" +
                "    INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur" +
                "    INNER JOIN CATEGORIES c ON av.no_categorie = c.no_categorie" +
                "    LEFT JOIN RETRAITS r ON av.no_article = r.no_article" +
                "    LEFT JOIN ENCHERES e ON av.no_article = e.no_article" +
                "   ORDER BY av.no_article;";

        return jdbcTemplate.query(sql, new ArticleVenduExtractor());
    }

    @Override
    public List<ArticleVendu> getArticleVenduEnCoursByUserMail(String email) {
        String sql = "SELECT " +
                "    av.no_article," +
                "    av.nom_article," +
                "    av.description," +
                "    av.date_debut_encheres," +
                "    av.date_fin_encheres," +
                "    av.prix_initial," +
                "    av.prix_vente," +
                "    av.no_utilisateur," +
                "    av.no_categorie," +
                "    u.pseudo," +
                "    u.nom," +
                "    u.prenom," +
                "    u.email," +
                "    u.telephone," +
                "    u.rue AS rue," +
                "    u.code_postal AS code_postal," +
                "    u.ville AS ville," +
                "    u.mot_de_passe," +
                "    u.credit," +
                "    u.administrateur," +
                "    c.libelle AS libelle," +
                "    r.rue AS retrait_rue," +
                "    r.code_postal AS retrait_code_postal," +
                "    r.ville AS retrait_ville," +
                "    e.no_enchere," +
                "    e.date_enchere," +
                "    e.montant_enchere," +
                "    e.no_utilisateur AS enchere_no_utilisateur " +
                "    FROM ARTICLES_VENDUS av" +
                "    INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur" +
                "    INNER JOIN CATEGORIES c ON av.no_categorie = c.no_categorie" +
                "    LEFT JOIN RETRAITS r ON av.no_article = r.no_article" +
                "    LEFT JOIN ENCHERES e ON av.no_article = e.no_article" +
                "   WHERE GETDATE()>date_debut_encheres AND GETDATE()<date_fin_encheres AND email = :email" +
                "   ORDER BY av.no_article;";

                MapSqlParameterSource params = new MapSqlParameterSource("email", email);

        return jdbcTemplate.query(sql, params, new ArticleVenduExtractor());

    }

    @Override
    public List<ArticleVendu> getArticleVenduNonDebuteeByUserId(int noUtilisateur) {
        return List.of();
    }

    @Override
    public List<ArticleVendu> getArticleVenduTermineeByUserId(int noUtilisateur) {
        return List.of();
    }


    public class ArticleVenduExtractor implements ResultSetExtractor<List<ArticleVendu>> {

        @Override
        public List<ArticleVendu> extractData(ResultSet rs) throws SQLException {
            // Map temporaire pour éviter de créer plusieurs fois le même ArticleVendu.
            // La clé est l'identifiant unique de l'article (no_article).
            Map<Integer, ArticleVendu> articlesMap = new LinkedHashMap<>();

            // Parcours du ResultSet ligne par ligne
            while (rs.next()) {
                int noArticle = rs.getInt("no_article");

                // Vérifie si l'article a déjà été traité (présent dans la map)
                ArticleVendu article = articlesMap.get(noArticle);

                // Si l'article n'existe pas encore, on le crée et on le remplit
                if (article == null) {
                    article = new ArticleVendu();

                    // ==== Remplissage des données de l'article ====
                    article.setNoArticle(noArticle);
                    article.setNomArticle(rs.getString("nom_article"));
                    article.setDescription(rs.getString("description"));
                    article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
                    article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
                    article.setMiseAPrix(rs.getInt("prix_initial"));
                    article.setPrixVente(rs.getInt("prix_vente"));


                    // ==== Données du vendeur (Utilisateur) ====
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
                    utilisateur.setPseudo(rs.getString("pseudo"));
                    utilisateur.setNom(rs.getString("nom"));
                    utilisateur.setPrenom(rs.getString("prenom"));
                    utilisateur.setEmail(rs.getString("email"));
                    utilisateur.setTelephone(rs.getString("telephone"));
                    utilisateur.setRue(rs.getString("rue")); // ⚠️ Attention : ce champ est utilisé aussi pour le retrait
                    utilisateur.setCodePostal(rs.getString("code_postal"));
                    utilisateur.setVille(rs.getString("ville"));
                    utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
                    utilisateur.setCredit(rs.getInt("credit"));
                    utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
                    article.setUtilisateur(utilisateur);

                    // ==== Données de la catégorie ====
                    Categorie categorie = new Categorie();
                    categorie.setLibelle(rs.getString("libelle")); // libelle de la catégorie
                    article.setCategorie(categorie);

                    // ==== Données de retrait (si présentes) ====
                    String retraitRue = rs.getString("retrait_rue"); // ⚠️ Même nom que pour l'utilisateur
                    if (retraitRue != null) {
                        Retrait retrait = new Retrait();
                        retrait.setRue(retraitRue);
                        retrait.setCode_postal(rs.getString("retrait_code_postal"));
                        retrait.setVille(rs.getString("retrait_ville"));
                        article.setLieuRetrait(retrait);
                    }

                    // On enregistre l'article dans la map pour ne pas le recréer
                    articlesMap.put(noArticle, article);
                }

                // ==== Données de l'enchère (si présentes) ====
                int noEnchere = rs.getInt("no_enchere");

                // Vérifie que l'enchère existe (pas NULL)
                if (!rs.wasNull()) {
                    Enchere enchere = new Enchere();

                    enchere.setDateEnchere(rs.getDate("date_debut_encheres").toLocalDate());

                    enchere.setMontant_enchere(rs.getInt("montant_enchere"));

                    // Tu pourrais ajouter l'utilisateur enchérisseur ici si ta requête SQL le permet.

                    // Ajout de l'enchère dans la liste associée à l'article
                    article.getEncheresList().add(enchere);
                }
            }

            // On retourne la liste des articles, chacun pouvant contenir plusieurs enchères
            return new ArrayList<>(articlesMap.values());
        }
    }


}
