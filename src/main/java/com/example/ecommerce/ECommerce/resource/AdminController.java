package com.example.ecommerce.ECommerce.resource;

import com.example.ecommerce.ECommerce.dto.ProductDTO;
import com.example.ecommerce.ECommerce.model.Category;
import com.example.ecommerce.ECommerce.model.Product;
import com.example.ecommerce.ECommerce.service.CategoryService;
import com.example.ecommerce.ECommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    CategoryService cs;

    @Autowired
    ProductService ps;

    public static String uploadDir = System.getProperty("user.dir") +"/E-Commerce/src/main/resources/static/productImages";

    @GetMapping("/admin")
    public String adminHome(){
        return "admin";
    }

    @GetMapping("/admin/categories")
    public String getCat(Model model){
        model.addAttribute("categories",cs.getAllCategory());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model){
        model.addAttribute("category",new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category")Category c){
        //model.addAttribute("category",new Category());
        cs.addCategory(c);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id){
        cs.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable int id,Model model){
        Optional<Category> cat = cs.getCategoryById(id);
        if(cat.isPresent()){
            model.addAttribute("category", cat.get());
            return "categoriesAdd";
        }
        else{
            return "404";
        }
    }

    // Product Section
    @GetMapping("/admin/products")
    public String getPro(Model model){
        model.addAttribute("products",ps.getAllProducts());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String getProAdd(Model model){
        model.addAttribute("productDTO",new ProductDTO());
        model.addAttribute("categories",cs.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String postProAdd(@ModelAttribute("ProductDTO")ProductDTO pdto, @RequestParam("productImage")MultipartFile file,@RequestParam("imgName")String imgName) throws IOException {
        Product p = new Product();
        p.setPid(pdto.getPid());
        p.setPname(pdto.getPname());
        p.setCategory(cs.getCategoryById(pdto.getCategoryId()).get());
        p.setPrice(pdto.getPrice());
        p.setDescription(pdto.getDescription());

        String imageUUID;
        if (!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            System.out.println("Here");
            System.out.println(uploadDir);
            System.out.println(imageUUID);
            Path fileNameAndPath = Paths.get(uploadDir,imageUUID);
            //Path fileName = Paths.get(uploadDir,imageUUID);
            //System.out.println(fileName.toString());
            Files.write(fileNameAndPath,file.getBytes());
        }
        else{
            imageUUID = imgName;
            //remark
        }
        p.setImageName(imageUUID);
        ps.addProduct(p);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deletePro(@PathVariable long id){
        ps.removeProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updatePro(@PathVariable long id,Model model){
        Product p = ps.getProductById(id).get();
        ProductDTO pdto = new ProductDTO();
        pdto.setPid(p.getPid());
        pdto.setPname(p.getPname());
        pdto.setCategoryId(p.getCategory().getCid());
        pdto.setPrice(p.getPrice());
        pdto.setDescription(p.getDescription());
        pdto.setImageName(p.getImageName());

        model.addAttribute("categories",cs.getAllCategory());
        model.addAttribute("productDTO",pdto);
        return "productsAdd";
    }
}
