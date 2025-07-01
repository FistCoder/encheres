package fr.eni.projet.encheres.configuration;

import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        // Création d'un gestionnaire d'utilisateurs qui va récupérer les infos depuis la base de données
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Requête SQL pour récupérer les informations utilisateur (identifiant, mot de passe, enabled)

        userDetailsManager.setUsersByUsernameQuery("select  email, password, 1 from MEMBRE where email = ?");

        // Requête SQL pour récupérer les rôles associés à un utilisateur identifié par son pseudo
        userDetailsManager.setAuthoritiesByUsernameQuery("select m.email ,r.ROLE  from ROLES as r inner join MEMBRE as m on r.IS_ADMIN = m.admin where email = ?");

        // Retourne le gestionnaire configuré
        return userDetailsManager;
    }
}
