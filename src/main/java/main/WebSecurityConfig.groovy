package main

import main.service.AuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and().csrf().disable()
                .authorizeRequests()
                     .antMatchers("/dir").authenticated()
                    // .antMatchers("/dir").access("hasAnyRole('ADMIN','USER')")
                     .anyRequest().permitAll()
                .and()
                     .formLogin()
                     .loginPage("/login")
                     .permitAll()
                .and()
                     .csrf().disable()
                     .exceptionHandling()
                     .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                .and()
                     .logout()
                     .permitAll()
    }
    @Autowired
    private AuthenticationService authenticationService
    //@Autowired
    public void methodCall() {
        authenticationService.setAuthenticationMethod()

                /*.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN")
                .and()
                .withUser("user").password("user").roles("USER")*/
   }
}

