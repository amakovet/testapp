package com.depaul.depaulmarketplace.products;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductsRepository repo;

    @GetMapping
    public String list(Model model, HttpSession session) {
        model.addAttribute("products", repo.findAll());
        if (session.getAttribute("product") == null) {
            model.addAttribute("product", new Products());
            model.addAttribute("btnAddOrModifyLabel", "Add");
        } else {
            model.addAttribute("product", session.getAttribute("product"));
            model.addAttribute("btnAddOrModifyLabel", "Modify");
        }
        return "product/list";
    }

    @GetMapping("/edit/{id}")
    public String get(@PathVariable("id") Long id, Model model, HttpSession session) {
        session.setAttribute("product", repo.findById(id));
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {
        repo.deleteById(id);
        return "redirect:/product";
    }

    @PostMapping
    public String save(@ModelAttribute Products product, HttpSession session) {
        if (product.getId() == 0)
            repo.save(product);
        else {
            var editProduct = repo.findById(product.getId()).get();
            editProduct.setName(product.getName());
            editProduct.setCategory(product.getCategory());
            editProduct.setPrice(product.getPrice());
            editProduct.setInventory(product.getInventory());
            repo.save(editProduct);
            session.setAttribute("product", null);
        }
        return "redirect:/product";
    }

}