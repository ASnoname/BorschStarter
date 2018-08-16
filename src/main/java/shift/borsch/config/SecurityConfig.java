package shift.borsch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import shift.borsch.controllers.Resources;
import shift.borsch.entities.enums.Role;
import org.springframework.security.core.userdetails.User;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(Resources.ROOT_PREFIX).permitAll()
                .antMatchers(Resources.ADMIN_PREFIX).hasRole(Role.ADMIN.name())
                .anyRequest().hasRole(Role.USER.name())

                .and()

                .formLogin()
                .loginPage(Resources.LOGIN_PREFIX)
                .permitAll()

                .and()

                .logout()
                .logoutUrl(Resources.LOGOUT_PREFIX)
                .logoutSuccessUrl(Resources.ROOT_PREFIX)
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(User.withDefaultPasswordEncoder().username("admin").password("admin").roles(Role.ADMIN.name()));
    }
}