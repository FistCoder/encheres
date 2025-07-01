package fr.eni.projet.encheres.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        // Création d'un gestionnaire d'utilisateurs qui va récupérer les infos depuis la base de données
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Requête SQL pour récupérer les informations utilisateur (identifiant, mot de passe, enabled)
        userDetailsManager.setUsersByUsernameQuery("select  email, mot_de_passe, 1 from UTILISATEURS where email = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT pseudo, CASE WHEN administrateur = 1 THEN 'ROLE_ADMIN' ELSE 'ROLE_USER' END FROM utilisateurs WHERE pseudo = ?");

        // Retourne le gestionnaire configuré
        return userDetailsManager;
    }

    @Bean
        // Définit la chaîne de filtres de sécurité HTTP
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth
                    .requestMatchers("/").permitAll()


                    // Autorise tout le monde à accéder à la racine (/*)
                    .requestMatchers("/*").permitAll()

                    // Autorise tout le monde à accéder à la racine (/*)
                    .requestMatchers("/login").permitAll()

                    // Autorise tout le monde à accéder aux fichiers CSS (ex: /css/style.css)
                    .requestMatchers("/css/*").permitAll()

                    // Autorise tout le monde à accéder aux images (ex: /images/logo.png)
                    .requestMatchers("/images/*").permitAll()

                    // Bloque toutes les autres requêtes non explicitement autorisées
                    .anyRequest().denyAll();
        });

        // Configuration de la page de connexion personnalisée
        http.formLogin(form -> {
            form
                    .loginPage("/login")    // Définit la page de login personnalisée à /login
                    .permitAll()            // Autorise tout le monde à accéder à la page de login
                    .defaultSuccessUrl("/"); // Après connexion réussie, redirige vers la racine /
        });

        // Configuration de la déconnexion
        http.logout(logout -> {
            logout
                    // Permet de se déconnecter via une requête GET vers /logout (à noter : en général, on préfère POST pour plus de sécurité)
                    .logoutRequestMatcher(request ->
                            request.getMethod().equals("GET") && request.getRequestURI().endsWith("/logout")
                    )
                    .invalidateHttpSession(true)   // Invalide la session HTTP lors de la déconnexion
                    .clearAuthentication(true)     // Efface les données d’authentification
                    .deleteCookies("JSESSIONID")   // Supprime le cookie de session
                    .logoutSuccessUrl("/")          // Redirige vers la page d’accueil après déconnexion
                    .permitAll();                  // Autorise tout le monde à accéder à la déconnexion
        });

        // Construire et retourner la configuration de sécurité complète
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
