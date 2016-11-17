package main.service.impl

import main.service.AuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
/**
 * Created by chinchik_b on 16.11.2016.
 */
@Component
@Profile("database")
public class AuthenticationServiceDataBaseImpl implements AuthenticationService{
    @Autowired
    UserDetailsService userDetailsService

    @Override
    @Autowired
    public void setAuthenticationMethod(AuthenticationManagerBuilder auth) {
        auth.userDetailsService(userDetailsService)

    }
}
