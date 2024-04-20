package org.xproce.revormclass.web;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xproce.revormclass.dao.entities.Product;
import org.xproce.revormclass.service.ProductManager;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductManager productManager;

    @GetMapping("/")
        public String redirectTo(){
        return "redirect:/listProduit";
    }
    @GetMapping("/getProductsList")
    public String getALlProducts(Model model,
                                 @RequestParam (name="page",defaultValue="0") int page,
                                 @RequestParam (name="taille",defaultValue="5") int taille,
                                 @RequestParam(name = "keywords", defaultValue = "") String keyword
                                 ) {

        Page<Product> products = productManager.searchProducts(keyword,page,taille);
        model.addAttribute("listProduits", products.getContent());
        int[] pages = new int[products.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        return "listProduit";
    }


    @GetMapping("/ajouterproduit")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "addProduit";
    }
    @PostMapping("ajouterproduit")
    public String addproductdb(@Valid Product product,
                               BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "addProduit" ;
        }
        productManager.addProduct(product);
        return "redirect:getProductsList";
    }
    @GetMapping("/modifierproduit/{id}")
    public String updateProduct(@PathVariable("id") Integer id, Model model) {
        Product product = productManager.getProductById(id);
        if (product == null) {
            return "redirect:/error";
        }
        model.addAttribute("product", product);
        return "updateProduit";
    }
    @PostMapping("/modifierproduit")
    public String updateProduct(@RequestParam("id") Integer id,
                                @RequestParam("description") String description,
                                @RequestParam("price") double price, Model model) {
            Product product = productManager.getProductById(id);
            product.setDescription(description);
            product.setPrice(price);
            productManager.updateProduct(product);
        return "redirect:/getProductsList";
    }
    @GetMapping("/supprimerproduit/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, Model model)
    {
            Product product = productManager.getProductById(id);
            productManager.deleteProduct(product);
          //  return getALlProducts(model,0,5);
        return "redirect:/getProductsList";
    }
    @GetMapping("/afficherproduits/{id}")
    public String showProduct(@PathVariable("id") Integer id, Model model)
    {
        Product product = productManager.getProductById(id);
        model.addAttribute("listpdts",product);
        return "afficherproduits";
    }
}
