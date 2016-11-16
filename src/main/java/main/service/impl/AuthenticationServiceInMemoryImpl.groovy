package main.service.impl

import main.service.AuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Component
/**
 * Created by chinchik_b on 16.11.2016.
 */

@Component
@Profile("memory")
public class AuthenticationServiceInMemoryImpl implements AuthenticationService {

    @Override
    @Autowired
    public void setAuthenticationMethod(AuthenticationManagerBuilder auth) {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("123").roles("ADMIN")
                .and()
                .withUser("user").password("user").roles("USER")
    }
}