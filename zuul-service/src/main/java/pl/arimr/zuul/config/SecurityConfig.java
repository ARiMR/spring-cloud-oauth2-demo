package pl.arimr.zuul.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()


                .antMatchers("/")
                .permitAll()

                .antMatchers("/login", "/logout")
                .permitAll()

                .antMatchers("/api/hello")
                .hasRole("ADMIN")

                .antMatchers("/api/principal")
                .permitAll()

                .anyRequest()
                .authenticated()

                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll()

                .and()
                .csrf()
                .disable();
    }
}