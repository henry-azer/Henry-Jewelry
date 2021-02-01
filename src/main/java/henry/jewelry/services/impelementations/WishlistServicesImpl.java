package henry.jewelry.services.impelementations;

import henry.jewelry.entity.Product;
import henry.jewelry.entity.User;
import henry.jewelry.entity.Wishlist;
import henry.jewelry.repository.WishlistRepository;
import henry.jewelry.services.ProductServices;
import henry.jewelry.services.WishlistServices;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class WishlistServicesImpl implements WishlistServices {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductServices productServices;

    @Override
    @Transactional
    public Collection<Wishlist> getAllWishlistProductsByUser(User user) {
        return wishlistRepository.findWishesByUser(user);
    }

    @Override
    @Transactional
    public void addProductToWishlist(Integer productId, User user) throws NotFoundException {
        Product product = productServices.getProductById(productId);
        Wishlist wishlistItem = wishlistRepository.findWishesByUserAndProduct(user, product);

        if (wishlistItem != null) return;
        else {
            wishlistItem = new Wishlist();
            wishlistItem.setProduct(product);
            wishlistItem.setUser(user);
        }

        wishlistRepository.save(wishlistItem);
    }

    @Override
    @Transactional
    public void deleteProductFromWishlist(Integer productId, User user) throws NotFoundException {
        wishlistRepository.deleteByUserAndProduct(user, productServices.getProductById(productId));
    }

}
