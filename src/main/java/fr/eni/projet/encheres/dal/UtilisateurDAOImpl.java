package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Utilisateur findUtilisateur(int noUtilisateur) {

        final String sql = "SELECT * FROM UTILISATEURS WHERE no_Utilisateur = :noUtilisateur ";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("noUtilisateur", noUtilisateur);
        System.out.println(noUtilisateur);

        return jdbcTemplate.queryForObject(sql, map, new UtilisateurMapper() );
    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur) {

        final String sql = "UPDATE UTILISATEURS SET pseudo=:pseudo , nom=:nom , prenom=:prenom , email=:email , telephone=:telephone , " +
                "rue=:rue , code_postal=:codePostal , ville=:ville , mot_de_passe=:motDePasse ";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("pseudo", utilisateur.getPseudo());
        map.addValue("nom", utilisateur.getNom());
        map.addValue("prenom", utilisateur.getPrenom());
        map.addValue("email", utilisateur.getEmail());
        map.addValue("telephone", utilisateur.getTelephone());
        map.addValue("rue", utilisateur.getRue());
        map.addValue("code_postal", utilisateur.getCodePostal());
        map.addValue("ville", utilisateur.getVille());
        map.addValue("motDePasse", utilisateur.getMotDePasse());
        jdbcTemplate.update(sql, map);

    }


    public boolean deleteUtilisateur(int id) {
        return false;
    }


    public List<Utilisateur> getAllUtilisateurs() {
        return List.of();
    }


    public void addUtilisateur(Utilisateur utilisateur) {

        String encodedPwd = passwordEncoder.encode(utilisateur.getMotDePasse());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "Insert into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) " +
                "values (:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :mot_de_passe, 0, 0)";
        MapSqlParameterSource map = getMapSqlParameterSource(utilisateur, encodedPwd);

        jdbcTemplate.update(sql, map, keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null) {
            // Mise à jour de l'identifiant du film auto-généré par la base
            utilisateur.setNoUtilisateur(keyHolder.getKey().intValue());
        }
    }

    private static MapSqlParameterSource getMapSqlParameterSource(Utilisateur utilisateur, String EncodedMdp) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("prenom", utilisateur.getPrenom());
        map.addValue("nom", utilisateur.getNom());
        map.addValue("email", utilisateur.getEmail());
        map.addValue("telephone", utilisateur.getTelephone());
        map.addValue("pseudo", utilisateur.getPseudo());
        map.addValue("rue", utilisateur.getRue());
        map.addValue("ville", utilisateur.getVille());
        map.addValue("code_postal", utilisateur.getCodePostal());
        map.addValue("mot_de_passe", EncodedMdp);
        return map;
    }

    @Override
    public void deleteUtilisateur(String email) {
        String sql = "DELETE  FROM UTILISATEURS WHERE email = :email";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("email", email);

        jdbcTemplate.update(sql, map);
    }

    @Override
    public Utilisateur findByEmail(String email) {
        String sql = "SELECT * FROM UTILISATEURS WHERE email = :email";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("email", email);

        return jdbcTemplate.queryForObject(sql, map, new UtilisateurMapper());
    }

    @Override
    public int checkPseudoExists(String pseudo) {
        // Prépare la requête SQL pour compter le nombre d'utilisateurs avec cet email
        String sql = "SELECT COUNT(*) FROM UTILISATEURS WHERE pseudo = :pseudo";
        // Crée un map pour lier le paramètre email à la requête
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("pseudo", pseudo);
        // Exécute la requête et récupère le résultat (nombre d'occurrences)
        int count = jdbcTemplate.queryForObject(sql, map, Integer.class);
        // Retourne le nombre d'utilisateurs trouvés avec cet email
        return count;
    }


    // (Samir) Check si l'email existe dans la base. Cette requête retourne le nombre de lignes dans la table UTILISATEURS où la colonne email est égale à la valeur donnée (:email).
@Override
public int checkEmailExists(String email) {
    // Prépare la requête SQL pour compter le nombre d'utilisateurs avec cet email
    String sql = "SELECT COUNT(*) FROM UTILISATEURS WHERE email = :email";
    // Crée un map pour lier le paramètre email à la requête
    MapSqlParameterSource map = new MapSqlParameterSource();
    map.addValue("email", email);
    // Exécute la requête et récupère le résultat (nombre d'occurrences)
    int count = jdbcTemplate.queryForObject(sql, map, Integer.class);
    // Retourne le nombre d'utilisateurs trouvés avec cet email
    return count;
}

    private class UtilisateurMapper implements RowMapper<Utilisateur> {
        @Override
        public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
           Utilisateur utilisateur = new Utilisateur();
           utilisateur.setPseudo(rs.getString("pseudo"));
           utilisateur.setNom(rs.getString("nom"));
           utilisateur.setPrenom(rs.getString("prenom"));
           utilisateur.setEmail(rs.getString("email"));
           utilisateur.setTelephone(rs.getString("telephone"));
           utilisateur.setRue(rs.getString("rue"));
           utilisateur.setCodePostal(rs.getInt("code_postal"));
           utilisateur.setVille(rs.getString("ville"));

            return utilisateur;
        }
    }
}
