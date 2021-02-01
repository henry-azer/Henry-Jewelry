package henry.jewelry.services.impelementations;

import henry.jewelry.entity.Cart;
import henry.jewelry.entity.Product;
import henry.jewelry.entity.User;
import henry.jewelry.repository.CartRepository;
import henry.jewelry.services.CartServices;
import henry.jewelry.services.ProductServices;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class CartServicesImpl implements CartServices {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductServices productServices;

    @Override
    @Transactional
    public Collection<Cart> getAllCartProductsByUser(User user) {
        return cartRepository.findCartsByUser(user);
    }

    @Override
    @Transactional
    public void addProductToCart(Integer productId, Integer quantity, User user) throws NotFoundException {
        Product product = productServices.getProductById(productId);
        Cart cartItem = cartRepository.findCartsByUserAndProduct(user, product);

        if (cartItem != null)
            cartItem.setQuantity(quantity);
        else {
            cartItem = new Cart();
            cartItem.setQuantity(quantity);
            cartItem.setProduct(product);
            cartItem.setUser(user);
        }

        cartRepository.save(cartItem);
    }

    @Override
    @Transactional
    public void updateProductInCart(Integer productId, Integer quantity, User user) throws NotFoundException {
        Product product = productServices.getProductById(productId);
        Cart cartItem = cartRepository.findCartsByUserAndProduct(user, product);

        if (cartItem != null) cartItem.setQuantity(quantity);
        else return;

        cartRepository.saveAndFlush(cartItem);
    }

    @Override
    @Transactional
    public void deleteProductFromCart(Integer productId, User user) throws NotFoundException {
        cartRepository.deleteByUserAndProduct(user, productServices.getProductById(productId));
    }

    @Override
    @Transactional
    public void deleteAllProductsFromCart(User user) {
        cartRepository.deleteAllByUser(user);
    }

}
