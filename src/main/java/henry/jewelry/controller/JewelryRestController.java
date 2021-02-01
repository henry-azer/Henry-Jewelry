package henry.jewelry.controller;

import henry.jewelry.entity.User;
import henry.jewelry.services.EmailServices;
import henry.jewelry.services.CartServices;
import henry.jewelry.services.UserServices;
import henry.jewelry.services.WishlistServices;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class JewelryRestController {

    @Autowired
    private CartServices cartServices;

    @Autowired
    private UserServices userServices;

    @Autowired
    private EmailServices emailServices;

    @Autowired
    private WishlistServices wishlistServices;

    @ResponseBody
    @PostMapping(value = "/user/cart/add",produces = MediaType.APPLICATION_JSON_VALUE)
    public void addToCart(
            @RequestParam("productId") int productId,
            @RequestParam("quantity") int quantity) throws NotFoundException {

        cartServices.addProductToCart(productId,quantity, getCurrentLoggedUser());
    }

    @ResponseBody
    @PostMapping(value = "/user/cart/update",produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateCartItem(
            @RequestParam("productId") int productId,
            @RequestParam("quantity") int quantity) throws NotFoundException {

        cartServices.updateProductInCart(productId,quantity, getCurrentLoggedUser());
    }

    @ResponseBody
    @PostMapping(value = "/user/cart/delete",produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteFromCart(@RequestParam("productId") Integer productId) throws NotFoundException {

        cartServices.deleteProductFromCart(productId, getCurrentLoggedUser());
    }

    @ResponseBody
    @PostMapping("/user/cart/delete-all")
    public void deleteAllFromCart() {

        cartServices.deleteAllProductsFromCart(getCurrentLoggedUser());
    }

    @ResponseBody
    @PostMapping(value = "/user/wishlist/add",produces = MediaType.APPLICATION_JSON_VALUE)
    public void addToWishList(
            @RequestParam("productId") int productId) throws NotFoundException {

        wishlistServices.addProductToWishlist(productId, getCurrentLoggedUser());
    }

    @ResponseBody
    @PostMapping(value = "/user/wishlist/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteFromWishlist(@RequestParam("productId") Integer productId) throws NotFoundException {

        wishlistServices.deleteProductFromWishlist(productId, getCurrentLoggedUser());
    }

    @ResponseBody
    @PostMapping(value = "/send-feedback", produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendFeedback(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String subject,
            @RequestParam String message) {

        emailServices.sendEmail(name, email, subject, message);
    }

    private User getCurrentLoggedUser() {
        return userServices.getCurrentLoggedUser();
    }

}
