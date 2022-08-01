package com.example.ecommerce.ECommerce.resource;

import com.example.ecommerce.ECommerce.global.GlobalData;
import com.example.ecommerce.ECommerce.model.User;
import com.example.ecommerce.ECommerce.repository.UserRepo;
import com.example.ecommerce.ECommerce.service.CategoryService;
import com.example.ecommerce.ECommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller  // to make a below class as a controller
@RequestMapping("/")
public class MyController {

    @Autowired
    UserRepo ur;

    @Autowired
    CategoryService cs;

    @Autowired
    ProductService ps;

    @GetMapping({"/","/Ecommerce"})
    public String getHome(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("categories",cs.getAllCategory());
        model.addAttribute("products",ps.getAllProducts());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id){
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("categories",cs.getAllCategory());
        model.addAttribute("products",ps.getAllProductsByCategoryId(id));
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable int id){
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("product",ps.getProductById(id).get());///remark .get()
        return "viewProduct";
    }





//    @PostMapping(value = "/register")
//    public ModelAndView checkLoginOrRegister(Model mod,@Param("sub1")String sub1,@Param("ema")String ema,@Param("pass")String pass)
//    {
//        System.out.println(""+ema+","+pass);
//        System.out.println(sub1);
//        if(sub1.equals("Login"))
//        {
//            User u = ur.findByEmailAndPassword(ema,pass);
//            ModelAndView model = new ModelAndView();
//
//            if(u==null){
//                //return "login";
//                model.setViewName("login");
//                return model;
//            }
//            else{
//                mod.addAttribute("newuser",u);
//                model.addObject(mod);
//                model.setViewName("index2");
//                return model;
//                //return "index2";
//            }
//
//        }
//        else
//        {
//            //System.out.println("here");
//            //return "register";
//            ModelAndView model = new ModelAndView();
//            model.setViewName("register");
//            return model;
//        }
//    }
//
//    @PostMapping(value = "/homec")
//    public ModelAndView addUser(Model mod, @Param("sub")String sub, @Param("name")String name, @Param("ema")String ema, @Param("pass")String pass)
//    {
//        //User u = new User(name,ema,pass);
//        System.out.println(""+name+","+ema+","+pass);
//        //ur.save(u);
//        System.out.println("Successfully");
//        //mod.addAttribute("newuser",u);
//        ModelAndView model = new ModelAndView();
//        model.addObject(mod);
//        model.setViewName("index2");
//        return model;
//        //return "index";
//    }
}
