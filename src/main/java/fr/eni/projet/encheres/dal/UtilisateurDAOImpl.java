package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public UtilisateurDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Utilisateur findUtilisateur(int noUtilisateur) {

        final String sql = "SELECT * FROM UTILISATEURS WHERE no_Utilisateur = :noUtilisateur ";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("noUtilisateur", noUtilisateur);
        System.out.println(noUtilisateur);

        return jdbcTemplate.queryForObject(sql, map, new UtilisateurMapper() );
    }

    @Override
    public boolean updateUtilisateur(Utilisateur utilisateur) {

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

        return true;

    }

    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        return List.of();
    }

    @Override
    public boolean addUtilisateur(Utilisateur utilisateur) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "Insert into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) " +
                "values (:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :mot_de_passe, 0, 0)";
        MapSqlParameterSource map = getMapSqlParameterSource(utilisateur);

        jdbcTemplate.update(sql, map, keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null) {
            // Mise à jour de l'identifiant du film auto-généré par la base
            utilisateur.setNoUtilisateur(keyHolder.getKey().intValue());
            return true;
        }
            return false;
    }

    private static MapSqlParameterSource getMapSqlParameterSource(Utilisateur utilisateur) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("prenom", utilisateur.getPrenom());
        map.addValue("nom", utilisateur.getNom());
        map.addValue("email", utilisateur.getEmail());
        map.addValue("telephone", utilisateur.getTelephone());
        map.addValue("pseudo", utilisateur.getPseudo());
        map.addValue("rue", utilisateur.getRue());
        map.addValue("ville", utilisateur.getVille());
        map.addValue("code_postal", utilisateur.getCodePostal());
        map.addValue("mot_de_passe", utilisateur.getMotDePasse());
        return map;
    }

    @Override
    public boolean deleteUtilisateur(int id) {
        return false;
    }

    @Override
    public Utilisateur findByEmail(String email) {
        String sql = "SELECT * FROM UTILISATEURS WHERE email = :email";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("email", email);

        return jdbcTemplate.queryForObject(sql, map, new UtilisateurMapper());
    }

    private class UtilisateurMapper implements RowMapper<Utilisateur> {
        @Override
        public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
           Utilisateur utilisateur = new Utilisateur();
           utilisateur.setNoUtilisateur(rs.getInt("no_Utilisateur"));
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
