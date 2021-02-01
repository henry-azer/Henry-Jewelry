package henry.jewelry.controller;

import henry.jewelry.entity.Product;
import henry.jewelry.entity.User;
import henry.jewelry.services.CartServices;
import henry.jewelry.services.ProductServices;
import henry.jewelry.services.UserServices;
import henry.jewelry.services.WishlistServices;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class JewelryController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private ProductServices productServices;

    @Autowired
    private CartServices cartServices;

    @Autowired
    private WishlistServices wishlistServices;

    Product tempProduct;

    @RequestMapping("")
    public String home() {
        return "home-page";
    }

    @RequestMapping("/login")
    public String loginPage(HttpServletRequest request) {
        if (userServices.isAuthenticatedUser()) return "redirect:/";

        String referrer = request.getHeader("Referer");
        if(referrer!=null) request.getSession().setAttribute("url_prior_login", referrer);

        return "login-page";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) new SecurityContextLogoutHandler().logout(request, response, auth);

        return "redirect:/login?logout";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("ringGold", productServices.getAllRingsGold());
        model.addAttribute("ringSilver", productServices.getAllRingsSilver());
        model.addAttribute("ringDiamond", productServices.getAllRingsDiamond());

        model.addAttribute("earringsGold", productServices.getAllEarringsGold());
        model.addAttribute("earringsSilver", productServices.getAllEarringsSilver());
        model.addAttribute("earringsDiamond", productServices.getAllEarringsDiamond());

        model.addAttribute("necklaceGold", productServices.getAllNecklaceGold());
        model.addAttribute("necklaceSilver", productServices.getAllNecklaceSilver());
        model.addAttribute("necklaceDiamond", productServices.getAllNecklaceDiamond());

        return "products";
    }

    @PostMapping("/product-details")
    public String productDetails(@RequestParam("productId") int productId) throws NotFoundException {
        tempProduct = productServices.getProductById(productId);

        return "redirect:/product";
    }

    @GetMapping("/product")
    public String product(Model model) {

        model.addAttribute("productDetails", tempProduct);
        model.addAttribute("suggestedProducts",
                productServices.getSuggestedProducts(tempProduct.getType(), tempProduct.getMetal()));

        return "product";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cartProducts", cartServices.getAllCartProductsByUser(
                getCurrentLoggedUser()));

        return "cart-page";
    }

    @GetMapping("/wishlist")
    public String wishlist(Model model) {
        model.addAttribute("wishlistProducts", wishlistServices.getAllWishlistProductsByUser((
                getCurrentLoggedUser())));

        return "wishlist-page";
    }

    private User getCurrentLoggedUser() {
        return userServices.getCurrentLoggedUser();
    }

}
