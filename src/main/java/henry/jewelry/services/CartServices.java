package henry.jewelry.services;

import henry.jewelry.entity.Cart;
import henry.jewelry.entity.User;
import javassist.NotFoundException;

import java.util.Collection;

public interface CartServices {

    Collection<Cart> getAllCartProductsByUser(User user);
    void addProductToCart(Integer productId, Integer quantity, User user) throws NotFoundException;
    void updateProductInCart(Integer productId, Integer quantity, User user) throws NotFoundException;
    void deleteProductFromCart(Integer productId, User user) throws NotFoundException;
    void deleteAllProductsFromCart(User user);

}
