package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.Utilisateur;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDAOImpl implements UtilisateurDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Utilisateur findUtilisateur(int noUtilisateur) {

        final String sql = "SELECT * FROM UTILISATEURS WHERE no_Utilisateur = :noUtilisateur ";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("no_Utilisateur", noUtilisateur);

        return jdbcTemplate.queryForObject(sql, map, new UtilisateurMapper() );
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
