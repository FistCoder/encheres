package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.Utilisateur;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

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
