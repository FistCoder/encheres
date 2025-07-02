package fr.eni.projet.encheres.configuration;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    protected final Log logger = LogFactory.getLog(getClass());
    private final String SELECT_USER = "select  email, mot_de_passe, 1 from UTILISATEURS where email = ?";
    private final String SELECT_ROLES = "SELECT email, administrateur FROM utilisateurs WHERE email = ?";

    /**
     * Récupération des membres de l'application via la base de données
     */
    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(SELECT_USER);
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(SELECT_ROLES);

        return jdbcUserDetailsManager;
    }

    /**
     * Tout le monde doit accéder à la vue principale Restreindre l’accès des autres
     * vues à un membre connecté pour le moment
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth
                    //Permettre aux visiteurs d'accéder à la liste des films
                    .requestMatchers(HttpMethod.GET, "/login").permitAll()
                    // Accès à la vue principale
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/register").denyAll()
                    // Permettre à tous d'afficher correctement les images et CSS
                    .requestMatchers("/css/*").permitAll().requestMatchers("/images/*").permitAll()
                    // Il faut être connecté pour toutes autres URLs
                    .anyRequest().authenticated();
        });

//		//formulaire de connexion par défaut
//		http.formLogin(Customizer.withDefaults());


        // Customiser le formulaire
        http.formLogin(form -> {
            form.loginPage("/login").permitAll();
            form.defaultSuccessUrl("/").permitAll();
        });

        // /logout --> vider la session et le contexte de sécurité
        http.logout(logout ->
                logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/").permitAll());

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
