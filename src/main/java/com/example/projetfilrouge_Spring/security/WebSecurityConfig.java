package com.example.projetfilrouge_Spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity      // Activation de la configuration personnalisée de la sécurité
public class WebSecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //Initialisation d'une instance de BCrypt pour hacher les passwords
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Définition des filtres sur les URLS
    //Accès en fonction d'un état authentifié ou non
    //Accès en fonction de l'utilisateur authentifié (désactivé, expiré..etc)
    //Accès en fonction des roles

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //désactive la gestion des en-têtes CORS au sein de Spring Security
        http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                httpSecurityCorsConfigurer.disable();
            }
        });

        //désactivation de CSRF (Cross - site Request Forgery)
        http.csrf(new Customizer<CsrfConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
                httpSecurityCsrfConfigurer.disable();
            }
        });
        //Configuration des règles d'autorisations concernant les requêtes HTTP
        http.authorizeHttpRequests(requests -> {
            requests
                    // Toutes les requêtes HTTP /api/users sont autorisées pour tout le monde (authentifié ou non)
                    .requestMatchers("/api/users/**").permitAll() //TODO filter access
                    .requestMatchers("/api/auth/**").permitAll()  //TODO filter access
                    .requestMatchers("/api/tickets/**").permitAll() //TODO filter access
                    .requestMatchers("/api/tickets**").permitAll() //TODO filter access
                    //Toutes les autres requêtes HTTP nécessitent une authentification
                    .anyRequest().authenticated();
        });

        //Configuration de la session Sprind sécurité : AUCUNE session ne sera créée côté serveur
        //Moins coûteux et inutile lorsque nous sommes dans une configuration RESTful
        http.sessionManagement(httpSecuritySessionManagementConfigurer -> {
            httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });


        //Ajoute un filtre personnalisé qui s'exécute avant le filtre UsernamePasswordAuthenticationFilter
        //Ce filtre pour gérer l'authentigfication basé sur le JWT reçu dans les en-têtes des requêtes
        //Le filtre UsernamePasswordAuthenticationFilter est un filtre de base de Spring Security
        //Il est exécuté pour gérer l'authentification par username et password
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    AuthJwtTokenFilter authenticationJwtTokenFilter(){
        return new AuthJwtTokenFilter();
    }
}
