package LimpBiscuit.Demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/home",
                        "/about",
                        "/contact",
                        "/login",
                        "/signup",
                        "/css/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/about")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        http.csrf().disable(); // TODO: Fix this
    }
}
