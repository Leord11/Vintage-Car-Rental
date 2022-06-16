package com.leord.car_rental;


import com.leord.car_rental.security.models.User;
import com.leord.car_rental.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String redirectUrl = "";
        Collection<?extends GrantedAuthority> authorities = authentication.getAuthorities();

        for(GrantedAuthority grantedAuthority : authorities) {
//            System.out.println("role" + grantedAuthority.getAuthority());
            if(grantedAuthority.getAuthority().equals("USER")) {
                redirectUrl = "/user/index";
                break;
            } else if (grantedAuthority.getAuthority().equals("ADMIN")) {
                redirectUrl = "/admin/index";
                break;
            }

        }
        if(redirectUrl == "") {
            throw new IllegalStateException();
        }

        new DefaultRedirectStrategy().sendRedirect(request,response,redirectUrl);
    }
}
