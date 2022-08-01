package com.example.ecommerce.ECommerce.resource;

import com.example.ecommerce.ECommerce.global.GlobalData;
import com.example.ecommerce.ECommerce.model.Role;
import com.example.ecommerce.ECommerce.model.User;
import com.example.ecommerce.ECommerce.repository.RoleRepo;
import com.example.ecommerce.ECommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    public BCryptPasswordEncoder bce;

    @Autowired
    UserRepo ur;

    @Autowired
    RoleRepo rr;

    @GetMapping("/login")
    public String login(){
        GlobalData.cart.clear();
        return "login";
    }

    @PostMapping("/login")
    public String fromLogin(@ModelAttribute("user")User user, HttpServletRequest request)throws ServletException{
        System.out.println("here");
        return "redirect:/shop";
    }

    @GetMapping("/register")
    public String registerGet(){
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("user")User user, HttpServletRequest request)throws ServletException {
        String password = user.getPassword();
        user.setPassword(bce.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(rr.findById(2).get());
        user.setRoles(roles);
        ur.save(user);
        //System.out.println("done");
        request.login(user.getEmail(),password);
        return "redirect:/shop";
    }
}
