package main.service

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder

/**
 * Created by chinchik_b on 16.11.2016.
 */
public interface AuthenticationService {
    public void setAuthenticationMethod(AuthenticationManagerBuilder auth)
}
