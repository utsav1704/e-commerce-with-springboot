package com.example.ecommerce.ECommerce.configuration;

import com.example.ecommerce.ECommerce.model.Role;
import com.example.ecommerce.ECommerce.model.User;
import com.example.ecommerce.ECommerce.repository.RoleRepo;
import com.example.ecommerce.ECommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    RoleRepo rolerepo;

    @Autowired
    UserRepo userrepo;

    private RedirectStrategy rs=new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token=(OAuth2AuthenticationToken)  authentication;
        String email=token.getPrincipal().getAttributes().get("email").toString();
        if(!userrepo.findUserByEmail(email).isPresent()){
            User user=new User();
            user.setName(token.getPrincipal().getAttributes().get("given_name").toString());
//            user.setName(token.getPrincipal().getAttributes().get("given_name").toString());
            user.setEmail(email);
            List<Role> roles=new ArrayList<>();
            roles.add(rolerepo.findById(2).get());
            user.setRoles(roles);
            userrepo.save(user);
        }
        rs.sendRedirect(httpServletRequest,httpServletResponse,"/");
    }

}
